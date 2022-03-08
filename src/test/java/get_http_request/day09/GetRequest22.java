package get_http_request.day09;

import base_url.HerOkuAppUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest22 extends HerOkuAppUrl {
      /*
https://restful-booker.herokuapp.com/booking/47
      {
          "firstname": "Ali",
          "lastname": "Can",
          "totalprice": 500,
          "depositpaid": true,
          "bookingdates": {
              "checkin": "2022-02-01",
              "checkout": "2022-02-11"
         }
      }
1) JsonPhat
2) De-Serialization
*/

    @Test
    public void test22(){
        //1) URL oluştur
        spec05.pathParams("1","booking", "2", 17);

        //2) expected data oluştur
        HerOkuAppTestData expectedObje=new HerOkuAppTestData();
        HashMap<String , Object> expectedTestDataMap=expectedObje.setUpTestData();
        System.out.println(expectedTestDataMap);

        //3) Reqouest ve Response
        Response response=given().spec(spec05).when().get("/{1}/{2}");
        response.prettyPrint();

        //4)) Doğrulama
        // De-Serialization, Json formatında gelen veriyi Javaya döndüreceğiz
        HashMap<String , Object> actualData=response.as(HashMap.class);


        Assert.assertEquals(expectedTestDataMap.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"), actualData.get("depositpaid"));

        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkout"),
                ((Map<?, ?>) actualData.get("bookingdates")).get("checkout"));


        // JSON PATH
        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedTestDataMap.get("firstname"), json.getString("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"),json.getString("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"),json.getInt("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"),json.getBoolean("depositpaid"));
        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkin"), json.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkout"), json.getString("bookingdates.checkout"));

    }
}
