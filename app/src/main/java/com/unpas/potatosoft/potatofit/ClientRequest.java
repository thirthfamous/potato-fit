package com.unpas.potatosoft.potatofit;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by fhblu on 4/8/2018.
 */

public class ClientRequest {

        public static String SendDataForLogin(String url, String input){
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

                // add header
                post.setHeader("User-Agent", "Mozilla/5.0");
                StringEntity se = new StringEntity( input.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                post.setEntity(se);
                HttpResponse response = null;

                try {
                    response = client.execute(post);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("send","\nSending 'POST' request to URL : " + url);
                Log.e("params","Post parameters : " + post.getEntity().toString());
                Log.e("code","Response Code : " +
                        response.getStatusLine().getStatusCode());

                BufferedReader rd = null;
                try {
                    rd = new BufferedReader(
                            new InputStreamReader(response.getEntity().getContent()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                StringBuffer result = new StringBuffer();
                String line = "";
                try {
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e("result",result.toString());
                return result.toString();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
    }
}
