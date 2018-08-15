package cs320.ahler.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ParkingActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "cs320.ahler.project1part1.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
    }

    public void calcParking(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        if (!message.matches("^[0-9]+$")) {
            message = "Please enter an integer";
        } else {
            double hours = Double.valueOf(message);
            double cost;

            if (hours <= 3) {
                cost = 5;
            } else {
                cost = 5 + 1.5*hours;
            }

            if (cost > 18.00) {
                cost = 18.00;
            }

            message = "Parking costs $" + cost + "0";
        }

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}
