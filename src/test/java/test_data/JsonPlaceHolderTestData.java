package test_data;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> setUpTestData(){

        Map<String, Object>expected = new HashMap<>();
        expected.put("completed",false);
        expected.put("statusCode",200);
        expected.put( "title","quis ut nam facilis et officia qui");
        expected.put( "userId",1 );
        expected.put( "via", "1.1 vegur");
        expected.put("Server", "cloudflare");
        return expected;
    }
/*
   https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
  {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": …
   }
*/
    public JSONObject setUpPostData() {

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("statusCode", 201);
        expectedRequest.put("userId",55);
        expectedRequest.put("title","Tidy your room");
        expectedRequest.put("completed",false);
    return expectedRequest;
    }

    public JSONObject setUpPutData(){

        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId", 21);
        expectedRequest.put("title", "Wash the dishes");
        expectedRequest.put("completed", false);

        return expectedRequest;
    }

}
