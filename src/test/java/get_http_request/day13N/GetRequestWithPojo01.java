package get_http_request.day13N;

import base_url.DummyBaseUrl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Data;
import pojos.DummyPojo;

import static io.restassured.RestAssured.given;

public class GetRequestWithPojo01 extends DummyBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employee/1 url ‘ine bir get request
gönderildiğinde , dönen response ‘un,
Status kodunun 200 ve response body’nin
{
"status": "success",
"data": {
"id": 1,

"employee_name": "Tiger Nixon",
"employee_salary": 320800,
"employee_age": 61,
"profile_image": ""

},
"message": "Successfully! Record has been fetched."
}
Olduğunu test edin
     */

    @Test
    public void test01(){
        spec02.pathParams("1", "employee","2",1);
        Data data=new Data(1, "Tiger Nixon", 320800, 61,"");

        DummyPojo expectedData=new DummyPojo("success", data,"Successfully! Record has been fetched.");
        Response response=given().contentType(ContentType.JSON).spec(spec02).
                when().get("/{1}/{2}");
        response.prettyPrint();

        DummyPojo actualData=response.as(DummyPojo.class);

        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.getStatus(), actualData.getStatus());
        Assert.assertEquals(expectedData.getData().getemployee_name(), actualData.getData().getemployee_name());
        Assert.assertEquals(expectedData.getData().getId(), actualData.getData().getId());
        Assert.assertEquals(expectedData.getData().getemployee_age(), actualData.getData().getemployee_age());
        Assert.assertEquals(expectedData.getData().getemployee_salary(), actualData.getData().getemployee_salary());
        Assert.assertEquals(expectedData.getData().getprofile_image(), actualData.getData().getprofile_image());
        Assert.assertEquals(expectedData.getMessage(), actualData.getMessage());

        //Gson classdan obje oluşturuyoruz
        Gson gson=new Gson();
        String jsonFromJava=gson.toJson(actualData);
        System.out.println("jsonFromJava = " + jsonFromJava);
    }

}
