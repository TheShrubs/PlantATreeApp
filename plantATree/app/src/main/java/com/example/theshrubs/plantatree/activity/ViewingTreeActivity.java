package com.example.theshrubs.plantatree.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.theshrubs.plantatree.R;

public class ViewingTreeActivity extends AppCompatActivity {//implements View.OnDragListener{

    private ImageView takenPboto;
    private ImageView dragPhoto;
    private Bitmap photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualize_tree);

        this.takenPboto = (ImageView) findViewById(R.id.housePhoto);
        this.photo = ViewItemActivity.getBitmap();
        this.takenPboto.setImageBitmap(photo);

        int height = takenPboto.getHeight();
        int width = takenPboto.getWidth();

        this.dragPhoto = (ImageView) findViewById(R.id.itemDrop);
        this.dragPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }


}
