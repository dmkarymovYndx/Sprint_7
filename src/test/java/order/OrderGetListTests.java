package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.classes.OrderServer;
import java.util.List;

import static ru.yandex.praktikum.constants.Endpoints.*;
import static org.junit.Assert.*;

public class OrderGetListTests extends OrderActions {

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    @Test
    @DisplayName("Check if Get Orders endpoint returns not empty orders list")
    public void checkIfOrdersListIsNotEmpty() {
        Response response = getOrderListNoParams();
        List<OrderServer> orders = response.jsonPath().getList("orders");
        assertFalse("Ошибка: пустой список заказов", orders.isEmpty());
    }
    
}
