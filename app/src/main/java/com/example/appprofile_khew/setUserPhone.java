package com.example.appprofile_khew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * setUserPhone checks the setUserPhone TextView from activity_set_user_phone.xml for user input.
 * If
 *  - update button is clicked on no input
 *  - phone number is not 10 digits long
 *  - phone number is not numerical
 * it throws an error.
 * Once a valid phone number is set, it is saved through SharedPreferences.
 */
public class setUserPhone extends AppCompatActivity {
    private String phoneNumber;
    private EditText inputNumber;
    private Button updateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_phone);

        inputNumber = (EditText) findViewById(R.id.setUserPhoneNumber);
        updateButton = (Button) findViewById((R.id.updatePhoneButton));
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneUpdate();
            }
        });
    }

    private void phoneUpdate(){
        phoneNumber = inputNumber.getText().toString().trim();
        if(!checkPhoneNumber()){
            return;
        }
        saveNumber();
        Intent returnToMain = new Intent(this, MainActivity.class);
        startActivity(returnToMain);
    }

    private boolean checkPhoneNumber(){
        if(phoneNumber.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter your phone number.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phoneNumber.length() != 10){
            inputNumber.setError("U.S. Phone numbers have 10 digits.");
        }
        if(!phoneNumber.matches("[0-9]+")){
            inputNumber.setError("Phone number needs to be numerical only.");
            return false;
        }
        else{ //parsing string for formatting purposes
            String temp1;
            String temp2;
            //"( 0 1 2 ) "
            temp2 = phoneNumber.substring(0, 3);
            temp1 = '(' + temp2 + ") ";

            //"( 0 1 2 ) 3 4 5"
            temp2 = phoneNumber.substring(3, 6);
            temp1 = temp1 + temp2 + '-';

            //"(0 1 2 ) 3 4 5 - 6 7 8 9"
            temp2 = phoneNumber.substring(6, 10);
            temp1 = temp1 + temp2;

            phoneNumber = temp1;

            return true;
        }
    }

    private void saveNumber(){
        SharedPreferences userPhone = getSharedPreferences("shared_phone", MODE_PRIVATE);
        SharedPreferences.Editor editor = userPhone.edit();
        editor.putString("user_phone", phoneNumber);
        editor.commit();
    }
}