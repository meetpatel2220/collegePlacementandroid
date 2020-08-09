package com.meet.collegeplacement;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiTcp {

    String BASE_URL = "https://college-placement.herokuapp.com/api/tcp";

    @POST("")
    Call<RegisterModel> getRegisterDetails(@Body JsonObject jsonObject);




}
