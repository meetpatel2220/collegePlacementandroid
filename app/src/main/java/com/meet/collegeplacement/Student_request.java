package com.meet.collegeplacement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Student_request extends AppCompatActivity {
    RecyclerView recyclerView;
    private static final String mypreference = "mypreference";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request);

        recyclerView=findViewById(R.id.t1);
        recyclerView.setLayoutManager(new LinearLayoutManager(Student_request.this));

        Retrofit retrofit = new Retrofit.Builder().baseUrl( ApiProfile.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() ).build();

        ApiProfile api = retrofit.create( ApiProfile.class );

        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

       // Toast.makeText(this, sharedPreferences.getString("token",""), Toast.LENGTH_SHORT).show();


        Call<List<MeetModel>> call = api.getMeet(sharedPreferences.getString("token",""));


        call.enqueue(new Callback<List<MeetModel>>() {
            @Override
            public void onResponse(Call<List<MeetModel>> call, Response<List<MeetModel>> response) {
                List<MeetModel> heroes = response.body();


                recyclerView.setAdapter(new SendAdapter(Student_request.this,heroes));

            }

            @Override
            public void onFailure(Call<List<MeetModel>> call, Throwable t) {

            }
        });

    }
}
