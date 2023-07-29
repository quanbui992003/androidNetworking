package com.example.lab2_androidnetworking.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2_androidnetworking.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity_lab6 extends AppCompatActivity {

    TextView textView1;
    Button button;
    String jsonString = "" ;
    Context context = this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab6);
        textView1 = findViewById(R.id.txt_lab6_view);
        button = findViewById(R.id.btn_idbut);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchDataFromAPI();
            }
        });


    }

    private void fetchDataFromAPI() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = "https://batdongsanabc.000webhostapp.com/mob403lab6/array_json_new.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {


                            for (int i = 0; i < response.length(); i++) {
                                JSONObject person=response.getJSONObject(i);
                                String id=person.getString("id");
                                String name=person.getString("name");
                                String email=person.getString("email");
                                JSONObject phone=person.getJSONObject("phone");
                                String mobile=phone.getString("mobile");
                                String home=phone.getString("home");
                                //Noi chuoi
                                jsonString += "id: "+id+"\n\n";
                                jsonString += "name: "+name+"\n\n";
                                jsonString += "email: "+email+"\n\n";
                                jsonString += "mobile: "+mobile+"\n\n";
                                jsonString += "home: "+home+"\n\n";
                            }

                            // Cập nhật dữ liệu lên TextView
                            textView1.setText(jsonString);
                            Log.e("aaa","aaa"+textView1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}

