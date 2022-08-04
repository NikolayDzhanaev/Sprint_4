package scooter_test;

import scooter_page_object.MainPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MakeOrderTest extends BaseUITest {

    private final String button;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;

    public MakeOrderTest(String button, String name, String surname, String address, String metroStation, String phoneNumber, String date, String rentalPeriod, String colour, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] arguments() {
        return new Object[][] {
                { "Кнопка 'Войти' сверху", "Бобби", "Фишер", "Москва", "Марьино", "89991112234", "12.08.2022", "сутки", "чёрный жемчуг", "тут коммент 1"},
                { "Кнопка 'Войти' снизу", "Николай", "Джанаев", "Москва", "Парк культуры", "89991112233", "11.08.2022", "двое суток", "серая безысходность", "тут коммент 2"}
        };
    }

    @Test
    public void makeOrderTest() {

        boolean isOrderSuccessfullyCompleted = new MainPage(driver)
                .open()
                .waitForElementOrderButton(button)
                .cookieAccept()
                .clickMakeOrderButton(button)
                .waitForElementNameFieldInput()
                .enterMakeOrderForm(name, surname, address, metroStation, phoneNumber)
                .clickСontinueButton()
                .waitForFirstElement()
                .enterMakeOrderForm(date, rentalPeriod, colour, comment)
                .clickMakeOrderButton()
                .clickConfirmButton()
                .isOrderSuccessfullyCompleted();

        assertTrue("Нет сообщения об успешном оформлении заказа", isOrderSuccessfullyCompleted);
    }
}