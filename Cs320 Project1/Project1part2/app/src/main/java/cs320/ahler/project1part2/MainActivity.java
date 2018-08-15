package cs320.ahler.project1part2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "cs320.ahler.project1part2.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findScores(View view) {
        Intent intent = new Intent(this,DisplayGrades.class);
        EditText quizText = (EditText) findViewById(R.id.editText);
        EditText nameText = (EditText) findViewById(R.id.editText2);
        EditText assignmentText = (EditText) findViewById(R.id.editText3);
        EditText midtermText = (EditText) findViewById(R.id.editText4);
        EditText finalText = (EditText) findViewById(R.id.editText5);

        String message;

        String name = nameText.getText().toString();
        String quiz = quizText.getText().toString();
        String assignments = assignmentText.getText().toString();
        String midterm = midtermText.getText().toString();
        String finals = finalText.getText().toString();

        if (!quiz.matches("^[0-9]+$")) {
            message = "Please enter a valid input";
        } else if (!assignments.matches("^[0-9]+$")) {
            message = "Please enter a valid input";
        } else if (!midterm.matches("^[0-9]+$")) {
            message = "Please enter a valid input";
        } else if (!finals.matches("^[0-9]+$")) {
            message = "Please enter a valid input";
        } else {

            double average = (Double.valueOf(quiz) * 0.15) + (Double.valueOf(assignments) * .4) + (Double.valueOf(midterm) * 0.2) + (Double.valueOf(finals) * 0.25);
            String grade;
            if (average >= 90) {
                grade = "A";
            } else if (90 > average && average >= 80) {
                grade = "B";
            } else if (80 > average && average >= 70) {
                grade = "C";
            } else if (70 > average && average >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            message = "Student: " + name + ", Average score: " + average + ", Final Letter grade: " + grade;
        }

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}
