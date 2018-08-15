package cs320.ahler.project4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    ProgressBar progressBar;
    private String content;
    EditText searchText;
    private String url;
    static final String API_BASE_CALL = "https://itunes.apple.com/search?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.contentSpinner);
        searchText = (EditText) findViewById(R.id.searchText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.content_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        content = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        content = "";
    }

    private class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... urls) {
            String search = searchText.getText().toString();
            search = search.replace(' ', '+');

            Log.i("Search", search);
            Log.i("Content", content);
            String string_url = new CreateCallUrl(search, content).returnString();
            Log.i("Final URL", string_url);

            URL url = null;
            //Occasionally get errors when trying to connect to the internet.
            try {
                url = new URL(string_url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            assert url != null;
            Log.i("URL url", url.toString());
            URLConnection urlConnection= null;
            try {
                urlConnection = url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream is = null;
            try {
                assert urlConnection != null;
                is = urlConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert is != null;
            InputStreamReader reader = new InputStreamReader(is);

            try (BufferedReader in = new BufferedReader(reader)) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                return sb.toString().trim();
            }catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "ERROR";
            }
            Log.i("Response", response);
            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(getBaseContext(), DisplayList.class);

            try {
                new JSONObject(response);
            } catch (JSONException ex) {
                intent.putExtra("JSON_DATA", "NO");
                Log.i("Reached new activity", "WITHOUT JSON");
                startActivity(intent);
            }


            try {
                //Getting software has occasionally caused the program to crash with no error messege
                JSONObject reader = new JSONObject(response);
                intent.putExtra("JSON_DATA", reader.toString());

                Log.i("Reached new activity", "Here");
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private class CreateCallUrl {

            String url;
            CreateCallUrl(String s, String c) {
                String search = "term=" + s;
                String content = "&media=" + c.toLowerCase();
                url = API_BASE_CALL + search + content;
            }

            String returnString() {
                return url;
            }
        }
    }
}
