package resttest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import rest.RestClient;

public class Test1 extends Tests {
    /*
    *step1: GET info from URL and save as json
    *step2: change id and some values in  json info and do POST request
    *step3: GET new json info from URL use new id and compere it with previous info
     */
    private int ID = 11;
    @Test
    public void test1(){
        System.out.println("***Test1 Started***");
        JSONObject jsonObj2 = new JSONObject();
        try{
            jsonObj1 = restClient.restGet(URL+ID);
            jsonObj1.put("id", "888");
            jsonObj1.put("userId", "888");
            jsonObj1.put("title", "test125");
            restClient.restPost(URL, jsonObj1);
            jsonObj2 = restClient.restGet(URL + jsonObj1.get("id"));
            Assert.assertEquals(jsonObj1.toString(), jsonObj2.toString(), "Objects aren't equals!!!");
            System.out.println("Objects are equals!!!");
            System.out.println("Object1: " + jsonObj1);
            System.out.println("Object2: " + jsonObj2);;
        } catch (Exception e){
            System.out.println(String.format("Exception: %s in TEST1", e.getClass()));
            //e.printStackTrace();
        }
        System.out.println("***Test1 Completed***");
    }
}
