package com.example.rcahl.project3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String url;
    String chosenState;
    String message;
    Spinner dropdown;
    private final String[] states = new String[]{"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wyoming"};
    public final static String EXTRA_MESSAGE = "com.example.rcahl.project3.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropdown = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, states);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    public void GetState(View view) {
        Intent intent = new Intent(this, DisplayAlerts.class);

        String accro = "";
        switch (chosenState) {
            case "Alabama":
                accro = "al";
                break;
            case "Alaska":
                accro = "ak";
                break;
            case "Arizona":
                accro = "az";
                break;
            case "Arkansas":
                accro = "ar";
                break;
            case "California":
                accro = "ca";
                break;
            case "Colorado":
                accro = "co";
                break;
            case "Connecticut":
                accro = "ct";
                break;
            case "Delaware":
                accro = "de";
                break;
            case "Florida":
                accro = "fl";
                break;
            case "Georgia":
                accro = "ga";
                break;
            case "Hawaii":
                accro ="hi";
                break;
            case "Idaho":
                accro = "id";
                break;
            case "Illinois":
                accro = "il";
                break;
            case "Indiana":
                accro = "in";
                break;
            case "Iowa":
                accro = "ia";
                break;
            case "Kansas":
                accro = "ks";
                break;
            case "Kentucky":
                accro = "ky";
                break;
            case "Louisiana":
                accro = "la";
                break;
            case "Maine":
                accro = "me";
                break;
            case "Maryland":
                accro = "md";
                break;
            case "Massachusetts":
                accro = "ma";
                break;
            case "Michigan":
                accro = "mi";
                break;
            case "Minnesota":
                accro = "mn";
                break;
            case "Mississippi":
                accro = "ms";
                break;
            case "Missouri":
                accro = "mo";
                break;
            case "Montana":
                accro = "mt";
                break;
            case "Nebraska":
                accro = "ne";
                break;
            case "Nevada":
                accro = "nv";
                break;
            case "New Hampshire":
                accro = "nh";
                break;
            case "New Jersey":
                accro = "nj";
                break;
            case "New Mexico":
                accro = "nm";
                break;
            case "New York":
                accro = "ny";
                break;
            case "North Carolina":
                accro = "nc";
                break;
            case "North Dakota":
                accro = "nd";
                break;
            case "Ohio":
                accro = "oh";
                break;
            case "Oklahoma":
                accro = "ok";
                break;
            case "Oregon":
                accro = "or";
                break;
            case "Pennsylvania":
                accro = "pa";
                break;
            case "Rhode Island":
                accro = "ri";
                break;
            case "South Carolina":
                accro = "sc";
                break;
            case "South Dakota":
                accro = "sd";
                break;
            case "Tennessee":
                accro = "tn";
                break;
            case "Texas":
                accro = "tx";
                break;
            case "Utah":
                accro = "ut";
                break;
            case "Vermont":
                accro = "vt";
                break;
            case "Virginia":
                accro = "va";
                break;
            case "Washington":
                accro = "wa";
                break;
            case "West Virginia":
                accro = "wv";
                break;
            case "Wyoming":
                accro = "wy";
                break;
        }

        message = "http://alerts.weather.gov/cap/" + accro + ".php?x=0";

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        chosenState = dropdown.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        chosenState = "";
    }
}
