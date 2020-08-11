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

public class Register extends AppCompatActivity {

    EditText name,email,password;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        done=findViewById(R.id.done);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name1=name.getText().toString();
                String email1=email.getText().toString();
                String password1=password.getText().toString();

                Gson gson=new GsonBuilder()
                        .setLenient()
                        .create();
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                Api api=retrofit.create(Api.class);
                JsonObject object=new JsonObject();
                object.addProperty("email",email1);
                object.addProperty("password",password1);
                object.addProperty("name",name1);

                Call<RegisterModel> call=api.getRegisterDetails(object);


                call.enqueue(new Callback<RegisterModel>() {
                    @Override
                    public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {

                        RegisterModel registerModel=response.body();


                        if (response.body().getSuccess().equals(true)){
                            Toast.makeText(Register.this,"Registration successful", Toast.LENGTH_SHORT).show();

                          //  Toast.makeText(Login.this, ""+loginModel.getToken().toString(), Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(Register.this, "Registration fail", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterModel> call, Throwable t) {
                        Toast.makeText(Register.this, "Registration fail", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });




    }
}
