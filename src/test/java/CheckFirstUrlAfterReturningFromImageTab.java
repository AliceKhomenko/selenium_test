
import PageObject.GoogleMainPage;
import PageObject.GoogleSearchAllResults;
import PageObject.GoogleSearchImagesResults;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Search a string in Google, navigates to the Images Tab, returns to the All tab and checks first link before and after navigating
 */
public class CheckFirstUrlAfterReturningFromImageTab {


    private WebDriver driver;

    private String PATH_TO_CHROME_DRIVER;
    private String GOOGLE_URL = "http://google.com/ncr";
    private String LINK_FOR_COMPARE = "selenide.org/";
    private String STRING_FOR_SEARCH = "selenide";
    private String OS;
    private String BROWSER;
    Properties prop = new Properties();
    private String environment;


    @Before
    public void start() throws IOException {

        InputStream input = new FileInputStream("config.properties");
        prop.load(input);
        PATH_TO_CHROME_DRIVER = prop.getProperty("path.for.browser");
        GOOGLE_URL = prop.getProperty("web.searcher");
        LINK_FOR_COMPARE = prop.getProperty("link.for.compare");
        STRING_FOR_SEARCH = prop.getProperty("string.for.search");
        OS = prop.getProperty("os.environment");
        BROWSER = prop.getProperty("browser.environment");


    }


    @Test
    public void main() throws InterruptedException, IOException {
        System.out.println("Start test");
        switch (OS) {
            case "linux":
                System.setProperty("webdriver.chrome.driver", "drivers//chrome//linux//chromedriver");
                System.out.println("initialization of WebDriver");

                driver = new ChromeDriver();
                break;
            case "windows":
                System.setProperty("webdriver.chrome.driver", "drivers//chrome//windows//chromedriver.exe");
                System.out.println("initialization of WebDriver");
                driver = new ChromeDriver();
                break;
            case "macos":
                System.setProperty("webdriver.chrome.driver", "drivers//chrome//macos//chromedriver");
                System.out.println("initialization of WebDriver");

                driver = new ChromeDriver();
                break;
            default:
                System.out.println("Sorry, I haven't your configuration=(");
        }
        driver.get("https://cs8.pikabu.ru/post_img/big/2016/12/29/10/1483027518154632892.jpg");


        System.out.println("Maximize window");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        System.out.println("Open " + GOOGLE_URL);
        driver.get(GOOGLE_URL);
        System.out.println("Searching " + STRING_FOR_SEARCH);

        GoogleMainPage google = new GoogleMainPage(driver);

        google.searchInGoogle(STRING_FOR_SEARCH);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        GoogleSearchAllResults allResults = new GoogleSearchAllResults(driver);
        wait.until(ExpectedConditions.visibilityOf(allResults.allResultsBlock));


        List<WebElement> foundResultsList = allResults.resultLinksList;

        System.out.println("There are " + foundResultsList.size() + " results on the page:");

        String firstLink = foundResultsList.get(0).getText();

        Assert.assertEquals(LINK_FOR_COMPARE, firstLink);

        foundResultsList.clear();

        System.out.println("The first link is " + firstLink);
        System.out.println("Navigate to the Images tab");

        allResults.imagesTab.click();
        GoogleSearchImagesResults imagesResults = new GoogleSearchImagesResults(driver);
        wait.until(ExpectedConditions.visibilityOf(imagesResults.imagesResultsBlock));

        System.out.println("Return to the tab with All results");
        imagesResults.allTab.click();

        wait.until(ExpectedConditions.visibilityOf(allResults.allResultsBlock));

        GoogleSearchAllResults allResultsAfterNavigating = new GoogleSearchAllResults(driver);

        foundResultsList = allResultsAfterNavigating.resultLinksList;
        System.out.println("Comparing results before and after navigating");
        String firstLinkAfterNavigating = foundResultsList.get(0).getText();

        Assert.assertEquals(firstLink, firstLinkAfterNavigating);

        System.out.println("The link before navigating and link after navigating are equal");
        System.out.println("Finish the test");

    }

    @After
    public void close() {
        driver.quit();

    }

}
