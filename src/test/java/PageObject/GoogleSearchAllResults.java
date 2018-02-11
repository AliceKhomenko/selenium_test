package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 *
 *
 * Initializes All TAb in Google search results page
 *
 */

public class GoogleSearchAllResults {
final private WebDriver driver;

@FindBy(css="div#ires cite")
public List<WebElement> resultLinksList;

@FindBy(linkText = "Images")
public WebElement imagesTab;

@FindBy(className = "srg")
public WebElement allResultsBlock;



public GoogleSearchAllResults(WebDriver driver){
    this.driver=driver;
    PageFactory.initElements(driver, this);
}
}
