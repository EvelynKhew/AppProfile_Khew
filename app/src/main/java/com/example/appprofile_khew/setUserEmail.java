package com.example.appprofile_khew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * setUserEmail checks the setUserBio TextView from activity_set_user_email.xml for user input.
 * If
 *  - update button is clicked on no input
 *  - email syntax is incorrect
 * it throws an error.
 * Once a valid email is set, it is saved through SharedPreferences.
 */
public class setUserEmail extends AppCompatActivity {
    private String email;
    private EditText inputEmail;
    private Button updateButton;

    /**
     * Automatically called when activity opens.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_email);

        inputEmail = (EditText) findViewById(R.id.setUserEmail);
        updateButton = (Button) findViewById(R.id.updateEmailButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailUpdate();
            }
        });
    }

    /**
     * Called when the update button is clicked.
     * Sends an error if the email field does not pass emailRegEx().
     * Navigates back to main page otherwise.
     */
    public void emailUpdate(){
        email = inputEmail.getText().toString().trim();
        if(!emailRegEx(email)){ return; } //stop running if email is in wrong format
        //Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show(); [USED FOR DEBUGGING PURPOSES]
        saveEmail();
        Intent returnToMain = new Intent(this, MainActivity.class);
        startActivity(returnToMain);
    }

    /**
     * Called by emailUpdate().
     * Checks if email field is empty or does not conform to email RegEx.
     * @param userEmail
     * @return true if email field is not empty or conforms to email RegEx.
     * @return false otherwise.
     */
    private boolean emailRegEx(String userEmail){
        if(userEmail.isEmpty()){
            inputEmail.setError("Email textbox is empty. Try again.");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            inputEmail.setError("Please enter a valid email address.");
            return false;
        }
        else{
            inputEmail.setError(null);
            return true;
        }
    }

    /**
     * Stores User input of email using SharedPreferences.
     */
    public void saveEmail(){
        SharedPreferences userEmail = getSharedPreferences("shared_email", MODE_PRIVATE);
        SharedPreferences.Editor editor = userEmail.edit();
        editor.putString("user_email", email);
        editor.commit();
    }
}