package com.example.theshrubs.plantatree.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Tree;

public class VisualizationActivity extends AppCompatActivity implements View.OnDragListener {

    private ImageView takenPboto;
    private ViewGroup rootLayout;
    private ImageView dragPhoto;
    private Bitmap photo;
    private int USER_ID;
    private int height;
    private int width;
    private int TREE_ID;
    private Tree tree;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualize_tree);

        Bundle extras = getIntent().getExtras();
        TREE_ID = extras.getInt("TREE");

        this.dbHelper = new DatabaseHelper(this);
        this.tree = (Tree) dbHelper.findHandle(TREE_ID, "Tree");
        System.out.println("current tree is " + tree.getTreeName());

        this.rootLayout = (ViewGroup) findViewById(R.id.visualize_view_group);
        this.takenPboto = (ImageView) findViewById(R.id.housePhoto);
        this.photo = ViewItemActivity.getBitmap();
        this.takenPboto.setImageBitmap(photo);

        this.height = photo.getHeight();
        this.width = photo.getWidth();

        this.dragPhoto = (ImageView) findViewById(R.id.vis_image);
        this.dragPhoto.setImageResource(R.drawable.drag1);
        dragPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                image
//                rootLayout.removeView();
                rootLayout.addView(dragPhoto);
            }
        });

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        return true;
    }

}
