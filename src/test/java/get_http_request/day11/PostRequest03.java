package get_http_request.day11;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {

    @Test
    public void test03(){

        spec04.pathParams("1","todos");
        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();

        JSONObject expectedRequest=testObje.setUpPostData();

        Response response=given().spec(spec04).contentType(ContentType.JSON).body(expectedRequest.toString())
                .when().post("/{1}");
        response.prettyPrint();


        response.then().assertThat().statusCode(expectedRequest.getInt("statusCode"))
                .body("userId", equalTo(expectedRequest.get("userID")),
                        "title",equalTo(expectedRequest.get("title")),
                        "completed",equalTo(expectedRequest.get("completed")) ,
                        "id",equalTo(expectedRequest.get("id")));

        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedRequest.get("id"),json.getInt("id"));
        Assert.assertEquals(expectedRequest.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequest.get("statuscode"),json.getInt("statuscode"));
        Assert.assertEquals(expectedRequest.get("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.get("completed"),json.getBoolean("completed"));

        HashMap<String,Object> actualData=response.as(HashMap.class);
        Assert.assertEquals(expectedRequest.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedRequest.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedRequest.get("statuscode"),actualData.get("statuscode"));
        Assert.assertEquals(expectedRequest.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedRequest.get("completed"),actualData.get("completed"));


    }

}
