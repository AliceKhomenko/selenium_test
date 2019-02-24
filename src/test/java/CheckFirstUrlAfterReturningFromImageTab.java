
import Instruments.Initializator;
import Instruments.Screenshotter;
import PageObject.GoogleMainPage;

import PageObject.GoogleSearchAllResultsPage;
import PageObject.GoogleSearchImagesResults;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;


/**
 * Search a string in Google, navigates to the Images Tab, returns to the All tab and checks first link before and after navigating
 */
public class CheckFirstUrlAfterReturningFromImageTab extends Initializator {

    private String GOOGLE_URL;
    private String LINK_FOR_COMPARE;
    private String STRING_FOR_SEARCH;
    Screenshotter screen;


    @Before
    public void start() throws IOException {
        initializeBrowser("chrome");

        GOOGLE_URL = prop.getProperty("web.searcher");
        LINK_FOR_COMPARE = prop.getProperty("link.for.compare");
        STRING_FOR_SEARCH = prop.getProperty("string.for.search");

        screen = new Screenshotter(driver);
        screen.deleteOldScreenshots(this.getClass().getSimpleName());

    }


    @Test
    public void main() throws InterruptedException, IOException {

        try {
            openUrl(GOOGLE_URL);

            GoogleMainPage google = new GoogleMainPage(driver);

            google.searchInGoogle(STRING_FOR_SEARCH);

            GoogleSearchAllResultsPage allResults = new GoogleSearchAllResultsPage(driver);
            allResults.resultsCount();

            allResults.checkFirstLink(LINK_FOR_COMPARE);

            allResults.navigateToImageTab();

            GoogleSearchImagesResults imagesResults = new GoogleSearchImagesResults(driver);
            imagesResults.navigateToAllResultsPage();

            GoogleSearchAllResultsPage allResultsAfterNavigating = new GoogleSearchAllResultsPage(driver);

            allResultsAfterNavigating.checkFirstLink(LINK_FOR_COMPARE);
        } catch (Exception e) {

            screen.getScreenshot();
            System.out.println(e);
            Assert.fail();


        }

    }

}
