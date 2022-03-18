package test_data;

import org.json.JSONObject;

import java.util.HashMap;

public class HerOkuAppTestData {
    /*
https://restful-booker.herokuapp.com/booking/47
      {
          "firstname": "Ali",
          "lastname": "Can",
          "totalprice": 500,
          "depositpaid": true,
          "bookingdates": {
              "checkin": "2022-02-01",
              "checkout": "2022-02-11"
         }
      }
1) JsonPhat
2) De-Serialization
*/
    public HashMap<String , Object> setUpTestData(){
        HashMap<String, Object> bookingdates= new HashMap<>();
        bookingdates.put("checkin","2020-08-14");
        bookingdates.put("checkout", "2021-11-14");

        HashMap<String, Object> expectedData=new HashMap<>();
        expectedData.put("firstname", "Susan");
        expectedData.put("lastname", "Ericsson");
        expectedData.put("totalprice", 119);
        expectedData.put("depositpaid", false);
        expectedData.put("bookingdates",bookingdates);

        return expectedData;
    }

    /*
   https://restful-booker.herokuapp.com/booking
   { "firstname": "Ali",
              "lastname": "Can",
              "totalprice": 500,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2022-03-01",
                  "checkout": "2022-03-11"
               }
               */
    public JSONObject setUpTestAndRequestData(){
        JSONObject bookingDates=new JSONObject();
        bookingDates.put("checkin", "2022-03-01");
        bookingDates.put("checkout","2022-03-11");
        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("firstname", "Ali");
        expectedRequest.put("lastname", "Can");
        expectedRequest.put("totalprice", 500);
        expectedRequest.put("depositpaid", true);
        expectedRequest.put("bookingdates", bookingDates);
        return expectedRequest;

    }

}



