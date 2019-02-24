package PageObject;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Initializes All TAb in Google search results page
 */

public class GoogleSearchAllResultsPage {
    final private WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "div#ires cite")
    public List<WebElement> resultLinksList;

    @FindBy(linkText = "Images")
    public WebElement imagesTab;

    @FindBy(className = "srg")
    public WebElement allResultsBlock;


    public GoogleSearchAllResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void resultsCount() {
        wait.until(ExpectedConditions.visibilityOf(allResultsBlock));
        System.out.println("There are " + resultLinksList.size() + " results on the page:");

    }

    public void checkFirstLink(String expectedLink) {
        String firstLink = resultLinksList.get(0).getText();
        System.out.println("The first link is " + firstLink);
        Assert.assertEquals(expectedLink, firstLink);
    }

    public void navigateToImageTab() {

        System.out.println("Navigate to the Images tab");
        imagesTab.click();
    }
}
