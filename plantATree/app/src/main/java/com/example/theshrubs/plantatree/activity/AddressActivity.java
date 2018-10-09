package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Address;


public class AddressActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean delivery;
    private int USER_ID;
    private double TOTAL_COST;

    private EditText streetNumber;
    private EditText streetName;
    private EditText suburb;
    private EditText city;
    private EditText postCode;
    private Button nextButton;
    private Button backButton;
    private DatabaseHelper dbHelper;
    private boolean returningUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);
        delivery = false;

        Bundle extras = getIntent().getExtras();
        USER_ID = extras.getInt("USER_ID");
        this.dbHelper = new DatabaseHelper(this);

        this.streetNumber = findViewById(R.id.streetNumber);
        this.streetName = findViewById(R.id.streetName);
        this.suburb = findViewById(R.id.suburb);
        this.city = findViewById(R.id.city);
        this.postCode = findViewById(R.id.postcode);

        this.nextButton = findViewById(R.id.AddressContinueButton);
        this.backButton = findViewById(R.id.AddressBackButton);

        this.nextButton.setOnClickListener(this);
        this.backButton.setOnClickListener(this);
        setFields();

    }

    public void setFields(){
        System.out.println(" addressObject," );Object obj = dbHelper.findHandle(USER_ID, "Address");
        if(obj != null){
            Address address = (Address) obj;

            streetNumber.setText(address.getStreetNumber());
            streetName.setText(address.getStreeName());
            suburb.setText(address.getSuburb());
            city.setText(address.getCity());
            postCode.setText(address.getPostcode());

            returningUser = true;
        }else{
            returningUser = false;
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AddressContinueButton){
            int sNumber = 0;
            int post_Number = 0;
            String street_number = streetNumber.getText().toString().trim();
            String sName = streetNumber.getText().toString().trim();
            String sub = suburb.getText().toString().trim();
            String cit = city.getText().toString().trim();
            String post = postCode.getText().toString().trim();


            if(street_number.isEmpty() || sName.isEmpty() || sub.isEmpty() || cit.isEmpty() || post.isEmpty() ){
                setToast("Please ensure all fields are filled out");
            }else{
                if(!returningUser){
                    sNumber = Integer.parseInt(street_number);
                    post_Number = Integer.parseInt(post);
                    Address address = new Address(sNumber, sName, sub, cit, post_Number);
                    dbHelper.addHandle(address);
                }


                Intent intent = new Intent(AddressActivity.this, PaymentActivity.class);
                intent.putExtra("USER_ID", USER_ID);
                intent.putExtra("TOTAL_COST", TOTAL_COST);
                startActivity(intent);
            }




        }else if(v.getId() == R.id.AddressBackButton){
            finish();
        }

    }

    public void setToast(String message){
        Toast showToast = Toast.makeText(AddressActivity.this, message, Toast.LENGTH_SHORT);
        showToast.show();
    }
}
