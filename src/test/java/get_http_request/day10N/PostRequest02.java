package get_http_request.day10N;

import base_url.HerOkuAppUrl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerOkuAppUrl {
    /*
    https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
{ "firstname": "Selim",
"lastname": "Ak",
"totalprice": 11111,
"depositpaid": true,
"bookingdates": {
"checkin": "2020-09-09",
"checkout": "2020-09-21"
}
}gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,

"booking": {

"firstname": " Selim ",
"lastname": " Ak ",
"totalprice": 11111,
"depositpaid": true,
"bookingdates": {
"checkin": "2020-09-01",
"checkout": " 2020-09-21”
},
}
olduğunu test edin
     */

    @Test
    public void test01(){
        spec05.pathParam("1", "booking");

        //requestBody ve expectedData aynı olduğu için tek bir jsonObject kullanılması yeterlidir

        HerOkuAppTestData testData=new HerOkuAppTestData();

        JSONObject expectedRequestData= testData.setUpTestAndRequestDataNilufer();

        //request gönder

        Response response=given().contentType(ContentType.JSON).
                spec(spec05).
                auth().basic("admin", "password123").
                body(expectedRequestData.toString()).
                when().post("/{1}");

        //response.prettyPrint();

        HashMap<String , Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedRequestData.getString("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));


        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedRequestData.getString("firstname"), json.getString("booking.firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"), json.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"), json.getInt("booking.totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"), json.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"), json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"), json.getString("booking.bookingdates.checkout"));
    }
}
