package courier;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import ru.yandex.praktikum.classes.CourierClient;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.constants.Endpoints.*;

public class CourierActions {

    @Step
    protected Response createCourier(String login, String password, String name) {

        CourierClient courier = new CourierClient(login, password, name);

        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER);

    }

    @Step
    protected Response loginCourier(String login, String password) {

        CourierClient courier = new CourierClient(login, password);

        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER_LOGIN);
    }

    @Step
    protected void deleteTestCourier(String login, String password) {

        Response response = loginCourier(login, password);

        String courierId = response.jsonPath().getString("id");

        given()
                .when()
                .delete(COURIER + courierId);

    }

}
