package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class RubberDucksTest extends BaseTest {

    @Test
    public void countDucksTest() {
        rubberDucksPage.goToRubberDucks();
        Assert.assertEquals(
                rubberDucksPage.getDucksQuantity(),
                rubberDucksPage.expectedDucksQuantity
        );
        System.out.println(
                "Ducks found on the page: " +
                        rubberDucksPage.getDucksQuantity()
        );
    }

    @Test
    public void getYellowDuckPrice() {
        rubberDucksPage.goToRubberDucks();
        rubberDucksPage.clickDateButton();
        Assert.assertEquals(
                $(rubberDucksPage.yellowDuckPriceFilteredByDate).getText(),
                rubberDucksPage.expectedYellowDuckPriceByDate
        );
        System.out.println(
                "Yellow duck price sorted by Date: " +
                        $(rubberDucksPage.yellowDuckPriceFilteredByDate).getText()
        );
    }

    @Test
    public void getBlueDuckPrice() {
        rubberDucksPage.goToRubberDucks();
        rubberDucksPage.clickNameButton();
        Assert.assertEquals(
                $(rubberDucksPage.blueDuckPriceFilteredByName).getText(),
                rubberDucksPage.expectedBlueDuckPriceByName
        );
        System.out.println(
                "Blue duck price sorted by Name: " +
                        $(rubberDucksPage.blueDuckPriceFilteredByName).getText()
        );
    }

    @Test
    public void descriptionOfYellowDuckTest() {
        rubberDucksPage.goToRubberDucks();
        String yellowDuckDescription = rubberDucksPage.getYellowDuckDescriptionOnSubcategory();
        Assert.assertEquals(
                yellowDuckDescription, rubberDucksPage.expectedDescriptionOfYellowDuck
        );
        System.out.println(
                "Description of yellow duck is:\n" +
                        rubberDucksPage.expectedDescriptionOfYellowDuck
        );
    }

    @Test
    public void searchDuckPrice() {
        rubberDucksPage.goToRubberDucks();
        rubberDucksPage.getDuckPriceViaSearchField();
        Assert.assertEquals(
                $(rubberDucksPage.actualResultDuckPrice).getText(),
                rubberDucksPage.expectedResultDuckPriceAfterSearch
        );
        System.out.printf(
                "Price of a %s = %s", rubberDucksPage.searchText,
                rubberDucksPage.expectedResultDuckPriceAfterSearch
        );
    }

    @Test
    public void checkDucksNames() {
        rubberDucksPage.verifyDucksNames();
    }

}
