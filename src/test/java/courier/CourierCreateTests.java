package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static ru.yandex.praktikum.constants.CourierTestsData.*;
import static ru.yandex.praktikum.constants.Endpoints.*;

public class CourierCreateTests extends CourierActions {

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    @Test
    @DisplayName("Check if courier can be created")
    public void createCourierLoginReturnsId() {
        Response response = createCourier(LOGIN, PASSWORD, NAME);
        int statusCode = response.getStatusCode();
        Response loginResponse = loginCourier(LOGIN, PASSWORD);
        String id = loginResponse.jsonPath().getString("id");
        assertEquals("Ошибка: неверный статус-код", 201, statusCode);
        assertNotNull("Ошибка: отсутствует id", id);
    }

    @Test
    @DisplayName("Check if Create Courier returns 201 status code")
    public void createCourierReturns201() {
        Response response = createCourier(LOGIN, PASSWORD, NAME);
        int statusCode = response.getStatusCode();
        assertEquals("Ошибка: неверный статус-код", 201, statusCode);
    }

    @Test
    @DisplayName("Check if Create Courier returns correct message")
    public void createCourierReturnsOkTrue() {
        Response response = createCourier(LOGIN, PASSWORD, NAME);
        boolean statusMessage = response.jsonPath().getBoolean("ok");
        assertTrue("Ошибка: сообщение в теле ответа не соответствует ожидаемому", statusMessage);
    }

    @Test
    @DisplayName("Check if Create Courier with existing login returns 409 status code")
    public void createExistingCourierReturns409() {
        createCourier(LOGIN, PASSWORD, NAME);
        Response responseIdentical = createCourier(LOGIN, PASSWORD, NAME);
        int statusCode = responseIdentical.getStatusCode();
        assertEquals("Ошибка: неверный статус-код", 409, statusCode);
    }

    @Test
    @DisplayName("Check if Create Courier with existing login returns correct message")
    public void createExistingCourierReturnsCorrectMessage() {
        createCourier(LOGIN, PASSWORD, NAME);
        Response responseIdentical = createCourier(LOGIN, PASSWORD, NAME);
        String expected = "Этот логин уже используется";
        String actual = responseIdentical.jsonPath().getString("message");
        assertEquals("Ошибка: сообщение в теле ответа не соответствует ожидаемому", expected, actual);
    }

    @Test
    @DisplayName("Check if Create Courier without name returns 201 status code")
    public void createCourierWithoutNameReturns201() {
        Response response = createCourier(LOGIN, PASSWORD, "");
        int statusCode = response.getStatusCode();
        assertEquals("Ошибка: неверный статус-код", 201, statusCode);
    }

    @Test
    @DisplayName("Check if Create Courier with one empty required field returns correct message")
    public void createCourierWithoutLoginReturnsCorrectMessage() {
        Response response = createCourier("", PASSWORD, NAME);
        String expected = "Недостаточно данных для создания учетной записи";
        String actual = response.jsonPath().getString("message");
        assertEquals("Ошибка: сообщение в теле ответа не соответствует ожидаемому", expected, actual);
    }

   @After
   public void cleanUp() {
        deleteTestCourier(LOGIN, PASSWORD);
   }

}
