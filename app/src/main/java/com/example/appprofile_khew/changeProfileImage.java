package com.example.appprofile_khew;
//@author Evelyn Khew

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.net.URI;

/**
 * changeProfileImage handles profile picture changes for the user.
 * Upload Photo button opens the phone's gallery and lets the user choose an image to upload.
 * Update button gets the image that the user uploaded (if they did) and uses it to set the main page's image.
 * If user does not upload an image, the update button just sends the user back to the main page without doing anything.
 * @ISSUE: uploaded image data will disappear after a different page is visited.
 */
public class changeProfileImage extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    private ImageView uploadedPhoto;
    private Button uploadButton;
    private Button updatePageButton;
    private Uri imageUri;
    private Boolean imageUploaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile_image);

        uploadedPhoto = (ImageView) findViewById(R.id.userUploadedPhoto);

        uploadButton = (Button) findViewById(R.id.uploadPhotoButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFromGallery();
            }
        });

        updatePageButton = (Button) findViewById(R.id.updatePhotoPageButton);
        updatePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUploaded == true){sendToMain();}
                else{photoUpdate();}//for now, just goes back to the main page since no image uploaded
            }
        });
    }

    /**
     * Goes back to the main page without doing anything.
     */
    public void photoUpdate(){
        Intent returnToMain = new Intent(this, MainActivity.class);
        startActivity(returnToMain);
    }

    /**
     * Trying this out from tutorialspoint.com/how-to-pick-an-image-from-image-gallery-in-android
     * Basically it gets image from the phone's gallery.
     */
    public void getFromGallery(){
        Intent getPic = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(getPic, 100);
    }

    /**
     * Called from getFromGallery()
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 100){
            imageUploaded = true; //set to true here because user may decide not to upload anything
            imageUri = data.getData();
            uploadedPhoto.setImageURI(imageUri);
        }
    }

    /**
     * Called only when there is a new image uploaded to the app.
     * Used to send uploaded image to main page.
     */
    public void sendToMain(){
        Intent i = new Intent(changeProfileImage.this, MainActivity.class);
        String imgPath = imageUri.getPath();
        i.putExtra("imgID", imageUri.toString());
        //Toast.makeText(getApplicationContext(), imageUri, Toast.LENGTH_LONG).show(); [FOR DEBUGGING PURPOSES]
        startActivity(i);
    }
}