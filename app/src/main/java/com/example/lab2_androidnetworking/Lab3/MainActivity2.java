package com.example.lab2_androidnetworking.Lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab2_androidnetworking.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity2 extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    Button button;
    TextView tvKQ;
    String path ="https://batdongsanabc.000webhostapp.com/mob403/demo2_api_get.php";
    String pathPost ="https://batdongsanabc.000webhostapp.com/mob403/demo2_api_post.php";

    String kq="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText1=findViewById(R.id.editTextTextPersonName);
        editText2=findViewById(R.id.editTextTextPersonName2);
        tvKQ=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      new GETAsyncTask().execute();
            //  new POSTAsyncTask().execute();
            }
        });



    }

     class POSTAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(pathPost);
//                String param="canh="+ URLEncoder.encode(editText1.getText().toString(),"utf-8");
                String param="canh="+ URLEncoder.encode(editText1.getText().toString(),"utf-8");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setFixedLengthStreamingMode(param.getBytes().length);

                urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                PrintWriter printWriter=new PrintWriter(urlConnection.getOutputStream());
                printWriter.print(param);
                printWriter.close();

                String line="";
                BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder=new StringBuilder();
                while ((line=br.readLine())!=null){
                    stringBuilder.append(line);
                }

                kq=stringBuilder.toString();
                Log.e("aa","ddd"+kq);
                urlConnection.disconnect();
            }catch (MalformedURLException e){
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

         @Override
         protected void onPostExecute(Void unused) {
             super.onPostExecute(unused);
             tvKQ.setText(kq);
         }
     }
    class GETAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            path+="?name="+editText1.getText().toString()+"&mark="+editText2.getText().toString();
            try {
                URL url=new URL(path);

                BufferedReader br=new BufferedReader(new
                        InputStreamReader(url.openConnection().getInputStream()));
                //bat dau doc
                String line="";
                StringBuilder stringBuilder=new StringBuilder();
                while ((line=br.readLine())!=null){
                    stringBuilder.append(line);
                }
                kq=stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            tvKQ.setText(kq);
        }
    }
}