package com.example.siddhiparekh11.labassignment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by siddhiparekh11 on 9/19/17.
 */

public class AboutActivity extends Activity {

    private ZXingScannerView scannerView;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private TextView t;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

    }

    public void scanCode(View v)

    {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZxingScannerResultHandler());
        setContentView(scannerView);
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},MY_CAMERA_REQUEST_CODE
            );
        }
        else {
            scannerView.startCamera();
        }

    }
    @Override
    public void onPause()
    {
        super.onPause();
        scannerView.stopCamera();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_CAMERA_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    scannerView.startCamera();

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
    class ZxingScannerResultHandler implements ZXingScannerView.ResultHandler
    {
        @Override
        public void handleResult(Result result)
        {
            String resultCode= result.getText();

            Toast.makeText(AboutActivity.this,resultCode,Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_about);
            t=findViewById(R.id.displayInformation);
            t.setText(resultCode);
            scannerView.stopCamera();
        }
    }
}
