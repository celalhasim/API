package get_http_request.day08;

import base_url.DummyBaseUrl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
5. Çalışan isminin "Airi Satou" olduğunu , çalışan sayısının 24 olduğunu,
Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
{
“id”:”11”
"employee_name": "Jena Gaines",
"employee_salary": "90560",
"employee_age": "30",
"profile_image": "" }
} gibi olduğunu test edin.
     */

    @Test
    public void test01(){
        spec02.pathParams("1","employees");
        DummyTestData expectedData=new DummyTestData();
        HashMap<String, Object> expectedDataMap=expectedData.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response=given().accept("application/json").spec(spec02).when().get("/{1}");
        //response.prettyPrint();
        HashMap<String , Object> actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("ondorduncücalisan"), ((Map)((List)actualDataMap.get("data")).get(13)).get("employee_name"));
        Assert.assertEquals(expectedDataMap.get("calisansayisi"), ((List)actualDataMap.get("data")).size());
        int datasize=((List)actualDataMap.get("data")).size();
        Assert.assertEquals(expectedDataMap.get("sondanucuncucalısanmaası"), ((Map)((List<?>)actualDataMap.get("data")).get(datasize-3)).get("employee_salary"));

        List<Integer> actualYas= new ArrayList<>();

        for (int i = 0; i < datasize; i++) {
            actualYas.add((Integer) (((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_age")));
        }

        Assert.assertTrue(actualYas.containsAll((List)(expectedDataMap.get("arananyaslar"))));

        Assert.assertEquals(((Map)expectedDataMap.get("onuncucalisan")).get("employee_name"), ((Map)((List<?>) actualDataMap.get("data")).get(9)).get("employee_name"));

    }

}
