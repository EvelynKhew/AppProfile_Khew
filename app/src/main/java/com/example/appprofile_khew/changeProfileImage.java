package com.example.appprofile_khew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * changeProfileImage currently does nothing.
 * Upload Photo button does nothing.
 * Update button just sends you back to the main screen.
 */
public class changeProfileImage extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    private ImageView uploadedPhoto;
    private Button uploadButton;
    private Button updatePageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile_image);

        uploadedPhoto = (ImageView) findViewById(R.id.userUploadedPhoto);

        uploadButton = (Button) findViewById(R.id.uploadPhotoButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Unimplemented yet", Toast.LENGTH_SHORT).show();
            }
        });

        updatePageButton = (Button) findViewById(R.id.updatePhotoPageButton);
        updatePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for now, just goes back to the main page
                photoUpdate();
            }
        });
    }

    public void photoUpdate(){
        Intent returnToMain = new Intent(this, MainActivity.class);
        startActivity(returnToMain);
    }


}