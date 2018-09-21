package com.example.theshrubs.plantatree.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.models.Tree;

import java.util.ArrayList;

public class LandingPageActivity extends AppCompatActivity {

    ListView List;
    LandingPageAdapter adapter;
    String[] title;
    double[] price;
    int[] icon;
    int[] id;
    ArrayList<Tree> arrayList = new ArrayList<Tree>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Search");

        id = new int[]{0,1,2,3};
        title = new String[]{"Pine tree", "Apple tree", "Maple tree", "Oak tree"};
        price = new double[]{14.99, 24.99, 34.95, 89.99};//"$14.99", "$24.99", "$34.95", "$89.99"};
        icon = new int[]{R.drawable.tree_pine, R.drawable.tree_apple, R.drawable.tree_maple, R.drawable.tree_oak};

        List = (ListView) findViewById(R.id.treeList);

        for(int i = 0; i < title.length; i++){
            Tree model = new Tree(id[i], title[i], Double.valueOf(price[i]), icon[i]);
            //bind all strings in an array
            arrayList.add(model);
        }

        //pass results to listViewAdapter class
        adapter = new LandingPageAdapter(this, arrayList);

        //bind the adapter to the listview
        List.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    adapter.filter("");
                    List.clearTextFilter();
                }else {
                    adapter.filter(newText);
                }
                return false;
            }
        });

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id==R.id.action_settings){
//            //do your functionality here
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

//Design landing_page_item of listView
//Adding menu to searchView in actionbar - Res|new android Resource Dir|Menu|set to menu
//Add model class
//Add adapter class
//Add some images in drawable
//Run project to test the listView and searchView
//If working handle the itemClick to new activity with action bar and some data
//Change actionbar title of both activities
//Add back button in action bar of new activity|manifest set parent activity
//Handle item clicks

