package cs320.ahler.project4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class DisplayExpanded  extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        Log.i("Reached new activity", "Here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expanded);

        try {
            JSONObject object = new JSONObject(getIntent().getStringExtra("JSON_DATA"));

            String callType = object.getString("kind");

            TableLayout table = findViewById(R.id.table);

            switch (callType) {
                case ("song"):
                    String songTrackName = object.getString("trackName");



                    String songArtistName = "artistName " + object.getString("artistName");
                    TextView t1 = new TextView(this);
                    t1.setText(songArtistName);
                    TableRow r1 = new TableRow(this);
                    r1.addView(t1);
                    table.addView(r1);

                    //tableRow.addView((new TextView(this)).setText(songArtistName));
                    String collectionName = "collectionName " + object.getString("collectionName");
                    TextView t2 = new TextView(this);
                    t2.setText(collectionName);
                    TableRow r2 = new TableRow(this);
                    r2.addView(t2);
                    table.addView(r2);

                    String trackPrice = "trackPrice " + object.getString("trackPrice");
                    TextView t3 = new TextView(this);
                    t3.setText(trackPrice);
                    TableRow r3 = new TableRow(this);
                    r3.addView(t3);
                    table.addView(r3);

                    String collectionPrice = "collectionPrice " + object.getString("collectionPrice");
                    TextView t4 = new TextView(this);
                    t4.setText(collectionPrice);
                    TableRow r4 = new TableRow(this);
                    r4.addView(t4);
                    table.addView(r4);

                    break;
                case ("feature-movie"):
                    String movieTrackName = "trackName " + object.getString("trackName");
                    TextView t5 = new TextView(this);
                    t5.setText(movieTrackName);
                    TableRow r5 = new TableRow(this);
                    r5.addView(t5);
                    table.addView(r5);

                    String longDescription = "longDescription " + object.getString("longDescription");
                    TextView t6 = new TextView(this);
                    t6.setText(longDescription);
                    TableRow r6 = new TableRow(this);
                    r6.addView(t6);
                    table.addView(r6);

                    String primaryGenreName = "primaryGenreName " + object.getString("primaryGenreName");
                    TextView t7 = new TextView(this);
                    t7.setText(primaryGenreName);
                    TableRow r7 = new TableRow(this);
                    r7.addView(t7);
                    table.addView(r7);

                    String trackHdPrice = "trackHdPrice " + object.getString("trackHdPrice");
                    TextView t8 = new TextView(this);
                    t8.setText(trackHdPrice);
                    TableRow r8 = new TableRow(this);
                    r8.addView(t8);
                    table.addView(r8);

                    break;
                case ("software"):
                    String softArtistName = "artistName " + object.getString("artistName");
                    TextView t9 = new TextView(this);
                    t9.setText(softArtistName);
                    TableRow r9 = new TableRow(this);
                    r9.addView(t9);
                    table.addView(r9);

                    JSONArray deviceArray = object.getJSONArray("supportedDevices");
                    String supportedDevices = "supportedDevices: ";
                    TextView t10 = new TextView(this);
                    t10.setText(supportedDevices);
                    TableRow r10 = new TableRow(this);
                    r10.addView(t10);

                    for(int i = 0; i < deviceArray.length(); ++i) {
                        String s = deviceArray.getString(i);
                        TextView textView = new TextView(this);
                        textView.setText(s);
                        r10.addView(textView);
                    }

                    if (deviceArray.length() == 0) {
                        Log.i("No len", "deviceArray");
                    }

                    table.addView(r10);

                    String description = "description " + object.getString("description");
                    TextView t11 = new TextView(this);
                    t11.setText(description);
                    TableRow r11 = new TableRow(this);
                    r11.addView(t11);
                    table.addView(r11);

                    String genres = "Genres: ";
                    JSONArray genreArray = object.getJSONArray("genres");
                    for(int i = 0; i < genreArray.length(); ++i) {
                        genres += " " + genreArray.get(i);
                    }
                    TextView t12 = new TextView(this);
                    t12.setText(genres);
                    TableRow r12 = new TableRow(this);
                    r12.addView(t12);
                    table.addView(r12);


                    JSONArray imageUrlArray = object.getJSONArray("screenshotUrls");
                    TableRow r13 = new TableRow(this);

                    for (int i = 0; i < imageUrlArray.length(); ++i) {
                        ImageView iv = new ImageView(this);

                        new DownloadImageTask(iv).execute(imageUrlArray.getString(i));

                        r13.addView(iv);
                    }

                    if (imageUrlArray.length() == 0) {
                        Log.i("len", String.valueOf(imageUrlArray.length()));
                    }

                    table.addView(r13);

                    break;
            }

            //finish();
        } catch (JSONException e) {
            e.printStackTrace();
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
