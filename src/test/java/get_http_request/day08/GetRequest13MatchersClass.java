package get_http_request.day08;

import base_url.DummyBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest13MatchersClass extends DummyBaseUrl {
    @Test
    public void test01() {
        spec02.pathParam("1", "employees");
        DummyTestData expectedData = new DummyTestData();
        HashMap<String, Object> expectedDataMap = expectedData.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response = given().accept("application/json").spec(spec02).when().get("/{1}");
        //response.prettyPrint();

        response.then().assertThat().statusCode((Integer)expectedDataMap.get("statusCode")).
                body("data[13].employee_name", equalTo(expectedDataMap.get("ondorduncücalisan")),
                        "data.id",hasSize((Integer) expectedDataMap.get("calisansayisi")),
                        "data[-3].employee_salary", equalTo(expectedDataMap.get("sondanucuncucalısanmaası")),
                        "data.employee_age", hasItems(((List)expectedDataMap.get("arananyaslar")).get(0),
                                            ((List<?>)expectedDataMap.get("arananyaslar")).get(1),
                                            ((List<?>)expectedDataMap.get("arananyaslar")).get(2)),
                        "data[9].employee_name", equalTo(((Map)expectedDataMap.get("onuncucalisan")).get("employee_name")));

    }
}