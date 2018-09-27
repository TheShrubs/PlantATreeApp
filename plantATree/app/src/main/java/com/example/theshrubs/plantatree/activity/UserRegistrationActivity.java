package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.User;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPasswordd;
    private Button registerButton;
    private TextView loginLink;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);

        dbHelper = new DatabaseHelper(this);
        this.username = (EditText) findViewById(R.id.reg_name);
        this.email = (EditText) findViewById(R.id.reg_email);
        this.password = (EditText) findViewById(R.id.reg_password);
        this.confirmPasswordd = (EditText) findViewById(R.id.reg_confirmPassword);
        this.registerButton = (Button) findViewById(R.id.reg_Button);
        this.loginLink = (TextView) findViewById(R.id.login_Link);


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String userEmail = email.getText().toString().trim();
                if (!userEmail.contains("@") || (!userEmail.contains(".com"))) {
                    email.setError("Email must be valid!");
                }
            }
        });
        registerButton.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_Link) {
            Intent intent = new Intent(UserRegistrationActivity.this, UserLoginActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.reg_Button) {
            String name = this.username.getText().toString().trim();
            String userEmail = this.email.getText().toString().trim();
            String password = this.password.getText().toString().trim();
            String confrim = this.confirmPasswordd.getText().toString().trim();
            if (!password.equals(confrim)) {
                setToast("Passwords do not match");
            } else {
                dbHelper.setUser(userEmail, password);
                Object obj = dbHelper.findHandle(1, "findExistingUser");
                if (obj != null) {
                    User foundUser = (User) obj;
                    if (foundUser.getUserEmail().equals(userEmail)) {
                        setToast("Email already exists");
                    }
                } else {
                    setToast("Registration Successful");
                    User user = new User(name, userEmail, password);
                    dbHelper.addHandle(user);
                    Intent intent = new Intent(UserRegistrationActivity.this, UserLoginActivity.class);
                    startActivity(intent);
                }
            }
        }

    }

    private void setToast(String message) {
        Toast messageToast = Toast.makeText(UserRegistrationActivity.this, message, Toast.LENGTH_SHORT);
        messageToast.show();
    }
}
