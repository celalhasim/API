package get_http_request.day11;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ali Can",
    "salary":"2000",
    "age":"40",
}
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,

{
    "status": "success",
    "data": {
    "id":…
},
    "message": "Successfully! Record has been added."
}

olduğunu test edin
 */
    @Test
    public void test02(){
        spec02.pathParams("1", "api", "2","v1", "3","create");
        DummyTestData obje= new DummyTestData();
        //Request için
        HashMap<String , Object> requestBodyMap=obje.setUpRequestBody();
        //Expected Data için
        HashMap<String , Object> expectedDataMap=obje.setUpExpectedData();

        Response response=given().contentType(ContentType.JSON).spec(spec02)
                            .body(requestBodyMap)
                .when().post("/{1}/{2}/{3}");
        // requestBodyMap 'e toString yapmadık; POST işleminde Map kullandığımız gerek kalmadı.
        //De-Serialization

        HashMap<String, Object> actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));

        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("status"),json.getString("status"));
        Assert.assertEquals(expectedDataMap.get("message"),json.getString("message"));
    }
}
