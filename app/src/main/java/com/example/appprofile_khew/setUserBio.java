package com.example.appprofile_khew;
//@author Evelyn Khew

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * setUserBio checks the setUserBio TextView from activity_set_user_bio.xml for user input.
 * If update button is clicked on no input, it throws an error.
 * Once a valid bio is set, it is saved through SharedPreferences.
 */
public class setUserBio extends AppCompatActivity {
    private String uBio;
    private EditText inputBio;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_bio);
        inputBio = (EditText) findViewById(R.id.setUserBio);
        updateButton = (Button) findViewById(R.id.updateUserBio);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bioUpdate();
            }
        });
    }

    private void bioUpdate(){
        uBio = inputBio.getText().toString();
        if(uBio.isEmpty()){
            inputBio.setError("Please enter a bio.");
            return;
        }
        saveBio();
        Intent returnToMain = new Intent(this, MainActivity.class);
        startActivity(returnToMain);
    }

    private void saveBio(){
        SharedPreferences userBio = getSharedPreferences("shared_bio", MODE_PRIVATE);
        SharedPreferences.Editor editor = userBio.edit();
        editor.putString("user_bio", uBio);
        editor.commit();
    }
}