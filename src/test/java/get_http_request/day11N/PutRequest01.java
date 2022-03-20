package get_http_request.day11N;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;

public class PutRequest01 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
{
"userId": 21,
"title": "Wash the dishes",
"completed": false
}
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test
edin
{
"userId": 21,
"title": "Wash the dishes",
"completed": false,
"id": 198
}
     */

    @Test
    public void test01(){
        spec04.pathParams("1", "todos", "2", "198");
        JsonPlaceHolderTestData obje=new JsonPlaceHolderTestData();
        JSONObject expectedData=obje.setUpPutData();
        Response response=given().contentType(ContentType.JSON).spec(spec04).auth().basic("admin", "password123").body(expectedData.toString()).
                            when().put("/{1}/{2}");

        response.prettyPrint();

        //JSONPATH

        JsonPath json=response.jsonPath();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.getBoolean("completed"), json.getBoolean("completed"));
        Assert.assertEquals(expectedData.getString("title"), json.getString("title"));
        Assert.assertEquals(expectedData.getInt("userId"), json.getInt("userId"));

    }
}
