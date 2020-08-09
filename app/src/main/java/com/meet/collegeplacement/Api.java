package com.meet.collegeplacement;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://college-placement.herokuapp.com/api/auth/";

    @POST("login")
    Call<LoginModel> getLoginDetails(@Body JsonObject object);

   @POST("register")
    Call<RegisterModel> getRegisterDetails(@Body JsonObject jsonObject);


}
