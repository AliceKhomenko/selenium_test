
import PageObject.GoogleMainPage;
import PageObject.GoogleSearchAllResults;
import PageObject.GoogleSearchImagesResults;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Search a string in Google, navigates to the Images Tab, returns to the All tab and checks first link before and after navigating
 */
public class CheckFirstUrlAfterReturningFromImageTab {


    private WebDriver driver;
    private static final String GOOGLE_URL = "http://google.com/ncr";
    private static final String LINK_FOR_COMPARE = "selenide.org/";
    private static final String STRING_FOR_SEARCH = "selenide";
    private static final String PATH_TO_CHROME_DRIVER = "chromedriver.exe";

    @Test
    public void main() throws InterruptedException {
        System.out.println("Start test");

        System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);

        System.out.println("initialization of WebDriver");

        driver = new ChromeDriver();

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

        for (int i = 0; i < foundResultsList.size(); i++) {
            System.out.println((i + 1) + ". " + foundResultsList.get(i).getText());
        }
        String firstLink = foundResultsList.get(0).getText();

        if (!firstLink.equals(LINK_FOR_COMPARE)) {
            Assert.fail("First link isn't " + LINK_FOR_COMPARE);
        }

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
        if (!firstLink.equals(firstLinkAfterNavigating)) {
            Assert.fail("The first link after navigation isn't equal previous result");
        }
        System.out.println("The link before navigating and link after navigating are equal");
        System.out.println("Finish the test");

    }

    @After
    public void close() {
        driver.quit();

    }

}
