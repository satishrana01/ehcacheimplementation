package com.ehcache.spring.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ApacheHttpClientGet {



        public static void main(String[] args) {

       putCache1(1,50);
      putCache2(1,10);
    }

        public static void putCache1(int start, int end) {

        try {

            for (int i = start; i <= end; i++) {
                DefaultHttpClient httpClient = new DefaultHttpClient();

                System.out.println("request for element: " + i);
                HttpGet getRequest = new HttpGet(
                        "http://localhost:8080/ehcacheServer/customers/" + i);
                getRequest.addHeader("accept", "application/json");

                HttpResponse response = httpClient.execute(getRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }

                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));
                httpClient.getConnectionManager().shutdown();
            }
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

        public static void putCache2(int start, int end) {

        try {

            for (int i = start; i <= end; i++) {
                DefaultHttpClient httpClient = new DefaultHttpClient();

                System.out.println("request for element: " + i);
                HttpGet getRequest = new HttpGet(
                        "http://localhost:8080/ehcacheServer/mycustomers/" + i);
                getRequest.addHeader("accept", "application/json");

                HttpResponse response = httpClient.execute(getRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }

                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

                httpClient.getConnectionManager().shutdown();
            }
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}
