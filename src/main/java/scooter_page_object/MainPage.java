package scooter_page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }
    public static final String URL = "https://qa-scooter.praktikum-services.ru";

    public MainPage open() {
        driver.get(URL);
        return this;
    }

    // локатор пункта "Сколько это стоит? И как оплатить?" в разделе "Вопросы о важном"
    private By howMuchItCost = By.xpath("//*[@aria-controls='accordion__panel-0']");
    // локатор пункта "Хочу сразу несколько самокатов! Так можно?" в разделе "Вопросы о важном"
    private By iNeedSomeScooters = By.xpath("//*[@aria-controls='accordion__panel-1']");
    // локатор пункта "Как рассчитывается время аренды?" в разделе "Вопросы о важном"
    private By howTotalTimeCalculates = By.xpath("//*[@aria-controls='accordion__panel-2']");
    // локатор пункта "Можно ли заказать самокат прямо на сегодня?"
    private By canIMakeOrderForToday = By.xpath("//*[@aria-controls='accordion__panel-3']");
    // локатор пункта "Можно ли продлить заказ или вернуть самокат раньше?"
    private By canIMakeMyOrderLongerOrGiveScooterBackEarlier = By.xpath("//*[@aria-controls='accordion__panel-4']");
    // локатор пункта "Вы привозите зарядку вместе с самокатом?"
    private By doYouBringChargerWithScooter = By.xpath("//*[@aria-controls='accordion__panel-5']");
    // локатор пункта "Можно ли отменить заказ?"
    private By canICancelTheOrder = By.xpath("//*[@aria-controls='accordion__panel-6']");
    // локатор пункта "Я жизу за МКАДом, привезёте?"
    private By iLiveOutFromMKAD =By.xpath("//*[@aria-controls='accordion__panel-7]");
    // локатор кнопки "Заказать" вверху страницы
    private By makeOrderButtonTop = By.xpath("//button[@class='Button_Button__ra12g']");
    // локатор кнопки "Заказать" внизу страницы
    private By makeOrderButtonFooter = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // локатор кнопки согласия на куки
    private By cookieAccept = By.xpath("//*[@id='rcc-confirm-button']");

    public MainPage waitForQuestionElement(String questionNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//*[@aria-controls='accordion__panel-%s']", questionNumber))));
        return this;
    }
    public MainPage scrollToElement(String questionNumber) {
        WebElement element = driver.findElement(By.xpath(String.format("//*[@aria-controls='accordion__panel-%s']", questionNumber)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }
    public MainPage clickFaqQuestion(String questionNumber) {
        driver.findElement(By.xpath(String.format("//*[@aria-controls='accordion__panel-%s']", questionNumber))).click();
        return this;
    }
    public MainPage waitForAnswerElement(String questionAndAnswerNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[@id='accordion__panel-%s']", questionAndAnswerNumber))));
        return this;
    }
    public String getAnswer(String questionAndAnswerNumber) {
       String actualText = driver.findElement(By.xpath(String.format("//*[@id='accordion__panel-%s']", questionAndAnswerNumber))).getText();
       return actualText;
    }
    public MainPage cookieAccept() {
        if (driver.findElement(cookieAccept).isDisplayed()) {
            driver.findElement(cookieAccept).click();
            return this;
        }
        return this;
    }
    public MainPage waitForElementOrderButton(String button) {
        if (button.equals("Кнопка 'Войти' сверху")) {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderButtonTop));
        } else if (button.equals("Кнопка 'Войти' снизу")) {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderButtonFooter));
        }
        return this;
    }
    public FirstStepOfOrderForm clickMakeOrderButton(String button) {
        if (button.equals("Кнопка 'Войти' сверху")) {
            driver.findElement(makeOrderButtonTop).click();
        } else if (button.equals("Кнопка 'Войти' снизу")) {
            driver.findElement(makeOrderButtonFooter).click();
        }
        return new FirstStepOfOrderForm(driver);
    }
}

