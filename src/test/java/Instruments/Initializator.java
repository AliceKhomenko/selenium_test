package Instruments;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Initializator {
    private String browser;
    private String os;
    protected Properties prop = new Properties();
    protected WebDriver driver;
    protected WebDriverWait wait;

    private void osDetector() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();



        if (os.contains("win"))
            this.os = "windows";
        else if (os.contains("nux") || os.contains("nix") || (os.contains("centos")) || (os.contains("ubuntu")))
            this.os = "linux";
        else {
            System.out.println("Sorry, your OS is unsupported");
            Assert.fail();
        }

        System.out.println("Current OS: " + this.os);
    }

    protected void initializeBrowser(String browser) throws IOException {
        InputStream input;
        input = new FileInputStream("config.properties");
        prop.load(input);


        osDetector();

        this.browser = browser;
        System.out.println("Initializing Browser: " + browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                initChrome();
                break;
            case "firefox":
            case "mozilla":
            case "mozilla firefox":
                initFirefox();
                break;
            default:
                System.out.println("Sorry, selected browser is unsupported");
                Assert.fail();

        }


    }

    private void initFirefox() {
        System.setProperty("webdriver.gecko.driver", prop.getProperty(os + ".firefox.driver.path"));
        driver = new FirefoxDriver();
        ;
        driver.manage().window().setSize(new Dimension(480, 620));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    private void initChrome() {
        System.setProperty("webdriver.chrome.driver", prop.getProperty(os + ".chrome.driver.path"));
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
        options.addArguments("window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    protected void openUrl(String url) {
        wait = new WebDriverWait(driver, 10);

        System.out.println("Open " + url);
        driver.get(url);


    }

    @After
    public void close() {
        System.out.println("Finish the test");
        driver.quit();

    }
}
