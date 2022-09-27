package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recview;
    Button move;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recview=findViewById(R.id.recview);
        move=findViewById(R.id.move);

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),insert_Activity.class);
                startActivity(intent);
                finish();

            }
        });


        recview.setLayoutManager(new LinearLayoutManager(this));
        getdata();
    }
    public void getdata(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.103/LoginRegister/")
                // on below line we are calling add
                // Converter factory as Gson converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        apiset retrofitAPI = retrofit.create(apiset.class);

        Call<ArrayList<responsemodel>> call= retrofitAPI.getdata();
        call.enqueue(new Callback<ArrayList<responsemodel>>() {
            @Override
            public void onResponse(Call<ArrayList<responsemodel>> call, Response<ArrayList<responsemodel>> response) {


                ArrayList<responsemodel> data=response.body();
                myadapter adapter=new myadapter();
                adapter.setdata(data);
                recview.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<responsemodel>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

}