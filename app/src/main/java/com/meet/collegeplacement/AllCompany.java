package com.meet.collegeplacement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllCompany extends AppCompatActivity {
    RecyclerView recyclerView;
    private static final String mypreference = "mypreference";
    private SharedPreferences sharedPreferences;

    EditText username,mobileno,address;
    Button post,strequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_company);
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        Toast.makeText(this, sharedPreferences.getString("token",""), Toast.LENGTH_SHORT).show();

        username=findViewById(R.id.username);
        mobileno=findViewById(R.id.mobileno);
        address=findViewById(R.id.address);
        post=findViewById(R.id.post);
        strequest=findViewById(R.id.strequest);

        strequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(AllCompany.this,Student_request.class);
                startActivity(in);

            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username1=username.getText().toString();
                String mobileno1=mobileno.getText().toString();
                String address1=address.getText().toString();

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(ApiProfile.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
           //     ApiProfile api=RetrofitClientinsttancePost.getRetrofit().create(ApiProfile.class);

                // Api api=RetrofitClientinsttance.getRetrofit().create(Api.class);
                ApiProfile api=retrofit.create(ApiProfile.class);


                sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);


                JsonObject object=new JsonObject();
                object.addProperty("username",username1);
                object.addProperty("mobileno",mobileno1);
                object.addProperty("address",address1);



             //   Toast.makeText(AllCompany.this, sharedPreferences.getString("token",""), Toast.LENGTH_SHORT).show();
                Call<ProfileModel> call=api.getProfileDetails(sharedPreferences.getString("token",""),object);

                call.enqueue(new Callback<ProfileModel>() {
                    @Override
                    public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {

                        if (response.body().getSuccess().equals(true)) {
                            Toast.makeText(AllCompany.this, "Profile send done", Toast.LENGTH_SHORT).show();
                        }
                        if (response.body().getSuccess().equals(false)) {
                            Toast.makeText(AllCompany.this, "Profile send request failed", Toast.LENGTH_SHORT).show();
                        }

                        }

                    @Override
                    public void onFailure(Call<ProfileModel> call, Throwable t) {

                    }
                });

            }
        });


        recyclerView=findViewById(R.id.t1);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllCompany.this));

        Retrofit retrofit = new Retrofit.Builder().baseUrl( ApiTcp.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() ).build();

        ApiTcp api = retrofit.create( ApiTcp.class );
        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                List<Hero> heroes = response.body();


                recyclerView.setAdapter(new PostAdapter(AllCompany.this,heroes));

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText( AllCompany.this, t.getMessage(), Toast.LENGTH_SHORT ).show();

            }
        });

    }
}
