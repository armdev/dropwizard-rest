package com.project.dropwizard.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dropwizard.model.Contact;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

public class ClientGet {

    public static void main(String[] args) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://localhost:8080/contact/" + 1);
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
         //   System.out.println("Returned@@@@@ " + EntityUtils.toString(entity));

           // System.out.println("Output from Server @@@@.... \n");
            // Contact cont = (Contact) entity;

            ObjectMapper mapper = new ObjectMapper();
            Contact cont = mapper.readValue((EntityUtils.toString(entity)), Contact.class);
            System.out.println("GET FIRSTNAME " + cont.getFirstName());
            System.out.println("GET LASTNAME " + cont.getLastName());
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
