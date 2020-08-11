package com.meet.collegeplacement;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiProfile {

    String BASE_URL = "https://college-app1.herokuapp.com/api/profile/";


    @POST("post")
    Call<ProfileModel> getProfileDetails(@Header("Authorization") String token, @Body JsonObject jsonObject);

    @POST("request")
    Call<ProfileModel> getRequestDetails(@Header("Authorization") String token, @Body JsonObject jsonObject);

    @GET("get")
    Call<List<MeetModel>> getMeet(@Header("Authorization") String token);

}
