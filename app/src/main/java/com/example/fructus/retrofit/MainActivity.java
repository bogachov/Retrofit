package com.example.fructus.retrofit;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String URL ="https://translate.yandex.net";
    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(URL)
        .build();

    private Link intf = retrofit.create(Link.class);
    private final String KEY = "trnsl.1.1.20160124T203710Z.efeae9fa7d11b60b.a778df7a6360ec8b746bea3e60c536ebd3dc378a";

    private EditText text;
    private TextView transleted;
    private  Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(this);

        text = (EditText)findViewById(R.id.editText);
        transleted = (TextView)findViewById(R.id.textView);

    }

    @Override
    public void onClick(View v) {
        //TextView txt = (TextView)findViewById(R.id.listView);
        switch (v.getId()){
            case R.id.button:
                Map<String,String> mapJson = new HashMap<String,String>();
                mapJson.put("key",KEY);
                mapJson.put("text",text.getText().toString());
                mapJson.put("lang","en-ru");

                Call<Object> call = intf.Read(mapJson);

                try {
                    retrofit2.Response<Object> response = call.execute();
                    Map<String,String> map = gson.fromJson(response.body().toString(),Map.class);

                    for (Map.Entry e: map.entrySet()){
                        System.out.println(e.getKey() + " " + e.getValue());

                        if (e.getKey().equals("text")) {
                            System.out.println(e.getValue().getClass().getName());

                            ArrayList item = (ArrayList) e.getValue();
                            transleted.setText(item.get(0).toString());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }







                /*//Map<String,String> mapJson = new HashMap<String,String>();
                //mapJson.put()
                Call<List<Object>> call = intf.Read(*//*mapJson*//*);

                try {
                    retrofit2.Response<List<Object>> response = call.execute();
                    Log.d(MainActivity.class.getSimpleName(), "text");

                    //response.body().toArray();
                    HashMap map = new HashMap();


                    //Map<String,String> map = gson.fromJson(response.body().toString(),Map.class);

                    //for(Map.Entry e : map.entrySet()){
                    //    txt.setText(e.getKey()+" "+e.getValue());
                    //response.body().toArray().toString();


                    ArrayList<String> spisok  = new ArrayList<String>();
                    //spisok.add(response.toString());


                    Iterator iterator = response.body().iterator();

                    while (iterator.hasNext()) {
                        spisok.add(iterator.next());
                    }

                    // находим список
                    ListView lvMain = (ListView) findViewById(R.id.listView);

                    // создаем адаптер
                    if (response != null){
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                android.R.layout.simple_list_item_1, spisok);

                        // присваиваем адаптер списку
                        lvMain.setAdapter(adapter);
                    }
                    //}
                } catch (IOException e) {
                    e.printStackTrace();
                }*/


        }

    }
}
