package get_http_request.day10N;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
"name":"Ahmet Aksoy",
"salary":"1000",
"age":"18",
"profile_image": ""
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
{
"status": "success",
"data": {
“id”:...
},
"message": "Successfully! Record has been added."
}
olduğunu test edin
     */

    @Test
    public void test01(){
        spec02.pathParam("1", "create");

        DummyTestData obje=new DummyTestData();
        //post request yaparken bir body göndermeliyiz. Testdata clasında oluşturduğumu datayı buraya çağırıyoruz.
        HashMap<String , String> requestBodyMap= obje.setUpRequestBodyNilufer();// göndereceğim data
        HashMap<String, Object> expectedDataMap= obje.setupExpectedDataNilüfer();// bana dönecek olan bilgi

        Response response=given().accept("application/json").
                spec(spec02).
                auth().basic("admin", "password123").
                body(requestBodyMap).
                when().
                post("/{1}");
        response.prettyPrint();

        HashMap<String, Object> actualDataMap=response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));

        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("status"), json.getString("status"));
        Assert.assertEquals(expectedDataMap.get("message"), json.getString("message"));

    }
}
