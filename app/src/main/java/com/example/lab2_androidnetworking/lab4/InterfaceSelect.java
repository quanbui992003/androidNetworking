package com.example.lab2_androidnetworking.lab4;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfaceSelect {
    @GET("get_all_product.php")
    Call<SvrResponseSelect> getPrd();


}
