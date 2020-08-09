package com.meet.collegeplacement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tcp extends AppCompatActivity {

    EditText companyname,salary,time;
    Button post,request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);


        companyname=findViewById(R.id.companyname);
        salary=findViewById(R.id.salary);
        time=findViewById(R.id.time);
        post=findViewById(R.id.post);
        request=findViewById(R.id.request);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String companyname1=companyname.getText().toString();
                String salary1=salary.getText().toString();
                String time1=time.getText().toString();

                Gson gson=new GsonBuilder()
                        .setLenient()
                        .create();
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(ApiTcp.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                ApiTcp apiTcp=retrofit.create(ApiTcp.class);
                JsonObject object=new JsonObject();
                object.addProperty("companyname",companyname1);
                object.addProperty("salary",salary1);
                object.addProperty("time",time1);

                Call<RegisterModel> call=apiTcp.getRegisterDetails(object);

                call.enqueue(new Callback<RegisterModel>() {
                    @Override
                    public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {

                        RegisterModel registerModel=response.body();


                        if(registerModel.getSuccess()){
                            Toast.makeText(Tcp.this, "Company Post Successful", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(Tcp.this, "company fail", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterModel> call, Throwable t) {

                    }
                });



            }
        });



    }
}
