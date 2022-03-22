package get_http_request.day12N;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
{

"title": "API calismaliyim"

}
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test
edin
{
"userId": 10,
"title": "API calismaliyim"
"completed": true,
"id": 198
}
     */
    @Test
    public void test01(){
        spec04.pathParams("1", "todos", "2", 198);

        JsonPlaceHolderTestData obje=new JsonPlaceHolderTestData();
        JSONObject requestData=obje.setUpPatchRequestData();
        JSONObject expectedData=obje.setUpPatchExpectedData();
        Response response=given().contentType(ContentType.JSON).auth().basic("admin", "password123").
                spec(spec04).body(requestData.toString()).when().patch("/{1}/{2}");

        response.prettyPrint();

        //JsonPath

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedData.getInt("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedData.getString("title"), json.getString("title"));
        Assert.assertEquals(expectedData.getBoolean("completed"), json.getBoolean("completed"));
        Assert.assertEquals(expectedData.getInt("id"), json.getInt("id"));

    }

}
