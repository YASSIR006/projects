package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class insert_Activity extends AppCompatActivity {
    EditText name, desig;
    Button  btSubmit;
    String name1,desig1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        name = findViewById(R.id.edt_name);
        desig = findViewById(R.id.edt_desig);

        btSubmit = (Button) findViewById(R.id.btn_submit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(valid_data() ) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "name";
                            field[1] = "desig";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = name1;
                            data[1] = desig1;

                            PutData putData = new PutData("http://192.168.1.103/LoginRegister/db_insert.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    if(result.equals("Success")){
                                        Toast.makeText(insert_Activity.this,result, Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }


                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(insert_Activity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
    public boolean valid_data() {
         name1=name.getText().toString();
       desig1=desig.getText().toString();


        if (!name1.equals("") && !desig1.equals("") ) {
            return true;

        } else {

            return false;

        }
    }



    }
