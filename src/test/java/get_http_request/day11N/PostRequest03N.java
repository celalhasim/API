package get_http_request.day11N;

import base_url.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.http.ContentType;
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

public class PostRequest03N extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
}
"userId": 55,
"title": "Tidy your room",
"completed": false
}
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu
test edin
{
"userId": 55,
"title": "Tidy your room",
"completed": false,
"id": ...
}
     */

    @Test
    public void test01() {
        spec04.pathParam("1", "todos");
        JsonPlaceHolderTestData obje = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = obje.setUpPostData();

        Response response = given().contentType(ContentType.JSON).spec(spec04).
                auth().basic("admin", "password123").
                body(expectedRequest.toString()).when().post("/{1}");
        response.prettyPrint();


        //Matcher Class
        response.then().assertThat().statusCode(expectedRequest.getInt("statusCode"))
                .body("completed", equalTo(expectedRequest.getBoolean("completed")),
                       "title", equalTo(expectedRequest.getString("title")),
                        "userId", equalTo(expectedRequest.getInt("userId")));

        //JSONPATH

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedRequest.getInt("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"), json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"), json.getBoolean("completed"));


        //De Serialization

        HashMap<String , Object> actualDataMap=response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.getString("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"), actualDataMap.get("completed"));


    }

}
