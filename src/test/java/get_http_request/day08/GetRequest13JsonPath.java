package get_http_request.day08;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13JsonPath extends DummyBaseUrl {

    @Test
    public void test01() {
        spec02.pathParam("1", "employees");
        DummyTestData expectedData = new DummyTestData();
        HashMap<String, Object> expectedDataMap = expectedData.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response = given().accept("application/json").spec(spec02).when().get("/{1}");
        //response.prettyPrint();

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("ondorduncücalisan"), json.getString("data[13].employee_name"));
        Assert.assertEquals(expectedDataMap.get("calisansayisi"), json.getList("data.id").size());
        Assert.assertEquals(expectedDataMap.get("sondanucuncucalısanmaası"), json.getInt("data[-3].employee_salary"));
        Assert.assertTrue(json.getList("data.employee_age").containsAll((List)expectedDataMap.get("arananyaslar")));
        Assert.assertEquals(((Map)expectedDataMap.get("onuncucalisan")).get("id"),json.getInt("data[9].id"));


    }
}
