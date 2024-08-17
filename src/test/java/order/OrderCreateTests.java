package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static ru.yandex.praktikum.constants.OrderTestsData.*;
import static ru.yandex.praktikum.constants.Endpoints.*;
import static ru.yandex.praktikum.constants.OrderTestsData.ScooterColor.*;

public class OrderCreateTests extends OrderActions {

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    @Test
    @DisplayName("Check if Create Order returns 201 status code")
    public void createOrderReturns201() {
        Response response = createOrder(FIRSTNAME, LASTNAME, ADDRESS, METROSTATION, PHONE, RENTTIME, DELIVERYDATE, COMMENT, List.of(BLACK, GREY));
        int statusCode = response.getStatusCode();
        assertEquals("Ошибка: неверный статус-код", 201, statusCode);
    }

    @Test
    @DisplayName("Check if Create Order response body contains a track number")
    public void createOrderReturnsTrackNumber() {
        Response response = createOrder(FIRSTNAME, LASTNAME, ADDRESS, METROSTATION, PHONE, RENTTIME, DELIVERYDATE, COMMENT, List.of(BLACK, GREY));
        String track = response.jsonPath().getString("track");
        assertNotNull("Ошибка: отсутствует трек-номер", track);
    }

}
