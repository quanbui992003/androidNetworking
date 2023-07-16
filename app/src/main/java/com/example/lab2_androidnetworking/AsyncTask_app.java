package com.example.lab2_androidnetworking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AsyncTask_app extends AsyncTask<String,Void, Bitmap> {
    private Interface_app anInterface;//lang nghe va tra ket qua
    private Context context;
    public AsyncTask_app(Interface_app anInterface, Context context) {
        this.anInterface = anInterface;
        this.context=context;
    }
    //ham doc du lieu tu server
    @Override
    protected Bitmap doInBackground(String... strings) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
    //tra ket qua ve client
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap!=null)//neu ton tai anh
        {
            anInterface.onLoadAnh(bitmap);//tra anh ve client
        }
        else {
            anInterface.onLoi();
        }
    }
}
