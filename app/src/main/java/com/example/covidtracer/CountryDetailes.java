package com.example.covidtracer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CountryDetailes extends AppCompatActivity {
    TextView recovery,death,active,todaycases,todaydeath,critical,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_country_detailes);

        recovery=findViewById(R.id.recovery);
        death=findViewById(R.id.death);
        active=findViewById(R.id.active);
        todaycases=findViewById(R.id.todaycases);
        todaydeath=findViewById(R.id.todaysdeath);
        critical=findViewById(R.id.critical);
        country=findViewById(R.id.country_detailed_name);

        Bundle bundle=getIntent().getExtras();
        recovery.setText(bundle.getString("recover"));
        death.setText(bundle.getString("death"));
        active.setText(bundle.getString("active"));
        todaydeath.setText(bundle.getString("todaysdeath"));
        todaycases.setText(bundle.getString("todaysCases"));
        critical.setText(bundle.getString("critical"));
        country.setText("covid 19 of: "+bundle.getString("country"));
    }
}