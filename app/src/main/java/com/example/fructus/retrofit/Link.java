package com.example.fructus.retrofit;



import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface Link {
    //@FormUrlEncoded
    @GET("/orgs/droidlabs/repos")
    //Call<Object> Read(@FieldMap Map<String,String> map);
    Call <List<Object>> Read (/*@FieldMap Map<String,String> map*/);
}
