package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.constants.CourierTestsData.*;
import static ru.yandex.praktikum.constants.Endpoints.*;

@RunWith(Parameterized.class)
public class CourierCreateEmptyFieldsTests extends CourierActions {

    private final String login;
    private final String password;

    public CourierCreateEmptyFieldsTests(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][] {
                {"", PASSWORD},
                {LOGIN, ""},
                {null, PASSWORD},
                {LOGIN, null},
                {"", ""},
                {null, null}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    @Test
    @DisplayName("Check if Create Courier with empty login or password returns 400 status code")
    public void createCourierWithEmptyFieldReturns400() {
        Response response = createCourier(login, password, NAME);
        int statusCode = response.getStatusCode();
        assertEquals("Ошибка: неверный статус-код", 400, statusCode);
    }
}
