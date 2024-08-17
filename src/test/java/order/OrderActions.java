package order;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import ru.yandex.praktikum.classes.OrderClient;
import ru.yandex.praktikum.constants.OrderTestsData;

import java.util.List;

import static ru.yandex.praktikum.constants.Endpoints.*;
import static io.restassured.RestAssured.given;

public class OrderActions {

    @Step
    protected Response createOrder(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<OrderTestsData.ScooterColor> color) {

        OrderClient order = new OrderClient(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(ORDERS);
    }

    @Step
    protected Response getOrderListNoParams() {
        return (Response) given()
                .when()
                .get(ORDERS);
    }

}
