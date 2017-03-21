package com.example.mlebeau.testws;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mlebeau on 21/03/2017.
 */

public class Truc {
    private int idTruc;
    private String libTruc;


    public Truc(JSONObject jsonObject) {
        try {
            this.idTruc = jsonObject.getInt("idTruc");
            this.libTruc = jsonObject.getString("libTruc");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
