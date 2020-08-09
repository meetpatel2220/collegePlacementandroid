package com.meet.collegeplacement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button done,google;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        done=findViewById(R.id.done);
        google=findViewById(R.id.google);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email1=email.getText().toString();
                String password1=password.getText().toString();

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api=retrofit.create(Api.class);
                JsonObject object=new JsonObject();
                object.addProperty("email",email1);
                object.addProperty("password",password1);

                Call<LoginModel> call=api.getLoginDetails(object);

                call.enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                        LoginModel loginModel=response.body();
                        if(loginModel.getSuccess()){
                            Toast.makeText(Login.this,"login successful", Toast.LENGTH_SHORT).show();

                            Toast.makeText(Login.this, ""+loginModel.getToken().toString(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Login.this,"login fail", Toast.LENGTH_SHORT).show();

                        }
                         }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {

                        Toast.makeText(Login.this, "Login fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
