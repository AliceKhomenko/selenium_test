package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Initializes  Google search page
 */

public class GoogleMainPage {
    final private WebDriver driver;

    @FindBy(name = "q")
    public WebElement searchGoogleField;

    public GoogleMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Inputs string for search in Google search field
     */

    public void searchInGoogle(String stringForSearch) {
        System.out.println("Searching: " + stringForSearch);
        searchGoogleField.sendKeys(stringForSearch);
        searchGoogleField.sendKeys(Keys.ENTER);


    }
}
