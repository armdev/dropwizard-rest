package com.project.dropwizard.client;

import java.io.IOException;
import java.io.Serializable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

public class ClientPost implements Serializable {

    private static final long serialVersionUID = 1L;

    public ClientPost() {

    }

    public void saveContact() {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost request = new HttpPost("http://localhost:8080/contact");
            JSONObject json = new JSONObject();
            //json.put("id", "12");
            json.put("firstName", "Anna");
            json.put("lastName", "Paulina");
            json.put("phone", "061 21 22 33");

            StringEntity params = new StringEntity(json.toString());
            //  System.out.println("sending json:=> " + json);
            request.addHeader("content-type", "application/json");
            request.addHeader("charset", "utf8");
            request.setEntity(params);
            HttpResponse response = (HttpResponse) httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            System.out.println("____________________________ ");
            System.out.println("Returned@@@@@@@@@@@@@@ " + EntityUtils.toString(entity));
            System.out.println("____________________________ ");
            //System.out.println("Returned@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + entity);
        } catch (IOException | ParseException ex) {
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public static void main(String args[]) {
        ClientPost test = new ClientPost();
        test.saveContact();

    }
}
