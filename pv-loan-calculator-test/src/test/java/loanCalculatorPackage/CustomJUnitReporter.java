package loanCalculatorPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.reporters.JUnitXMLReporter;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.List;

public class CustomJUnitReporter extends JUnitXMLReporter implements ITestListener, IReporter {

    private static final String SCREENSHOT_DIRECTORY = "screenshots";  // Directory to store screenshots
    private static final String OWNER_NAME = "pvenkatarajan@republicfinance.com"; 

    @Override
    public void onTestStart(ITestResult result) {
        // Capture screenshot at the start of the test
        captureScreenshot(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Retrieve any logs added using Reporter.log
        String logMessages = String.join("\n", Reporter.getOutput(result));

        // Append the success message to the result message
        String successMessage = "Assertion Success: Expected value matched actual value.\n" + logMessages;

        // Set a dummy throwable to include the success message in the JUnit report
        result.setThrowable(new Exception(successMessage));

        // Attach screenshot after test success
        attachScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Retrieve any logs added using Reporter.log
        String logMessages = String.join("\n", Reporter.getOutput(result));
        String originalMessage = (result.getThrowable() != null && result.getThrowable().getMessage() != null)
                ? result.getThrowable().getMessage()
                : "Test failed";

        String failureMessage = originalMessage + "\n" + logMessages;

        result.setThrowable(new Exception(failureMessage));

        // Attach screenshot after test failure
        attachScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Handle skipped tests if needed
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Handle failure within success percentage if needed
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        // Test execution starts
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        // Test execution finishes
    }

    private void captureScreenshot(ITestResult result) {
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        if (driver != null) {
            try {
                // Get screenshot directory
                String screenshotDirectory = getscreenshotDirectory();

                // Construct the screenshot file path
                String screenshotPath = Paths.get(screenshotDirectory, result.getName() + ".png").toString();
                File screenshotFile = new File(screenshotPath);

                // Capture the screenshot if it doesn't exist
                if (!screenshotFile.exists()) {
                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(screenshot, screenshotFile);
                }

                // Attach screenshot to the test result
                result.setAttribute("screenshot", screenshotFile.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to capture screenshot for test: " + result.getName());
            }
        } else {
            System.out.println("WebDriver not found for test: " + result.getName());
        }
    }

    private String getscreenshotDirectory() {
        String workingDir = System.getProperty("user.dir");
        String screenshotDirectory = Paths.get(workingDir, SCREENSHOT_DIRECTORY).toString();

        File screenshotDir = new File(screenshotDirectory);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs(); // Create if it doesn't exist
        }

        System.out.println("Screenshot Directory: " + screenshotDirectory);
        return screenshotDirectory;
    }

    private void attachScreenshot(ITestResult result) {
        Object screenshot = result.getAttribute("screenshot");
        if (screenshot != null) {
            String screenshotUrl = screenshot.toString();
            String message = "<a href=\"" + screenshotUrl + "\">Click here to view screenshot</a>";

            String resultMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "";
            resultMessage += "\n" + message;

            // Modify the result's message to include the screenshot link
            result.setThrowable(new Exception(resultMessage));
        }
    }

    /**
     * IReporter method: Runs after all tests have completed. This method modifies the JUnit XML report
     * to include the owner property.
     */
    //@Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        try {
            // Locate the JUnit XML report. Adjust the file name as needed.
            // For example, if your test suite name is "My Test Suite", TestNG might generate a file:
            // TEST-My_Test_Suite.xml or something similar. Update accordingly.
            File reportFile = findJUnitReportFile(outputDirectory);
            if (reportFile == null || !reportFile.exists()) {
                System.out.println("No JUnit report file found to update.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(reportFile);
            doc.getDocumentElement().normalize();

            // Get the <testsuite> element
            Element testSuite = (Element) doc.getElementsByTagName("testsuite").item(0);
            if (testSuite == null) {
                System.out.println("No <testsuite> element found in JUnit report.");
                return;
            }

            // Add <properties>
            Element properties = doc.createElement("properties");
            Element property = doc.createElement("property");
            property.setAttribute("name", "owner");
            property.setAttribute("value", OWNER_NAME);

            properties.appendChild(property);
            testSuite.appendChild(properties);

            // Write the updated document back to the file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(reportFile);
            transformer.transform(source, streamResult);

            System.out.println("Owner property added to JUnit XML report: " + OWNER_NAME);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to inject owner property into JUnit XML report.");
        }
    }

    /**
     * Helper method to find a JUnit report file (TEST-*.xml) in the output directory.
     */
    private File findJUnitReportFile(String outputDirectory) {
        File dir = new File(outputDirectory);
        if (!dir.exists() || !dir.isDirectory()) {
            return null;
        }

        // Look for files starting with TEST- and ending with .xml
        File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File d, String name) {
				return name.startsWith("TEST-") && name.endsWith(".xml");
			}
		});
        if (files != null && files.length > 0) {
            return files[0]; // Return the first match found
        }
        return null;
    }
}