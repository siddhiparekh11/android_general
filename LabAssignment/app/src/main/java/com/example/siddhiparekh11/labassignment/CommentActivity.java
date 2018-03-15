package com.example.siddhiparekh11.labassignment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RatingBar;
import android.view.View;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by siddhiparekh11 on 9/21/17.
 */

public class CommentActivity extends Activity {
    private RatingBar ratingBar;
    String ratings;
    EditText comment;
    TextView display;
    myDBHandler h;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
    }

    protected void onStart() {
        super.onStart();
        h = new myDBHandler(this, null, null, 1);
        comment = (EditText) findViewById(R.id.edit_name);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        display=(TextView)findViewById(R.id.displayComment);
    }
    public void rateMe(View v)
    {
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                ratings=String.valueOf(rating);

            }
        });
    }
    public void onAdd(View v)
    {
        Comments c;
        ratings=String.valueOf(ratingBar.getRating());
        c=new Comments(comment.getText().toString(),ratings);
        h.addComment(c);
        display.setText(h.databaseToString());

    }
    public void onCancel(View v)
    {
        comment.setText("");
        ratingBar.setRating(0);

    }
}
