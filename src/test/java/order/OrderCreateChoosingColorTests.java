package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.constants.Endpoints.SCOOTER_URL;
import static ru.yandex.praktikum.constants.OrderTestsData.*;
import static ru.yandex.praktikum.constants.OrderTestsData.ScooterColor.*;

@RunWith(Parameterized.class)
public class OrderCreateChoosingColorTests extends OrderActions {

    private final List<ScooterColor> color;

    public OrderCreateChoosingColorTests(List<ScooterColor> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][] {
                {List.of(BLACK)},
                {List.of(GREY)},
                {List.of(BLACK, GREY)},
                {null}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    @Test
    @DisplayName("Check if Create Order with different parameters of color returns 201 status code")
    public void createOrderDifferentColorsReturns201() {
        Response response = createOrder(FIRSTNAME, LASTNAME, ADDRESS, METROSTATION, PHONE, RENTTIME, DELIVERYDATE, COMMENT, color);
        int statusCode = response.getStatusCode();
        assertEquals("Ошибка: неверный статус-код", 201, statusCode);
    }

}
