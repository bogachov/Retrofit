package com.example.fructus.retrofit;



import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;


public interface Link {
    //@FormUrlEncoded
    @GET("/api/v1.5/tr.json/translate")
    //Call<Object> Read(@FieldMap Map<String,String> map);
    Call <Object> Read (@QueryMap Map<String,String> map);
}
