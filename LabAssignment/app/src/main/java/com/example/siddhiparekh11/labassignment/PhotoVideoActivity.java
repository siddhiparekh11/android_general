package com.example.siddhiparekh11.labassignment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.widget.TextView;

/**
 * Created by siddhiparekh11 on 9/19/17.
 */

public class PhotoVideoActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_EXTERNAL_STORAGE = 1;
    File photoFile = null;
    TextView t;

    String mCurrentPhotoPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photovideo);
        t=findViewById(R.id.picSaved);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

   public void checkPermission()
   {
       String[] PERMISSIONS_STORAGE = {
               Manifest.permission.READ_EXTERNAL_STORAGE,
               Manifest.permission.WRITE_EXTERNAL_STORAGE
       };
       int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

       if (permission != PackageManager.PERMISSION_GRANTED) {
           // We don't have permission so prompt the user
           ActivityCompat.requestPermissions(
                   this,
                   PERMISSIONS_STORAGE,
                   REQUEST_EXTERNAL_STORAGE
           );
       }
       else
       {
           try {
               photoFile = createImageFile();
           } catch (IOException ex) {
               System.out.println(ex.toString());
               // Error occurred while creating the File

           }

       }

   }

    private File createImageFile() throws IOException {
        // Create an image file name


        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                    photoFile = createImageFile();
                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                        // Error occurred while creating the File

                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }




    public void takePhoto(View v)
    {
        dispatchTakePictureIntent();
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go


                checkPermission();


            // Continue only if the File was successfully created
           if (photoFile != null) {
                Uri photoURI = Uri.fromFile(photoFile);
             //  Uri photoURI = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".my.package.name.provider", photoFile);
              takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);



        }
        }

        }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.gc();
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                     t.setText("Picture is saved!");

                }
            }
        }
    }

