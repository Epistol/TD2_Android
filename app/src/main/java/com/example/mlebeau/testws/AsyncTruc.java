package com.example.mlebeau.testws;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mlebeau on 21/03/2017.
 */

public class AsyncTruc extends AsyncTask<String, Integer, String > {

    private   OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... params) {

        String reponse = null;
        try {
            reponse = run(params[0]);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return reponse;

    }



    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}
