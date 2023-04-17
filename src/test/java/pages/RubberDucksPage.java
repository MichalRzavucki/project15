package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RubberDucksPage extends BasePage {
    static RubberDucksPage rubberDucksPage = null;
    private final By RUBBER_DUCKS_LINK = By.xpath("//nav[@id='site-menu']/ul/li[@class='category-1']/a");
    private final By SUBCATEGORY_LINK = By.xpath("//ul[@class='list-vertical']//a[contains(text(),'Subcategory')]");
    private final By DATE_BUTTON = By.cssSelector("nav.filter :last-child");
    private final By NAME_BUTTON = By.cssSelector("nav.filter :first-child");
    private final By DUCK = By.cssSelector(".product.column.shadow.hover-light");
    public final By SEARCH_BUTTON = By.xpath("//*[@type='search']");
    public By descriptionOfYellowDuck = By.xpath("//*[@title='Yellow Duck']//*[@class='description']");
    public String expectedDescriptionOfYellowDuck = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue.";
    public By yellowDuckPriceFilteredByDate = By.xpath("//a[@title='Yellow Duck']/div/strong");
    public By blueDuckPriceFilteredByName = By.xpath("//a[@title='Blue Duck']/div/span");
    public int expectedDucksQuantity = 5;
    public String expectedYellowDuckPriceByDate = "$18";
    public String expectedBlueDuckPriceByName = "$20";
    public By duckName = By.xpath("//a//div[@class='name']");
    public String searchText = "Green Duck";
    public By actualResultDuckPrice = By.xpath("//div[@class='price-wrapper']/span");
    public String expectedResultDuckPriceAfterSearch = "$20";

    public RubberDucksPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static RubberDucksPage getInstance(WebDriver webDriver) {
        if (rubberDucksPage == null) {
            rubberDucksPage = new RubberDucksPage(webDriver);
        }
        return rubberDucksPage;
    }

    public void clickRubberDucksLink() {
        $(RUBBER_DUCKS_LINK).click();
    }

    public void clickDate() {
        $(DATE_BUTTON).click();
    }

    public void clickName() {
        $(NAME_BUTTON).click();
    }

    public int getDucksQuantity() {
        ElementsCollection ducksQuantity = $$(DUCK);
        return ducksQuantity.size();
    }

    public void checkRubberDucksLinkAppeared() {
        $(RUBBER_DUCKS_LINK).shouldBe(Condition.exist);
    }

    public void checkDateButtonAppeared() {
        $(DATE_BUTTON).shouldBe(Condition.exist);
    }

    public void checkNameButtonAppeared() {
        $(NAME_BUTTON).shouldBe(Condition.exist);
    }

    public void checkDuckAppeared() {
        $(DUCK).shouldBe(Condition.exist);
    }

    public void goToRubberDucks() {
        checkRubberDucksLinkAppeared();
        clickRubberDucksLink();
        checkDuckAppeared();
    }

    public void clickDateButton() {
        checkDateButtonAppeared();
        clickDate();
        checkDuckAppeared();
    }

    public void clickNameButton() {
        checkNameButtonAppeared();
        clickName();
        checkDuckAppeared();
    }

    public void verifyDucksNames() {
        rubberDucksPage.goToRubberDucks();
        ElementsCollection duckNames = $$(rubberDucksPage.duckName);
        List<String> actualDuckNames = duckNames.texts();
        List<String> expectedDuckNames = List.of("Purple Duck", "Yellow Duck", "Green Duck", "Red Duck", "Blue Duck");
        String currentDuckName = "";
        String tempExpectedDuckName = "";

        System.out.println("Current duck's names: ");
        for (int i = 0; i < actualDuckNames.size(); i++) {
            currentDuckName = actualDuckNames.get(i);
            tempExpectedDuckName = expectedDuckNames.get(i);
            Assert.assertEquals(currentDuckName, tempExpectedDuckName);
            System.out.print(currentDuckName + ", ");
        }

        System.out.println("\n");

        System.out.println("Expected duck's names: ");
        for (String expectedDuckName : expectedDuckNames) {
            System.out.print(expectedDuckName + ", ");
        }
    }

    public void getDuckPriceViaSearchField() {
        $(rubberDucksPage.SEARCH_BUTTON).sendKeys(rubberDucksPage.searchText);
        $(rubberDucksPage.SEARCH_BUTTON).pressEnter();
    }

    public String getYellowDuckDescriptionOnSubcategory() {
        $(SUBCATEGORY_LINK).click();
        return $(descriptionOfYellowDuck).getText();
    }

}
