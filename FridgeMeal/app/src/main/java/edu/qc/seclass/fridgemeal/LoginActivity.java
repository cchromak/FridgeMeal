package edu.qc.seclass.fridgemeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnSignUp;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser newUser = new ParseUser();
                String username = etUsername.getText().toString();
                String password = etUsername.getText().toString();

                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Must input both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Login user
                    login(username, password);

                }
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser newUser = new ParseUser();
                String username = etUsername.getText().toString();
                String password = etUsername.getText().toString();

                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Must input both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Login user
                    signUp(newUser, username, password);

                }
            }
        });
    }

    public void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Toast.makeText(mContext, "Issue with login", Toast.LENGTH_LONG).show();
                    return;
                }
                //Go to main activity
                Intent i = new Intent(mContext, MainActivity.class);
                startActivity(i);
                Toast.makeText(mContext, "Successful login", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signUp(ParseUser newUser, String username, String password){
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Toast.makeText(mContext, "Issue with login", Toast.LENGTH_LONG).show();
                    return;
                }
                //Go to main activity if successful
                Intent i = new Intent(mContext, MainActivity.class);
                startActivity(i);
                Toast.makeText(mContext, "Successful sign up", Toast.LENGTH_LONG).show();
            }
        });
    }
}