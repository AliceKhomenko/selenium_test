package Instruments;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshotter {
    WebDriver driver;
    String className;
    String testName;


    public Screenshotter(WebDriver driver) {
        this.driver = driver;
    }


    public void getScreenshot() throws IOException {
        testName = Thread.currentThread().getName();


        File directory = new File("Screenshots/");
        if (!directory.exists())
            directory.mkdir();
        File classDirectory = new File("Screenshots/" + className + "/");
        if (!classDirectory.exists())
            classDirectory.mkdir();
        else
            for (File f : classDirectory.listFiles()) {
                if (f.getName().startsWith(testName))
                    f.delete();

            }

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);



        FileUtils.copyFile(screenshotFile, new File("Screenshots/"+className+"/" + testName + ".png"));

    }

    public void deleteOldScreenshots(String className) throws IOException {
        this.className=className;
        File classDirectory = new File("Screenshots/" + className + "/");
        if (classDirectory.exists())
            FileUtils.deleteDirectory(classDirectory);




    }


}
