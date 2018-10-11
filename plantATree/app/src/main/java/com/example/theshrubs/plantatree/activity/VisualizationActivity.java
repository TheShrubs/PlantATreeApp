package com.example.theshrubs.plantatree.activity;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Tree;

public class VisualizationActivity extends AppCompatActivity {

    private ImageView takenPboto;
    private ImageView thumbnail;
    private Bitmap photo;
    private int TREE_ID;
    private Tree tree;
    private DatabaseHelper dbHelper;
    private ImageView image1, image2, image3, image4, image5;
    private ImageView rubbish_bin;
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

        this.rubbish_bin = (ImageView) findViewById(R.id.trash);
        this.rubbish_bin.setOnDragListener(dragListener);

        this.takenPboto = (ImageView) findViewById(R.id.housePhoto);
        this.photo = ViewItemActivity.getBitmap();
        this.takenPboto.setImageBitmap(photo);
        this.takenPboto.setOnDragListener(dragListener);



        this.thumbnail = (ImageView) findViewById(R.id.vis_image);
        this.thumbnail.setImageResource(tree.getPhotoDrag());
        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                if (count < 6) {
                    setImageViews(count);
                } else {
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
                this.image1.setOnLongClickListener(longClickListener);
                break;
            case 2:
                this.image2.setVisibility(View.VISIBLE);
                this.image2.setOnLongClickListener(longClickListener);
                break;
            case 3:
                this.image3.setVisibility(View.VISIBLE);
                this.image3.setOnLongClickListener(longClickListener);
                break;
            case 4:
                this.image4.setVisibility(View.VISIBLE);
                this.image4.setOnLongClickListener(longClickListener);
                break;
            case 5:
                this.image5.setVisibility(View.VISIBLE);
                this.image5.setOnLongClickListener(longClickListener);
                break;

        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {


        @Override
        public boolean onDrag(View v, DragEvent event) {
            final View view = (View) event.getLocalState();
            int dragEvent = event.getAction();

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    //do nothing

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    float X = event.getX();
                    float Y = event.getY();
                    if(dragEvent == R.id.housePhoto){
//                        if (view.getId() == R.id.drag_vis1) {
                            view.animate()
                                .x(takenPboto.getX())
                                .y(takenPboto.getY())
                                .setDuration(700)
                                .start();
//                            view.setX(X);
//                            view.setY(Y);
//                            view.setVisibility(View.VISIBLE);
//                        }else if(view.getId() == R.id.drag_vis2){
//                            view.setX(X);
//                            view.setY(Y);
//                            view.setVisibility(View.VISIBLE);
////                        view.animate()
////                                .x(takenPboto.getX())
////                                .y(takenPboto.getY())
////                                .setDuration(700)
////                                .start();
//                        }
                    }
                    else if(dragEvent == R.id.trash){
//                        if (view.getId() == R.id.drag_vis1) {
//                            view.setVisibility(View.GONE);
//                        }
//                        }else if(view.getId() == R.id.drag_vis2){
                            view.setX(X);
                            view.setY(Y);
                            view.setVisibility(View.INVISIBLE);
                    }


                    break;
            }
            return true;
        }


    };

}
