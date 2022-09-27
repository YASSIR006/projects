package com.example.project;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.Call;
public interface apiset {
    @GET("json_user_fetch.php")
    Call<ArrayList<responsemodel>>getdata();



}
