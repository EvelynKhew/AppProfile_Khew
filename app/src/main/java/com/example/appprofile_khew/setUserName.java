package com.example.appprofile_khew;
//@author Evelyn Khew
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * setUserName checks the setUserBio TextView from activity_set_user_name.xml for user input.
 * If
 *  - update button is clicked on no input for either TextViews
 *  - name syntax is incorrect (they are not alphabetical)
 * it throws an error.
 * Once a valid name is set, it is saved through SharedPreferences.
 */
public class setUserName extends AppCompatActivity {

    private String fName;
    private String lName;
    private EditText fEditText;
    private EditText lEditText;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_name);

        fEditText = (EditText) findViewById(R.id.setUserFirstName);
        lEditText = (EditText) findViewById(R.id.setUserLastName);
        updateButton = (Button) findViewById(R.id.updateNameButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameUpdate();
            }
        });
    }

    public void nameUpdate(){
        fName = fEditText.getText().toString().trim();
        lName = lEditText.getText().toString().trim();
        //Toast.makeText(getApplicationContext(), fName + " " + lName, Toast.LENGTH_SHORT).show(); [FOR DEBUGGING PURPOSES]
        if(!nameChecker()){
            return;
        }
        saveName();
        Intent returnToMain = new Intent(this, MainActivity.class);
        returnToMain.putExtra("name", fName + " " + lName);
        startActivity(returnToMain);
    }

    public void saveName(){
        SharedPreferences usernames = getSharedPreferences("shared_name", MODE_PRIVATE);
        SharedPreferences.Editor editor = usernames.edit();
        editor.putString("username", fName + " " + lName);
        editor.commit();
    }

    public boolean nameChecker(){
        if(fName.isEmpty() && lName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Name fields are empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(fName.isEmpty()){
            fEditText.setError("Please enter something.");
            return false;
        }
        else if(lName.isEmpty()){
            lEditText.setError("Please enter something.");
            return false;
        }
        else if(!fName.matches("[a-zA-Z]+")){ //cannot accept names like "Mary Sue"
            fEditText.setError("Names are alphabetical.");
            return false;
        }
        else if(!lName.matches("[a-zA-Z]+")){ //Can accept names like "McClannahan" because Capitalisation doesn't matter here.
            lEditText.setError(("Names are alphabetical."));
            return false;
        }
        else{
            return true;
        }
    }
}
