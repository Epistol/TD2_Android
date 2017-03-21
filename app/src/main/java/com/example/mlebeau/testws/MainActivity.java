package com.example.mlebeau.testws;

import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ajouterTruc();
            }


        });



        AsyncTruc asyncTruc = new AsyncTruc(){
            @Override
            protected void onPostExecute(String s){
                traiterRetourAsync(s);
            }
        };
        asyncTruc.execute("http://10380.sio.jbdelasalle.com/~amedassi/testws/index.php?uc=getTrucs");



    }




    private void traiterRetourAsync(String s) {
        ArrayList<Truc> lesTrucs = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i=0; i< jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Truc truc = new Truc(jsonObject);
                lesTrucs.add(truc);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Le serveur n'est pas du matin. 4h04.", Toast.LENGTH_SHORT);
        }
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<Truc>(this, android.R.layout.simple_expandable_list_item_1, lesTrucs));


    }

    public void ajouterTruc(){
        String lib = ((EditText)findViewById(R.id.editText)).getText().toString();
        AsyncTruc asyncTruc = new AsyncTruc(){
            @Override
            protected void onPostExecute(String s) {
                traiterRetourAsyncAdd(s);
            }
        };
        asyncTruc.execute("http://10380.sio.jbdelasalle.com/~amedassi/testws/index.php?uc=addTruc&libTruc"+lib);
    }

    private void traiterRetourAsyncAdd(String s){

    }


}
