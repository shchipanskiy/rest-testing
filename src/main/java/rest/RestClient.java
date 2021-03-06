package rest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {
    private int restCode;

    public JSONObject restGet(String str_url) throws JSONException {
        JSONObject jsonObj;
        String json_out = "";
        try {

            URL url = new URL(str_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            restCode = conn.getResponseCode();
            if (restCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;

            while ((output = br.readLine()) != null) {
                json_out += output;
            }
            conn.disconnect();

        } catch (Exception e){
            System.out.println(String.format("Exception: %s in GET", e.getClass()));
            //e.printStackTrace();
        }
        jsonObj = new JSONObject(json_out);
        return jsonObj;
    }

    public void restPost(String str_url, JSONObject jsonObj){
        try {
            URL url = new URL(str_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(jsonObj.toString());
            out.close();

            restCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            /*String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }*/
            br.close();
            conn.disconnect();

        } catch (Exception e){
            System.out.println(String.format("Exception: %s in POST", e.getClass()));
            //e.printStackTrace();
        }
    }

    public void restPut(String str_url, JSONObject jsonObj){
        try {
            URL url = new URL(str_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.setConnectTimeout(5000);
            //conn.setReadTimeout(5000);

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(jsonObj.toString());
            out.close();

            restCode = conn.getResponseCode();

            //BufferedReader br = new BufferedReader(new InputStreamReader(
             //       (conn.getInputStream())));

            /*String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }*/
           // br.close();
            conn.disconnect();

        } catch (Exception e){
            System.out.println(String.format("Exception: %s in PUT", e.getClass()));
            e.printStackTrace();
        }
    }

    public int getRestCode(){
        return this.restCode;
    }
}
