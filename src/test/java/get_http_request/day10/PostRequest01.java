package get_http_request.day10;

import base_url.HerOkuAppUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends HerOkuAppUrl {
     /*
   https://restful-booker.herokuapp.com/booking
   { "firstname": "Ali",
              "lastname": "Can",
              "totalprice": 500,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2022-03-01",
                  "checkout": "2022-03-11"
               }
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
}
   "booking": {
       "firstname": "Ali",
       "lastname": "Can",
       "totalprice": 500,
       "depositpaid": true,
       "bookingdates": {
                           "checkin": "2022-03-01",
                            "checkout": "2022-03-11"
       }
   }
}
olduğunu test edin
    */

    @Test
    public void test01(){

        //1)) URL oluştur
        spec05.pathParam("1", "booking");
        //2))Expected Data Oluştur
        HerOkuAppTestData testData =new HerOkuAppTestData();
        JSONObject expectedRequestData= testData.setUpTestAndRequestData();
        System.out.println("expectedRequestData = " + expectedRequestData);

        //3)) Request ve Response
        Response response=given().contentType(ContentType.JSON).
                auth().
                basic("admin", "password123").
                spec(spec05).body(expectedRequestData.toString()).when().post("/{1}");
        response.prettyPrint();

        //4) Doğrulama
        //JSONPATH
        JsonPath json=response.jsonPath();// burada responsın içini jsonpath'e dönüştürüyoruz.
        response.then().assertThat().statusCode(200);

                            //TEST DATA İCERİSİNDEKİ firstname              Response ile bize gelen bilgiden alıyoruz.
        Assert.assertEquals(expectedRequestData.getString("firstname"), json.getString("booking.firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"), json.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"), json.getInt("booking.totalprice"));
        //Assert.assertEquals(expectedRequestData.getBoolean("depositedpaid"), json.getBoolean("booking.depositedpaid"));

       Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
               json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                json.getString("booking.bookingdates.checkout"));
    }

}
