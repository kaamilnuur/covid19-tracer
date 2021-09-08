package com.example.covidtracer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class   MainActivity extends AppCompatActivity {
     TextView  totalCases,totalDeath,totalRecovery,totalCritical;
     Button searchCountry;
     TextView lan_cases,lan_death,lan_recovery,lan_critcal;
     EditText search;
     ImageView change_language;
     int seleted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadlocale();
        setContentView(R.layout.activity_main);
        totalCases=findViewById(R.id.totalcases);
        totalRecovery=findViewById(R.id.totalrecovey);
        totalDeath=findViewById(R.id.totaldeath);
        totalCritical=findViewById(R.id.totalcritical);
        searchCountry=findViewById(R.id.searcCountry);
        change_language=findViewById(R.id.language);
        lan_cases=findViewById(R.id.cases);
        lan_critcal=findViewById(R.id.criticals);
        lan_death=findViewById(R.id.deathies);
        lan_recovery=findViewById(R.id.recoveries);
      change_language.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final  String[] languag={"English","somali "};
              final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
             builder.setTitle(R.string.change);
             builder.setItems(languag, new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     if (which==0){
                         setLocale("en");
                         recreate();
                     }
                     if (which==1){
                         setLocale("so");
                         recreate();
                     }
                 }
             });
             builder.show();
          }

      });

        searchCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CountyActivity.class);
                startActivity(intent);
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        String url="https://disease.sh/v3/covid-19/all";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    totalCases.setText(jsonObject.getString("cases"));
                    totalDeath.setText(jsonObject.getString("deaths"));
                    totalCritical.setText(jsonObject.getString("critical"));
                    totalRecovery.setText(jsonObject.getString("recovered"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,R.string.toast, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
    private void setLocale(String lang) {
       Locale locale=new Locale(lang);
       Locale.setDefault(locale);
       Configuration configuration=new Configuration();
       configuration.locale=locale;
       getBaseContext().getResources().updateConfiguration(configuration,
               getBaseContext().getResources().getDisplayMetrics());
      SharedPreferences.Editor editor=getSharedPreferences("save data",MODE_PRIVATE).edit();
      editor.putString("lang",lang);
      editor.apply();
    }
   public void loadlocale(){
        SharedPreferences preferences=getSharedPreferences("save data",Activity.MODE_PRIVATE);
        String language=preferences.getString("lang","");
        setLocale(language);
   }
}