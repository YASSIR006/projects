package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {
    TextInputEditText textInputEditTextfullname,textInputEditTextusername,textInputEditTextpassword,textInputEditTextemail;
    Button buttonsignup;
    TextView textViewlogin;
    String username,fullname,password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textInputEditTextfullname=findViewById(R.id.fullname);
        textInputEditTextusername=findViewById(R.id.username);
        textInputEditTextpassword=findViewById(R.id.password);
        textInputEditTextemail=findViewById(R.id.email);
        buttonsignup=findViewById(R.id.buttonSignUp);
        textViewlogin=findViewById(R.id.loginText);
        textViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();

            }
        });
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(valid_data()) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData("http://192.168.1.103/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(SignUp.this,result, Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(SignUp.this,result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public boolean valid_data() {
        username = textInputEditTextusername.getText().toString();
        fullname=textInputEditTextfullname.getText().toString();
        password=textInputEditTextpassword.getText().toString();
        email=textInputEditTextemail.getText().toString();

        if (!fullname.equals("") && !username.equals("") && !password.equals("")&& !email.equals("")) {
            return true;

        } else {

            return false;

        }
    }
}
