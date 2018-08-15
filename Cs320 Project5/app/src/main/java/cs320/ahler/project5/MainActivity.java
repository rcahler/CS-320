package cs320.ahler.project5;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> list = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json_string = null;
        try {
            InputStream is = this.getAssets().open("students.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json_string = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Log.i("Result", json_string);

        try {
            JSONArray json_array = new JSONArray(json_string);
            for (int i = 0; i < json_array.length(); ++i) {
                JSONObject json = json_array.getJSONObject(i);
                String fn = (String) json.get("firstName");
                Log.i("firstName", fn);

                String ln = (String) json.get("lastName");
                Log.i("lastName", ln);

                String y = (String) json.get("year");
                Log.i("year", y);

                Double g = (Double) json.get("gpa");
                Log.i("gpa", String.valueOf(g));

                list.add(new Student(fn, ln, y, g));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("List size", String.valueOf(list.size()));

        final TableLayout table = findViewById(R.id.table);

        TableRow header = new TableRow(this);

        TextView fnView = new TextView(this);
        fnView.setPadding(2,2,2,2);
        fnView.setText("First Name");
        fnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Sorts based on First Names
                Log.i("Click", "fn");
                Collections.sort(list, new Comparator<Student>() {
                    @Override
                    public int compare(Student student, Student t1) {
                        return student.getFirstName().compareTo(t1.getFirstName());
                    }
                });

                int count = table.getChildCount();
                for (int i = 1; i < count; i++) {
                    View child = table.getChildAt(i);
                    if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                }

                for (int i = 0; i < list.size(); ++i) {
                    TableRow row = new TableRow(getApplicationContext());

                    TextView fn = new TextView(getApplicationContext());
                    fn.setText(list.get(i).getFirstName());
                    row.addView(fn);

                    TextView ln = new TextView(getApplicationContext());
                    ln.setText(list.get(i).getLastName());
                    row.addView(ln);

                    TextView y = new TextView(getApplicationContext());
                    y.setText(list.get(i).getYear());
                    row.addView(y);

                    TextView g = new TextView(getApplicationContext());
                    Double d = list.get(i).getGpa();
                    g.setText(d.toString());
                    row.addView(g);

                    table.addView(row);
                }

            }
        });

        TextView lnView = new TextView(this);
        lnView.setPadding(2,2,2,2);
        lnView.setText("Last Name");
        lnView.setOnClickListener(new View.OnClickListener() { //Sorts based on Last Names
            @Override
            public void onClick(View view) {
                Log.i("Click", "ln");
                Collections.sort(list, new Comparator<Student>() {
                    @Override
                    public int compare(Student student, Student t1) {
                        return student.getLastName().compareTo(t1.getLastName());
                    }
                });

                int count = table.getChildCount();
                for (int i = 1; i < count; i++) {
                    View child = table.getChildAt(i);
                    if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                }

                for (int i = 0; i < list.size(); ++i) {
                    TableRow row = new TableRow(getApplicationContext());

                    TextView fn = new TextView(getApplicationContext());
                    fn.setText(list.get(i).getFirstName());
                    row.addView(fn);

                    TextView ln = new TextView(getApplicationContext());
                    ln.setText(list.get(i).getLastName());
                    row.addView(ln);

                    TextView y = new TextView(getApplicationContext());
                    y.setText(list.get(i).getYear());
                    row.addView(y);

                    TextView g = new TextView(getApplicationContext());
                    Double d = list.get(i).getGpa();
                    g.setText(d.toString());
                    row.addView(g);

                    table.addView(row);
                }
            }
        });

        TextView yView = new TextView(this);
        yView.setPadding(2,2,2,2);
        yView.setText("Year");
        yView.setOnClickListener(new View.OnClickListener() { //Sorts based on year
            @Override
            public void onClick(View view) {
                Log.i("Click", "y");
                Collections.sort(list, new Comparator<Student>() {
                    @Override
                    public int compare(Student student, Student t1) {
                        return student.getYear().compareTo(t1.getYear());
                    }
                });

                int count = table.getChildCount();
                for (int i = 1; i < count; i++) {
                    View child = table.getChildAt(i);
                    if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                }

                for (int i = 0; i < list.size(); ++i) {
                    TableRow row = new TableRow(getApplicationContext());

                    TextView fn = new TextView(getApplicationContext());
                    fn.setText(list.get(i).getFirstName());
                    row.addView(fn);

                    TextView ln = new TextView(getApplicationContext());
                    ln.setText(list.get(i).getLastName());
                    row.addView(ln);

                    TextView y = new TextView(getApplicationContext());
                    y.setText(list.get(i).getYear());
                    row.addView(y);

                    TextView g = new TextView(getApplicationContext());
                    Double d = list.get(i).getGpa();
                    g.setText(d.toString());
                    row.addView(g);

                    table.addView(row);
                }
            }
        });

        TextView gView = new TextView(this);
        gView.setPadding(2,2,2,2);
        gView.setText("GPA");
        gView.setOnClickListener(new View.OnClickListener() { //Sorts based on gpa
            @Override
            public void onClick(View view) {
                Log.i("Click", "g");
                Collections.sort(list, new Comparator<Student>() {
                    @Override
                    public int compare(Student student, Student t1) {
                        return student.getGpa().compareTo(t1.getGpa());
                    }
                });

                int count = table.getChildCount();
                for (int i = 1; i < count; i++) {
                    View child = table.getChildAt(i);
                    if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                }

                for (int i = 0; i < list.size(); ++i) {
                    TableRow row = new TableRow(getApplicationContext());

                    TextView fn = new TextView(getApplicationContext());
                    fn.setText(list.get(i).getFirstName());
                    row.addView(fn);

                    TextView ln = new TextView(getApplicationContext());
                    ln.setText(list.get(i).getLastName());
                    row.addView(ln);

                    TextView y = new TextView(getApplicationContext());
                    y.setText(list.get(i).getYear());
                    row.addView(y);

                    TextView g = new TextView(getApplicationContext());
                    Double d = list.get(i).getGpa();
                    g.setText(d.toString());
                    row.addView(g);

                    table.addView(row);
                }
            }
        });

        header.addView(fnView);
        header.addView(lnView);
        header.addView(yView);
        header.addView(gView);

        table.addView(header);

        for (int i = 0; i < list.size(); ++i) {
            TableRow row = new TableRow(this);

            TextView fn = new TextView(this);
            fn.setText(list.get(i).getFirstName());
            row.addView(fn);

            TextView ln = new TextView(this);
            ln.setText(list.get(i).getLastName());
            row.addView(ln);

            TextView y = new TextView(this);
            y.setText(list.get(i).getYear());
            row.addView(y);

            TextView g = new TextView(this);
            Double d = list.get(i).getGpa();
            g.setText(d.toString());
            row.addView(g);

            table.addView(row);
        }
    }
}


