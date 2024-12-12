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
import java.io.File;
import java.nio.file.Paths;

public class CustomJUnitReporter extends JUnitXMLReporter implements ITestListener {

    private static final String SCREENSHOT_DIRECTORY = "screenshots";  // Directory to store screenshots

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
        // Similar approach can be taken for failures
        String logMessages = String.join("\n", Reporter.getOutput(result));
        String failureMessage = result.getThrowable().getMessage() + "\n" + logMessages;

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
        // Handle failure within success percentage
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
        // String screenshotDirectory = Paths.get(System.getProperty("java.io.tmpdir"), SCREENSHOT_DIRECTORY).toString();
       String screenshotDirectory = Paths.get(workingDir, SCREENSHOT_DIRECTORY).toString();

        File screenshotDir = new File(screenshotDirectory);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs(); // Create if it doesn't exist
        }

     // System.out.println("Working Directory: " + workingDir);
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
}
