package get_http_request.day08;

import base_url.HerOkuAppUrl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends HerOkuAppUrl {

    /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
dönen response body nin
{
"firstname": "Susan",
"lastname": "Wilson",
"totalprice": 887,
"depositpaid": false,
"bookingdates": {
"checkin": "2019-02-12",
"checkout": "2022-01-09"
}
} gibi olduğunu test edin.
     */

    @Test
    public void test01(){
        spec05.pathParams("1", "booking", "2", "1");

        HerOkuAppTestData expectedObje=new HerOkuAppTestData();
        HashMap<String , Object> expectedDataMap=expectedObje.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);
        Response response=given().accept("application/json").spec(spec05).when().get("/{1}/{2}");
        response.prettyPrint();

        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("firstname"), json.getString("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), json.getString("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), json.getInt("tolaprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), json.getBoolean("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), json.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"), json.getString("bookingdates.checkout"));

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));
    }
}
