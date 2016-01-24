package com.example.fructus.retrofit;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String URL ="https://api.github.com";
    private Gson gson = new GsonBuilder().create();


    private Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(URL)
        .build();

    private Link intf = retrofit.create(Link.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        TextView txt = (TextView)findViewById(R.id.textView);
        switch (v.getId()){
            case R.id.button:
                //Map<String,String> mapJson = new HashMap<String,String>();
                //mapJson.put()
                Call<List<Object>> call = intf.Read(/*mapJson*/);

                try {
                    retrofit2.Response<List<Object>> response = call.execute();
                    Log.d(MainActivity.class.getSimpleName(), "text");

                    //Map<String,String> map = gson.fromJson(response.body().toString(),Map.class);

                    //for(Map.Entry e : map.entrySet()){
                    //    txt.setText(e.getKey()+" "+e.getValue());

                    //}
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
}
