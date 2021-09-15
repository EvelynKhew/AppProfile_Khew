package com.example.appprofile_khew;
//@author Evelyn Khew

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main Activity handles activity_main.xml
 * Clicking on the various TextViews will allow the user to navigate between their respective activity pages.
 * Uses SharedPreferences to store user input.
 */
public class MainActivity extends AppCompatActivity {

    private ImageView uPic;
    private TextView uName;
    private TextView uPhone;
    private TextView uEmail;
    private TextView uBio;

    //Was wanting to use OOP to retrieve information between classes, but it didn't work.
    //Switched to using SharedPreferences instead.
//    setUserName nameObj = new setUserName();
//    setUserPhone phoneObj = new setUserPhone();
//    setUserEmail emailObj = new setUserEmail();
//    setUserBio bioObj = new setUserBio();

    /**
     * Automatically called when Activity starts.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Handling user profile pic
        uPic = (ImageView) findViewById(R.id.userProfilePic);

            Bundle bundle = getIntent().getExtras();
            if(bundle!= null){
                Intent imgIntent = getIntent();
                String imgPath = imgIntent.getStringExtra("imgID");
                Uri imgUri = Uri.parse(imgPath);
                uPic.setImageURI(imgUri);
            }

        //Handling username TextView
        uName = (TextView) findViewById(R.id.userName);

        SharedPreferences namePrefs = getSharedPreferences("shared_name", MODE_PRIVATE);
        if(namePrefs.getString("username", "") != null){
            uName.setText(namePrefs.getString("username", ""));
        }

        //Handling user phone TextView
        uPhone = (TextView) findViewById(R.id.userPhone);

        SharedPreferences phonePrefs = getSharedPreferences("shared_phone", MODE_PRIVATE);
        if(phonePrefs.getString("user_phone", "") != null){
            uPhone.setText(phonePrefs.getString("user_phone", ""));
        }

        //Handling user email TextView
        uEmail = (TextView) findViewById(R.id.userEmail);

        SharedPreferences emailPrefs = getSharedPreferences("shared_email", MODE_PRIVATE);
        if(emailPrefs.getString("user_email", "") != null){
            uEmail.setText(emailPrefs.getString("user_email", ""));
        }

        //Handling user bio TextView
        uBio = (TextView) findViewById(R.id.userBio);

        SharedPreferences bioPrefs = getSharedPreferences("shared_bio", MODE_PRIVATE);
        if(bioPrefs.getString("user_bio", "") != null){
            uBio.setText(bioPrefs.getString("user_bio", ""));
        }

        uPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openSetUserImage(); }
        });

        //when name field is clicked
        uName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetUserName();
            }
        });

        //when phone field is clicked
        uPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetUserPhone();
            }
        });

        //when email field is clicked
        uEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetUserEmail();
            }
        });

        //when bio field is clicked
        uBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetUserBio();
            }
        });
    }

    //opens when the photo ImageView is clicked
    public void openSetUserImage(){
        Intent intent = new Intent(this, changeProfileImage.class);
        startActivity(intent);
    }

    //opens when the name TextView is clicked
    public void openSetUserName(){
        Intent intent = new Intent(this, setUserName.class);
        startActivity(intent);
    }

    //opens when the phone TextView is clicked
    public void openSetUserPhone(){
        Intent intent = new Intent(this, setUserPhone.class);
        startActivity(intent);
    }

    //opens when the email TextView is clicked
    public void openSetUserEmail(){
        Intent intent = new Intent(this, setUserEmail.class);
        startActivity(intent);
    }

    //opens when the bio TextView is clicked
    public void openSetUserBio(){
        Intent intent = new Intent(this, setUserBio.class);
        startActivity(intent);
    }
}