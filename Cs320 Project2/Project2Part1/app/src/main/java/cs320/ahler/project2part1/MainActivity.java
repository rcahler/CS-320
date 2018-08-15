package cs320.ahler.project2part1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "cs320.ahler.project2part1.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findTHR(View view) {
        Intent intent = new Intent(this, DisplayTHR.class);
        String message;

        EditText rateText = findViewById(R.id.editText1);
        EditText ageText = findViewById(R.id.editText2);

        String ageString = ageText.getText().toString();
        String rateString = rateText.getText().toString();
        if (ageString.matches("") || rateString.matches("")) {
            message = "Please enter data";
        } else if (!ageString.matches("^[0-9]+$") || !rateString.matches("^[0-9]+$")) {
            message = "Please enter numeric values";
        } else {
            int age = Integer.parseInt(ageString);
            int rRate = Integer.parseInt(rateString);
            double THR = (((220 - age) - rRate) * 0.6) + rRate;

            message = "THR is: " + THR;
        }

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
