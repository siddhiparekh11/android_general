package com.example.siddhiparekh11.labassignment;
import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.widget.ShareDialog;
import com.facebook.share.widget.ShareDialog.Mode;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
/**
 * Created by siddhiparekh11 on 9/20/17.
 */

public class SharePhotoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharephoto);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
    public void sharePhoto(View v)
    {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.ani_cat);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.show(content, Mode.AUTOMATIC);

    }
}
