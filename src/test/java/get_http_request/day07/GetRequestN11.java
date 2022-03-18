package get_http_request.day07;

import base_url.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestN11 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
Dönen response un
Status kodunun 200, dönen body de,
"completed": değerinin false
"title”: değerinin “quis ut nam facilis et officia qui”
"userId" sinin 1 ve header değerlerinden

"Via" değerinin “1.1 vegur” ve

"Server" değerinin “cloudflare” olduğunu test edin...
     */
    @Test
    public void test(){
        spec04.pathParams("1", "todos", "2", "2");

        HashMap<String , Object> expectedData=new HashMap<String , Object>();
        expectedData.put("statusCode",200);
        expectedData.put("via","1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        System.out.println("expectedData = " + expectedData);
        System.out.println("-------------------");
        Response response=given().
                accept("application/json").
                spec(spec04).
                when().
                get("/{1}/{2}");

        response.prettyPrint();

        response.then().assertThat().statusCode((int)expectedData.get("statusCode")).
                headers("via", equalTo(expectedData.get("via")),"Server",equalTo(expectedData.get("Server"))).
                body("userId", equalTo(expectedData.get("userId")), "title", equalTo(expectedData.get("title")), "completed", equalTo(expectedData.get("completed")));


        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedData.get("via"), response.getHeader("Via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), json.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), json.getBoolean("completed"));

        //Bu satır ile DE-SERİALİZATİON işlemini yaptık
        HashMap<String, Object> actualData=response.as(HashMap.class);
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        System.out.println("actualData = " + actualData);


    }
}
