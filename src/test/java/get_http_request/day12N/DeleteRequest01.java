package get_http_request.day12N;

import base_url.DummyBaseUrl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import static io.restassured.RestAssured.given;

public class DeleteRequest01 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test
edin
{
"status": "success",
"data": "2",
"message": "Successfully! Record has been deleted"
}
     */
    @Test
    public void test01(){
        spec02.pathParams("1", "delete", "2", 2);

        DummyTestData obje=new DummyTestData();
        JSONObject expectedData=obje.setUpDeleteExpectedData();

        Response response=given().contentType(ContentType.JSON).spec(spec02).auth().basic("admin", "password").
                when().delete("/{1}/{2}");

        response.prettyPrint();

        //jSONPATH

        JsonPath json=response.jsonPath();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.getString("status"), json.getString("status"));
        Assert.assertEquals(expectedData.getString("message"), json.getString("message"));
        Assert.assertEquals(expectedData.getString("data"), json.getString("data"));
    }
}
