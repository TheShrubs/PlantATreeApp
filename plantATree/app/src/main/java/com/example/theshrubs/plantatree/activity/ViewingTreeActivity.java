package com.example.theshrubs.plantatree.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.theshrubs.plantatree.R;

public class ViewingTreeActivity extends AppCompatActivity implements View.OnDragListener{

    private ImageView takenPboto;
    private ImageView dragPhoto;
    private Bitmap photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_tree);

        this.takenPboto = (ImageView) findViewById(R.id.housePhoto);
        this.photo = ViewItemActivity.getBitmap();
        this.takenPboto.setImageBitmap(photo);

        this.dragPhoto = (ImageView) findViewById(R.id.itemDrop);
        this.dragPhoto.setOnDragListener(this);

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }

//    public void setTakenPboto(Bitmap photo){
//        this.takenPboto.setImageBitmap(photo);
//    }
}
