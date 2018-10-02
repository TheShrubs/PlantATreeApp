package com.example.theshrubs.plantatree.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.theshrubs.plantatree.R;

public class ViewingTreeActivity extends AppCompatActivity {

    private ImageView takenPboto;
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_tree);

        this.takenPboto = (ImageView) findViewById(R.id.housePhoto);
        this.photo = ViewItemActivity.getBitmap();

        this.takenPboto.setImageBitmap(photo);

    }

//    public void setTakenPboto(Bitmap photo){
//        this.takenPboto.setImageBitmap(photo);
//    }
}
