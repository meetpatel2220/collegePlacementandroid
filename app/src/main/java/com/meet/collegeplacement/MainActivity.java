package com.meet.collegeplacement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button register,login,tcp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        register=findViewById(R.id.register);
        login=findViewById(R.id.login);
        tcp=findViewById(R.id.tcp);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(MainActivity.this,Register.class);
                startActivity(in);


            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(MainActivity.this,Login.class);
                startActivity(in);


            }
        });


        tcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(MainActivity.this,Tcp.class);
                startActivity(in);


            }
        });



    }
}
