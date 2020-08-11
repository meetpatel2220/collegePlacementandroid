package com.meet.collegeplacement;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiProfile {

    String BASE_URL = "https://college-app1.herokuapp.com/api/profile/";


    @POST("post")
    Call<TcpModel> getRegisterDetails(@Header("Authorization") String token, @Body JsonObject jsonObject);

    @POST("request")
    Call<TcpModel> getRequestDetails(@Header("Authorization") String token, @Body JsonObject jsonObject);

}
