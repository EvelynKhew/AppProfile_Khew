package com.example.appprofile_khew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView uName;
    private TextView uPhone;
    private TextView uEmail;
    private TextView uBio;

    setUserName nameObj = new setUserName();
    setUserPhone phoneObj = new setUserPhone();
    setUserEmail emailObj = new setUserEmail();
    setUserBio bioObj = new setUserBio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uName = (TextView) findViewById(R.id.userName);
        uPhone = (TextView) findViewById(R.id.userPhone);
        uEmail = (TextView) findViewById(R.id.userEmail);
        uBio = (TextView) findViewById(R.id.userBio);

        uName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetUserName();
            }
        });

        uPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetUserPhone();
            }
        });

        uEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetUserEmail();
            }
        });

        uBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetUserBio();
            }
        });
    }

    public void openSetUserName(){
        Intent intent = new Intent(this, setUserName.class);
        startActivity(intent);
    }

    public void openSetUserPhone(){
        Intent intent = new Intent(this, setUserPhone.class);
        startActivity(intent);
    }

    public void openSetUserEmail(){
        Intent intent = new Intent(this, setUserEmail.class);
        startActivity(intent);
    }

    public void openSetUserBio(){
        Intent intent = new Intent(this, setUserBio.class);
        startActivity(intent);
    }
}