package cs320.ahler.project1part2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

class DisplayGrades extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        android.content.Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        android.widget.TextView textView = findViewById(R.id.textView);
        textView.setText(message);

    }
}
