package get_http_request.day08;

import base_url.DummyBaseUrl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyBaseUrl {
/*http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.*/


    @Test
    public void test01(){
        spec02.pathParam("1", "employees");

        DummyTestData expectedObje=new DummyTestData();
        HashMap<String, Integer> expectedDataMap=expectedObje.setUpTestData02();
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response=given().accept("application/json").spec(spec02).when().get("/{1}");
       // response.prettyPrint();

        HashMap<String , Object> actualDataMap=response.as(HashMap.class);

        Assert.assertEquals(expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());
        List<Integer> maasListesi=new ArrayList<>();
        int dataSize=((List) actualDataMap.get("data")).size();
        for (int i = 0; i < dataSize; i++) {

           maasListesi.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(maasListesi);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasListesi.get(dataSize-1));
        Assert.assertEquals(expectedDataMap.get("ikinciYuksekMaas"), maasListesi.get(dataSize-2));

        List<Integer> yasListesi=new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            yasListesi.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        Collections.sort(yasListesi);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"), yasListesi.get(0));

        JsonPath json=response.jsonPath();
        List<Integer> maasListesiJson=json.getList("data.employee_salary");
        Collections.sort(maasListesiJson);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"), maasListesi.get(maasListesiJson.size()-1));
        Assert.assertEquals(expectedDataMap.get("ikinciYuksekMaas"), maasListesi.get(maasListesiJson.size()-2));
        List<Integer> yasListesiJson=json.getList("data.employee_age");
        Collections.sort(yasListesiJson);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"), yasListesi.get(0));
    }
}
