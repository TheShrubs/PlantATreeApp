package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;

public class PickupActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private int USER_ID;
    private double TOTAL_COST;

    private RadioButton pickupRadioButton;
    private RadioButton deliveryRadioButton;
    private Button nextButton;
    private Button backButton;
    private BottomNavigationMenu navigationControl;
    private BottomNavigationView navigationView;
    private Spinner spinner;
    private ImageView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup);

//        string array is located in values/strings called addresses - referenced from there
//        String[] address = {"22 Symonds St", "106 Great North Road", "43 Titirangi Road"};
        spinner = (Spinner) findViewById(R.id.spinner);
        //use an arrayAdapter to put string inside Spinner, pass in this context, the array from resources and a simple spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.addresses, android.R.layout.simple_spinner_item);
        //have to pass the layout for the drop down - so pass in another simple layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //pass the adapter
        spinner.setAdapter(adapter);
        //set On Item selected listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        Bundle extras = getIntent().getExtras();
        USER_ID = extras.getInt("USER_ID");
        TOTAL_COST = extras.getDouble("TOTAL_COST");

        navigationView = (BottomNavigationView) findViewById(R.id.address_Navigation);
        navigationControl = new BottomNavigationMenu();
        navigationControl.getBottomNavigation(this, navigationView, USER_ID);

        pickupRadioButton = (RadioButton) findViewById(R.id.pickupRadioButton);
        deliveryRadioButton = (RadioButton) findViewById(R.id.deliveryRadioButton);
        pickupRadioButton.setChecked(true);
        this.deliveryRadioButton.setOnClickListener(this);

        this.nextButton = findViewById(R.id.PickupContinueButton);
        this.backButton = findViewById(R.id.PickupBackButton);

        this.nextButton.setOnClickListener(this);
        this.backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.PickupContinueButton) {
                Toast.makeText(this, "Pick up confirmed.", Toast.LENGTH_SHORT).show();
        } else if(v.getId() == R.id.deliveryRadioButton){
            pickupRadioButton.setChecked(false);
            Intent intent = new Intent(PickupActivity.this, AddressActivity.class);
            intent.putExtra("USER_ID", USER_ID);
            intent.putExtra("TOTAL_COST", TOTAL_COST);
            startActivity(intent);
        }
        else if(v.getId() == R.id.PickupBackButton){
            pickupRadioButton.setChecked(false);
            Intent intent = new Intent(PickupActivity.this, PaymentActivity.class);
            intent.putExtra("USER_ID", USER_ID);
            intent.putExtra("TOTAL_COST", TOTAL_COST);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        map = (ImageView) findViewById(R.id.map);
        if(parent.getItemAtPosition(position).toString().equals("22 Symonds St")){
            map.setImageResource(R.drawable.symondsst);
            //Toast.makeText(this, "Symonds confirmed.", Toast.LENGTH_SHORT).show();
        }
        if(parent.getItemAtPosition(position).toString().equals("106 Great North Road")){
            map.setImageResource(R.drawable.greatnorthroad);
            //Toast.makeText(this, "Great North Rd confirmed.", Toast.LENGTH_SHORT).show();
        }
        if(parent.getItemAtPosition(position).toString().equals("43 Titirangi Road")){
            map.setImageResource(R.drawable.titirangiroad);
            //Toast.makeText(this, "Titirangi confirmed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
