package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Initializes Images Tab in Google search results page
 */

public class GoogleSearchImagesResults {
    final private WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "rg_s")
    public WebElement imagesResultsBlock;

    @FindBy(linkText = "All")
    public WebElement allTab;

    public GoogleSearchImagesResults(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,10);
    }

    public void navigateToAllResultsPage(){
        wait.until(ExpectedConditions.visibilityOf(imagesResultsBlock));
        System.out.println("Return to the tab with All results");
        allTab.click();

    }

}
