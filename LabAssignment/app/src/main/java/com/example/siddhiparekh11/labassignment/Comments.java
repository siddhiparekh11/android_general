package com.example.siddhiparekh11.labassignment;

/**
 * Created by siddhiparekh11 on 9/22/17.
 */

public class Comments {


    private String _commentdescription;
    private String _rating;

    public Comments(String _commentdescription, String _rating) {
        this._commentdescription = _commentdescription;
        this._rating = _rating;
    }

    public String get_commentdescription() {
        return _commentdescription;
    }

    public void set_commentdescription(String _commentdescription) {
        this._commentdescription = _commentdescription;
    }

    public String get_rating() {
        return _rating;
    }

    public void set_rating(String _rating) {
        this._rating = _rating;
    }
}
