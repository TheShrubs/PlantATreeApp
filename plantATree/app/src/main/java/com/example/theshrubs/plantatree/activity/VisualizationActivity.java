package com.example.theshrubs.plantatree.activity;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Tree;

public class VisualizationActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    private ImageView takenPboto;
    private ViewGroup rootLayout;
    private ImageView dragPhoto;
    private Bitmap photo;
    private int height;
    private int width;
    private int TREE_ID;
    private Tree tree;
    private DatabaseHelper dbHelper;
    private ImageView image1, image2, image3, image4, image5;
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualize_tree);

        Bundle extras = getIntent().getExtras();
        TREE_ID = extras.getInt("TREE");

        this.dbHelper = new DatabaseHelper(this);
        this.tree = (Tree) dbHelper.findHandle(TREE_ID, "Tree");

        instantiateControls();
        count = 0;

        this.rootLayout = (ViewGroup) findViewById(R.id.visualize_view_group);
        this.takenPboto = (ImageView) findViewById(R.id.housePhoto);
        this.photo = ViewItemActivity.getBitmap();
        this.takenPboto.setImageBitmap(photo);

        this.height = photo.getHeight();
        this.width = photo.getWidth();

        this.dragPhoto = (ImageView) findViewById(R.id.vis_image);
        this.dragPhoto.setImageResource(tree.getPhotoDrag());
        dragPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                if(count < 6){
                    setImageViews(count);
                }else{
                    showToast("Sorry, you have reached the limit of trees to visualize");
                }

            }
        });

    }

    private void instantiateControls() {
        this.image1 = (ImageView) findViewById(R.id.drag_vis1);
        this.image2 = (ImageView) findViewById(R.id.drag_vis2);
        this.image3 = (ImageView) findViewById(R.id.drag_vis3);
        this.image4 = (ImageView) findViewById(R.id.drag_vis4);
        this.image5 = (ImageView) findViewById(R.id.drag_vis5);

        this.image1.setImageResource(tree.getPhotoThumb());
        this.image2.setImageResource(tree.getPhotoThumb());
        this.image3.setImageResource(tree.getPhotoThumb());
        this.image4.setImageResource(tree.getPhotoThumb());
        this.image5.setImageResource(tree.getPhotoThumb());

        this.image1.setVisibility(View.INVISIBLE);
        this.image2.setVisibility(View.INVISIBLE);
        this.image3.setVisibility(View.INVISIBLE);
        this.image4.setVisibility(View.INVISIBLE);
        this.image5.setVisibility(View.INVISIBLE);
    }

    public void setImageViews(int countControl) {
        switch (countControl) {
            case 1:
                this.image1.setVisibility(View.VISIBLE);
                this.image1.setOnTouchListener(this);
                this.image1.setOnDragListener(this);
                break;
            case 2:
                this.image2.setVisibility(View.VISIBLE);
                this.image2.setOnTouchListener(this);
                break;
            case 3:
                this.image3.setVisibility(View.VISIBLE);
                this.image3.setOnTouchListener(this);
                break;
            case 4:
                this.image4.setVisibility(View.VISIBLE);
                this.image4.setOnTouchListener(this);
                break;
            case 5:
                this.image5.setVisibility(View.VISIBLE);
                this.image5.setOnTouchListener(this);
                break;

        }
    }

    private void showToast(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if ((event.getAction() == (MotionEvent.ACTION_DOWN)) && ((ImageView) v).getDrawable() != null) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                ImageView image = (ImageView) event.getLocalState();
                ((ImageView) v).setImageDrawable(getResources().getDrawable(tree.getPhotoThumb()));
                ((ImageView) image).setImageDrawable(null);
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
        }
        return true;
    }
}
