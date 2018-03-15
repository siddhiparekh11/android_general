package com.example.siddhiparekh11.labassignment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.CursorJoiner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.Result;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnAbout(View v)
    {
        Intent i=new Intent(getApplicationContext(),AboutActivity.class);
        startActivity(i);

    }
    public void btnPhotoVideo(View v)
    {
        Intent i=new Intent(getApplicationContext(),PhotoVideoActivity.class);
        startActivity(i);
    }
    public void btnPhotoShare(View v)
    {
        Intent i=new Intent(getApplicationContext(),SharePhotoActivity.class);
        startActivity(i);
    }
    public void btnMaps(View v)
    {
        Intent i=new Intent(getApplicationContext(),MapActivity.class);
        startActivity(i);
    }
    public void btnInteract(View v)
    {
        Intent i=new Intent(getApplicationContext(),InteractActivity.class);
        startActivity(i);
    }
    public void btnComment(View v)
    {
        Intent i=new Intent(getApplicationContext(),CommentActivity.class);
        startActivity(i);
    }

}
