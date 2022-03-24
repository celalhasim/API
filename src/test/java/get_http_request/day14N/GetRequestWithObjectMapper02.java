package get_http_request.day14N;

import base_url.HerOkuAppUrl;
import get_http_request.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper02 extends HerOkuAppUrl {
/*
https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
status kodun 200 ve response body’nin
{
"firstname": "Mark",
"lastname": "Wilson",
"totalprice": 284,
"depositpaid": false,
"bookingdates": {
    "checkin": "2016-08-10",
    "checkout": "2018-06-22"
}
}
Olduğunu Object Mapper kullanarak test edin
 */
    @Test
    public void test01(){
        spec05.pathParams("1", "booking", "2",2);

        String jsonData="{\n" +
                "    \"firstname\": \"Mark\",\n" +
                "    \"lastname\": \"Wilson\",\n" +
                "    \"totalprice\": 595,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-12-20\",\n" +
                "        \"checkout\": \"2019-08-20\"\n" +
                "    }\n" +
                "}";

        HashMap<String, Object> expectedData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        System.out.println("expectedData = " + expectedData);
        Response response=given().contentType(ContentType.JSON).spec(spec05).when().get("/{1}/{2}");
        //response.prettyPrint();

        HashMap<String, Object> actualData=JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        Assert.assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin") );
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout") );
    }
}
