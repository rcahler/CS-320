package cs320.ahler.project2part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private String car;
    private static final String[] cars = {"Economy","Compact","Intermediate","Standard","Full-size"};
    public final static String EXTRA_MESSAGE = "cs320.ahler.project2part2.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,cars);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void CalCar(View view) {
        Intent intent = new Intent(this, DisplayTHR.class);
        String message = "";
        EditText text = findViewById(R.id.editText1);
        String daySting = text.getText().toString();
        if (!daySting.matches("") && daySting.matches("^[0-9]+$")) {
            int days = Integer.parseInt(daySting);
            double cost = 0;
            int index = 0;
            for (int i = 0; i < cars.length; i++) {
                if (car.equalsIgnoreCase(cars[i])) {
                    index = i;
                }
            }

            switch (index) {
                case 0: cost = 24.99;
                        break;
                case 1: cost = 29.99;
                    break;
                case 2: cost = 39.99;
                    break;
                case 3: cost = 44.99;
                    break;
                case 4: cost = 49.99;
                    break;
            }

            message = "The " + car + " will cost " + days*cost + " over the " + days + " days";


        } else {
            message = "Please enter valid information";
        }

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        car = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        car = "";
    }
}
