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

public class CourierLoginTests extends CourierActions {

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    @Test
    @DisplayName("Check if Login Courier returns 200 status code")
    public void loginCourierReturns200() {
        createCourier(LOGIN, PASSWORD, NAME);
        Response loginResponse = loginCourier(LOGIN, PASSWORD);
        int statusCode = loginResponse.getStatusCode();
        assertEquals("Ошибка: неверный статус-код", 200, statusCode);
    }

    @Test
    @DisplayName("Check if login with non-existing user returns 404 status code")
    public void loginNonExistingCourierReturns404() {
        Response loginResponse = loginCourier(LOGIN, PASSWORD);
        int statusCode = loginResponse.getStatusCode();
        assertEquals("Ошибка: неверный статус-код", 404, statusCode);
    }

    @Test
    @DisplayName("Check if login with non-existing user returns correct message")
    public void loginNonExistingCourierReturnsCorrectMessage() {
        Response loginResponse = loginCourier(LOGIN, PASSWORD);
        String expected = "Учетная запись не найдена";
        String actual = loginResponse.jsonPath().getString("message");
        assertEquals("Ошибка: сообщение в теле ответа не соответствует ожидаемому", expected, actual);
    }

    @Test
    @DisplayName("Check if Login Courier with an empty required field returns correct message")
    public void loginCourierWithoutLoginReturnsCorrectMessage() {
        createCourier(LOGIN, PASSWORD, NAME);
        Response loginResponse = loginCourier("", PASSWORD);
        String expected = "Недостаточно данных для входа";
        String actual = loginResponse.jsonPath().getString("message");
        assertEquals("Ошибка: сообщение в теле ответа не соответствует ожидаемому", expected, actual);
    }

    @Test
    @DisplayName("Check if successful login returns id")
    public void successfulLoginReturnsId() {
        createCourier(LOGIN, PASSWORD, NAME);
        Response loginResponse = loginCourier(LOGIN, PASSWORD);
        String id = loginResponse.jsonPath().getString("id");
        assertNotNull("Ошибка: отсутствует id", id);
    }

    @After
    public void tearDown() {
        deleteTestCourier(LOGIN, PASSWORD);
    }

}
