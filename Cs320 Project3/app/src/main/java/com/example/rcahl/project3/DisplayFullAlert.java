package com.example.rcahl.project3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayFullAlert extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_alerts_full);

        android.content.Intent intent = getIntent();
        Entry entry = intent.getParcelableExtra(DisplayAlerts.EXTRA_MESSAGE);

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView8 = findViewById(R.id.textView8);

        textView1.setText("Event: " + entry.event);
        textView2.setText("Summary: " + entry.summary);
        textView3.setText("Effective: " + entry.effective);
        textView4.setText("Expires: " + entry.expires);
        textView5.setText("Urgency: " + entry.urgency);
        textView6.setText("Severity: " + entry.severity);
        textView7.setText("Certainty: " + entry.certainty);
        textView8.setText("Link: " + entry.link);
    }
}
