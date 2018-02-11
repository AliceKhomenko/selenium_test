package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * Initializes Images Tab in Google search results page
 *
 */

public class GoogleSearchImagesResults {
    final private WebDriver driver;

    @FindBy(id="rg_s")
    public WebElement imagesResultsBlock;

    @FindBy(linkText = "All")
    public WebElement allTab;

    public GoogleSearchImagesResults(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

}
