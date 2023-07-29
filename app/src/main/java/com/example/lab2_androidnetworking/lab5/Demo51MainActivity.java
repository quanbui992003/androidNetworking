package com.example.lab2_androidnetworking.lab5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2_androidnetworking.R;


import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demo51MainActivity extends AppCompatActivity {
    EditText txt1,txt2,txt3,txt4;
    Button btn1,btn2,btn3;
    TextView tv1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lab5);
        txt1=findViewById(R.id.demo51Txt1);
        txt2=findViewById(R.id.demo51Txt2);
        txt3=findViewById(R.id.demo51Txt3);
        txt4=findViewById(R.id.demo51Txt4);
        btn1=findViewById(R.id.demo51Btn1);
        btn2=findViewById(R.id.demo51Btn2);
        btn3=findViewById(R.id.demo51Btn3);
        tv1=findViewById(R.id.demo51TvKQ);

        btn1.setOnClickListener((view)->{
            selectRetrofit();
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRetrofit();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRetrofit();
            }
        });
    }

    private void deleteRetrofit() {
        //B0. Chuan bi du lieu
        Prod p=new Prod();
        p.setPid(txt1.getText().toString());
        //b1. Tao doi tuong Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterDelete interDelete=retrofit.create(InterDelete.class);
        Call<SvrResponseDelete> call=interDelete.deleteDB(p.getPid());
        call.enqueue(new Callback<SvrResponseDelete>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseDelete> call, Response<SvrResponseDelete> response) {
                //lay ve ket qua
                SvrResponseDelete svrResponseDelete=response.body();
                //dua ket qua len man hinh
                tv1.setText(svrResponseDelete.getMessage());
            }
            //that bai
            @Override
            public void onFailure(Call<SvrResponseDelete> call, Throwable t) {
                tv1.setText(t.getMessage());
            }
        });
    }

    private void updateRetrofit() {
        //B0. Chuan bi du lieu
        Prod p=new Prod();
        p.setPid(txt1.getText().toString());
        p.setName(txt2.getText().toString());
        p.setPrice(txt3.getText().toString());
        p.setDescription(txt4.getText().toString());
        //b1. Tao doi tuong Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterUpdate interUpdate=retrofit.create(InterUpdate.class);
        Call<SvrResponseUpdate> call=interUpdate.updateDB(p.getPid(),
                p.getName(),p.getPrice(),p.getDescription());
        call.enqueue(new Callback<SvrResponseUpdate>() {
            @Override
            public void onResponse(Call<SvrResponseUpdate> call, Response<SvrResponseUpdate> response) {
                //lay ve ket qua
                SvrResponseUpdate svrResponseUpdate=response.body();
                //dua ket qua len man hinh
                tv1.setText(svrResponseUpdate.getMessage());
                Toast.makeText(Demo51MainActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SvrResponseUpdate> call, Throwable t) {
                tv1.setText(t.getMessage());//ghi ra loi
                Toast.makeText(Demo51MainActivity.this, "k thnah cong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    ArrayList<Prod> ls;
    String strKQ="";//chuoi chua ket qua
    private void selectRetrofit() {
        //B0. Chuan bi du lieu
        //b1. Tao doi tuong Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterSelect interSelect=retrofit.create(InterSelect.class);
        Call<SvrResponseSelect> call=interSelect.selectDB();
        call.enqueue(new Callback<SvrResponseSelect>() {
            @Override
            public void onResponse(Call<SvrResponseSelect> call, Response<SvrResponseSelect> response) {
                //lay ve key qua
                SvrResponseSelect svrResponseSelect=response.body();
                //chuyen mang sang list
                ls=new ArrayList<>(Arrays.asList(svrResponseSelect.getProducts()));
                //for
                strKQ="";
                for(Prod p: ls){
                    strKQ += "Pid: "+p.getPid()
                            +" - "+p.getName()
                            +" - "+p.getPrice()
                            +" - "+p.getDescription()+"\n";
                }
                //dua ket qua len man hinh
                tv1.setText(strKQ);
            }

            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable t) {
                tv1.setText(t.getMessage());//dua ra thong bao loi
            }
        });
    }
}