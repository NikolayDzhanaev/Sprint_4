package scooter_page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstStepOfOrderForm extends BasePage{
        public FirstStepOfOrderForm(WebDriver driver) {
                super(driver);
        }

        // локатор поля "Имя"
        private By nameFieldInput = By.xpath("//input[@placeholder='* Имя']");
        // локатор поля "Фамилия"
        private By surnameFieldInput = By.xpath("//input[@placeholder='* Фамилия']");
        // локатор поля "Адрес: куда привезти заказ"
        private By addressFieldInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
        // локатор поля "Станция метро"
        private By metroStationFieldInput = By.xpath("//input[@placeholder='* Станция метро']");
        // локатор поля "Телефон: на него позвонит курьер"
        private By phoneNumberFieldInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
        // локатор кнопки "Далее"
        private By continueButton = By.xpath("//button[text()='Далее']");

        public FirstStepOfOrderForm waitForElementNameFieldInput() {
                WebDriverWait wait = new WebDriverWait(driver, 20);
                wait.until(ExpectedConditions.visibilityOfElementLocated(nameFieldInput));
                return this;
        }
        public void enterNameField(String name) {
                driver.findElement(nameFieldInput).sendKeys(name);
        }
        public void enterSurnameField(String surname) {
                driver.findElement(surnameFieldInput).sendKeys(surname);
        }
        public void enterAddressFieldInput(String address) {
                driver.findElement(addressFieldInput).sendKeys(address);
        }
        public void enterMetroStationFieldInput(String metroStation) {
                driver.findElement(metroStationFieldInput).sendKeys(metroStation);
        }
        public void clickMetroStationFromTheList(String metroStation) {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(".//div[text()='%s']", metroStation))));
                driver.findElement(By.xpath(String.format(".//div[text()='%s']", metroStation))).click();
        }
        public void enterPhoneNumberFieldInput(String phoneNumber) {
                driver.findElement(phoneNumberFieldInput).sendKeys(phoneNumber);
        }
        public SecondStepOfOrderForm clickСontinueButton() {
                driver.findElement(continueButton).click();
                return new SecondStepOfOrderForm(driver);
        }
        public FirstStepOfOrderForm enterMakeOrderForm(String name, String surname, String address, String metroStation,
                                       String phoneNumber) {
                enterNameField(name);
                enterSurnameField(surname);
                enterAddressFieldInput(address);
                enterPhoneNumberFieldInput(phoneNumber);
                enterMetroStationFieldInput(metroStation);
                clickMetroStationFromTheList(metroStation);
                return this;
        }
}