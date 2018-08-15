package cs320.ahler.project2part3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner discountSpinner;
    private Spinner priceSpinner;

    private String chosenProc = "";
    private double discount = 0;

    private static final String[] discounts = {"0","10","20"};
    private static final String[] proc = {"Makeover", "Hair Styling", "Manicure", "Permanent Makeup"};

    public final static String EXTRA_MESSAGE = "cs320.ahler.project2part3.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discountSpinner = findViewById(R.id.dropdown1);
        priceSpinner = findViewById(R.id.dropdown2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, discounts);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, proc);


        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        discountSpinner.setAdapter(adapter1);
        discountSpinner.setOnItemSelectedListener(this);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceSpinner.setAdapter(adapter2);
        priceSpinner.setOnItemSelectedListener(this);

    }

    public void CalCar(View view) {
        Intent intent = new Intent(this, DisplayTHR.class);
        String message = "";

        int index = 0;
        for (int i = 0; i < proc.length; i++) {
            if (chosenProc.equalsIgnoreCase(proc[i])) {
                index = i;
            }
        }

        double cost = 0;
        switch (index) {
            case 0: cost = 125;
                break;
            case 1: cost = 60;
                break;
            case 2: cost = 35;
                break;
            case 3: cost = 200;
                break;
            case 4: cost = 49.99;
                break;
        }

        double multiple = (1 - (discount * 0.01));
        message = chosenProc + " will cost " + cost * multiple + " with a " + discount + " percent discount";



        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        chosenProc = priceSpinner.getSelectedItem().toString();
        discount = Double.parseDouble(discountSpinner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        chosenProc = "";
        discount = 0;
    }
}
