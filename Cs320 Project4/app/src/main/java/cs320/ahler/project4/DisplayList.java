package cs320.ahler.project4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DisplayList extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("Here","Even though array is empty" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        TableLayout table = findViewById(R.id.table);

        String comp = getIntent().getStringExtra("JSON_DATA");

        try {
            JSONObject reader = new JSONObject(getIntent().getStringExtra("JSON_DATA"));
            JSONArray jArray = reader.getJSONArray("results");
            String len = "The length is " + jArray.length();
            Log.i("Array", len);

            if (jArray.length() == 0 ) {
                TableRow tableRow = new TableRow(this);
                TextView textView = new TextView(this);

                Log.i("Comp string", comp );

                String string = "Search returned no results";
                textView.setText(string);
                tableRow.addView(textView);
                table.addView(tableRow);
            } else {
                for (int i = 0; i < jArray.length(); i++) {

                    TableRow tableRow = new TableRow(this);

                    final JSONObject object = jArray.getJSONObject(i);
                    String callType = object.getString("kind");

                    switch (callType) {
                        case ("software"):
                            String artistName = object.getString("artistName");
                            artistName = "artistName: " + artistName;
                            TextView artV = new TextView(this);
                            artV.setPadding(10, 10, 10, 10);
                            artV.setText(artistName);

                            String price = object.getString("price");
                            price = "Price: " + price;
                            TextView priceV = new TextView(this);
                            priceV.setPadding(10, 10, 10, 10);
                            priceV.setText(price);

                            LinearLayout soft_layout = new LinearLayout(this);
                            soft_layout.setOrientation(LinearLayout.VERTICAL);
                            soft_layout.addView(artV);
                            soft_layout.addView(priceV);
                            tableRow.addView(soft_layout);

                            break;
                        case ("song"):
                            String songTrackName = object.getString("trackName");
                            songTrackName = "trackName: " + songTrackName;
                            TextView songTrackV = new TextView(this);
                            songTrackV.setPadding(10, 10, 10, 10);
                            songTrackV.setText(songTrackName);

                            String collectionName = object.getString("collectionName");
                            collectionName = "collectionName: " + collectionName;
                            TextView colV = new TextView(this);
                            colV.setPadding(10, 10, 10, 10);
                            colV.setText(collectionName);

                            String songImageURL = object.getString("artworkUrl30");
                            Log.i("Song Image URL", songImageURL);
                            ImageView song_iv = new ImageView(this);

                            new DownloadImageTask(song_iv).execute(songImageURL);

                            tableRow.addView(song_iv);
                            LinearLayout song_layout = new LinearLayout(this);
                            song_layout.setOrientation(LinearLayout.VERTICAL);
                            song_layout.addView(songTrackV);
                            song_layout.addView(colV);
                            tableRow.addView(song_layout);

                            break;
                        case ("feature-movie"):
                            String movieTrackName = object.getString("trackName");
                            movieTrackName = "trackName: " + movieTrackName;
                            TextView movieTrackV = new TextView(this);
                            movieTrackV.setPadding(10, 10, 10, 10);
                            movieTrackV.setText(movieTrackName);

                            String contentAdvisoryWarning = object.getString("contentAdvisoryRating");
                            contentAdvisoryWarning = "contentAdvisoryWarning: " + contentAdvisoryWarning;
                            TextView conV = new TextView(this);
                            conV.setPadding(10, 10, 10, 10);
                            conV.setText(contentAdvisoryWarning);

                            String movieImageURL = object.getString("artworkUrl30");
                            Log.i("Movie Image URL", movieImageURL);
                            ImageView movie_iv = new ImageView(this);

                            new DownloadImageTask(movie_iv).execute(movieImageURL);

                            tableRow.addView(movie_iv);
                            LinearLayout movie_layout = new LinearLayout(this);
                            movie_layout.setOrientation(LinearLayout.VERTICAL);
                            movie_layout.addView(movieTrackV);
                            movie_layout.addView(conV);
                            tableRow.addView(movie_layout);

                            break;
                    }

                    final int finalI = i;
                    tableRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String s = "index " + finalI;
                            Log.i("Click listener", s);

                            String objectString = object.toString();
                            Intent intent = new Intent(getBaseContext(), DisplayExpanded.class);
                            intent.putExtra("JSON_DATA", objectString);
                            startActivity(intent);
                        }
                    });

                    table.addView(tableRow);
                }
            }
        } catch (JSONException ignored) {
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
