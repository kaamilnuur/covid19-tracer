package com.example.covidtracer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Filter;
public class CountyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    recyclerViewadapter adapter;
    ArrayList<model>arrayList=new ArrayList<>();
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_county);
        recyclerView=findViewById(R.id.recycler_view);
        progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        EditText  searching=findViewById(R.id.search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String url="https://corona.lmao.ninja/v2/countries";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        JSONObject flagobject=jsonObject.getJSONObject("countryInfo");
                        arrayList.add(new model(flagobject.getString("flag"),
                                jsonObject.getString("country")
                                ,jsonObject.getString("recovered"),
                                jsonObject.getString("deaths"),
                                jsonObject.getString("todayDeaths"),
                                jsonObject.getString("cases"),
                                jsonObject.getString("active")
                                ,jsonObject.getString("todayCases")));
                    }
                     adapter=new recyclerViewadapter(arrayList,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            model model=arrayList.get(position);
                            Intent intent=new Intent(getApplicationContext(),CountryDetailes.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("recover",model.getRecovery());
                            bundle.putString("todaysCases",model.getTodayscases());
                            bundle.putString("todaysdeath",model.getTodaydeath());
                            bundle.putString("critical",model.getCritical());
                            bundle.putString("active",model.getActive());
                            bundle.putString("death",model.getDeath());
                            bundle.putString("death",model.getDeath());
                            bundle.putString("country",model.getCounrtyName());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        @Override
                        public void onLongItemClick(View view, int position) {
                        }
                    }));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CountyActivity.this,R.string.toast, Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);

        searching.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                 filter(s.toString());
            }
        });
    }
    private void filter(String text) {
        ArrayList<model> filterlist=new ArrayList<>();
        for (model item  :  arrayList){
            if (item.getCounrtyName().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(item);
            }
        }
       adapter.filters(filterlist);
    }
}