package scooter_page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondStepOfOrderForm extends BasePage{
    public SecondStepOfOrderForm(WebDriver driver) {
        super(driver);
    }

    //локатор поля "Когда привезти самокат"
    private By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор "Срок аренды"
    private By rentalPeriodChoosing = By.xpath(".//div[text()='* Срок аренды']");
    //локатор выпадающего списка поля "Срок аренды"
    private By rentalPeriodList = By.xpath(".//div[@aria-expanded='true']");
    //локатор чекбокса "черный жемчуг" блока "Цвет самоката"
    private By scooterColourBlack = By.xpath(".//input[@id='black']");
    //локатор чекбокса "серая безысходность" блока "Цвет самоката"
    private By scooterColourGrey = By.xpath(".//input[@id='grey']");
    //локатор поля "Комментарий для курьера"
    private By commentForCourierInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор кнопки "Заказать"
    private By makeOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //локатор кнопки "Да" при оформлении заказа
    private By orderConfirmButton = By.xpath(".//button[text()='Да']");
    //локатор успешного оформления заказа
    private By successfullyCompleted = By.xpath(".//div[text()='Заказ оформлен']");
    public SecondStepOfOrderForm waitForFirstElement() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput));
        return this;
    }
    public SecondStepOfOrderForm clickMakeOrderButton() {
        driver.findElement(makeOrderButton).click();
        return this;
    }
    public void enterDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
    }
    public void chooseRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalPeriodChoosing).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodList));
        driver.findElement(By.xpath(String.format(".//div[text()='%s']", rentalPeriod))).click();
    }
    public void chooseScooterColour(String colour) {
        if (colour.equals("чёрный жемчуг")) {
            driver.findElement(scooterColourBlack).click();
        } else if (colour.equals("серая безысходность")) {
            driver.findElement(scooterColourGrey).click();
        }
    }
    public void enterComment(String comment) {
        driver.findElement(commentForCourierInput).sendKeys(comment);
    }
    public SecondStepOfOrderForm clickConfirmButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmButton));
        driver.findElement(orderConfirmButton).click();
        return this;
    }
    public boolean isOrderSuccessfullyCompleted() {
        return driver.findElement(successfullyCompleted).isDisplayed();
    }
    public SecondStepOfOrderForm enterMakeOrderForm(String date, String rentalPeriod, String colour, String comment) {
        chooseRentalPeriod(rentalPeriod);
        chooseScooterColour(colour);
        enterComment(comment);
        enterDate(date);
        return this;
    }
}