package com.meet.collegeplacement;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiTcp {

    String BASE_URL = "https://college-app1.herokuapp.com/api/tcp/";

    @POST("post")
    Call<TcpModel> getRegisterDetails(@Body JsonObject jsonObject);

    @GET("get")
    Call<List<Hero>> getHeroes();


}
