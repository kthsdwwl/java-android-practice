package com.mini2assignment3a.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mini2assignment3a.R;
import com.mini2assignment3a.exception.EmptyException;
import com.mini2assignment3a.exception.ExistException;
import com.mini2assignment3a.exception.IDRangeException;
import com.mini2assignment3a.exception.ScoreRangeException;
import com.mini2assignment3a.model.Statistics;
import com.mini2assignment3a.model.Student;
import com.mini2assignment3a.util.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class MainActivity extends AppCompatActivity {

    private ListView scoreList;
    private Button addScoreBtn;

    private final int quizNum = 5;
    private Statistics stat = new Statistics(quizNum);
    private int[] low = new int[quizNum];
    private int[] high = new int[quizNum];
    private float[] avg = new float[quizNum];
    private List<String> allScores = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        addListeners();
    }

    /**
     * Get all the ui objects
     */
    private void findViews() {
        addScoreBtn = (Button) findViewById(R.id.btn_add);
        scoreList = (ListView) findViewById(R.id.list_all_score);
    }

    /**
     * Add listeners to ui objects
     */
    private void addListeners() {
        addScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput();
                clearInput();
            }
        });
    }

    /**
     * Handle user input after clicking the button
     */
    private void handleInput() {
        Student student = addStudent();
        if (student == null) return;
        analyseScore();
        refreshListView();
        updateStatResult();
        addToDB(student);
    }

    /**
     * Clear all the edittext values for the next input
     */
    private void clearInput() {
        EditText editID = (EditText) findViewById(R.id.edit_stu_id);
        EditText editQ1 = (EditText) findViewById(R.id.edit_q1);
        EditText editQ2 = (EditText) findViewById(R.id.edit_q2);
        EditText editQ3 = (EditText) findViewById(R.id.edit_q3);
        EditText editQ4 = (EditText) findViewById(R.id.edit_q4);
        EditText editQ5 = (EditText) findViewById(R.id.edit_q5);

        editID.setText("");
        editQ1.setText("");
        editQ2.setText("");
        editQ3.setText("");
        editQ4.setText("");
        editQ5.setText("");
    }

    /**
     * Create a student object according to user input and add it to statistics object
     */
    private Student addStudent() {
        Student s = new Student();
        try {
            // get id and scores from user input
            EditText editID = (EditText) findViewById(R.id.edit_stu_id);
            EditText editQ1 = (EditText) findViewById(R.id.edit_q1);
            EditText editQ2 = (EditText) findViewById(R.id.edit_q2);
            EditText editQ3 = (EditText) findViewById(R.id.edit_q3);
            EditText editQ4 = (EditText) findViewById(R.id.edit_q4);
            EditText editQ5 = (EditText) findViewById(R.id.edit_q5);

            int id = Integer.parseInt(editID.getText().toString());
            int[] scores = new int[quizNum];
            scores[0] = Integer.parseInt(editQ1.getText().toString());
            scores[1] = Integer.parseInt(editQ2.getText().toString());
            scores[2] = Integer.parseInt(editQ3.getText().toString());
            scores[3] = Integer.parseInt(editQ4.getText().toString());
            scores[4] = Integer.parseInt(editQ5.getText().toString());

            // set student values
            s.setSID(id);
            s.setScores(scores);
            stat.addStudent(s);

            // check whether input is valid
            if (id < 1000)
                throw new IDRangeException();
            for (int i = 0; i < quizNum; ++i)
                if (scores[i] > 100)
                    throw new ScoreRangeException();
        } catch (ScoreRangeException e) {
            e.handle();
            showError("Score out of range!");
            return null;
        } catch (IDRangeException e) {
            e.handle();
            showError("ID out of range!");
            return null;
        } catch (ExistException e) {
            e.handle();
            showError("ID exists!");
            return null;
        } catch (Exception e) {
            new EmptyException().handle();
            showError("Input empty!");
            return null;
        }

        return s;
    }

    /**
     * Show error in the dialog
     */
    private void showError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * Find lowest, highest, and average scores
     */
    private void analyseScore() {
        low = stat.findlow();
        high = stat.findhigh();
        avg = stat.findavg();
        allScores = stat.getAllScores();
    }

    /**
     * Refresh the content of list view after user add a new student
     */
    private void refreshListView() {
        ListAdapter adapter = new ArrayAdapter<String>(this,
                                                       R.layout.score_item,
                                                       R.id.text_score_item,
                                                       allScores);
        scoreList.setAdapter(adapter);
    }

    /**
     * Update lowest, highest, and average scores in the textview
     */
    private void updateStatResult() {
        // update highest scores
        TextView h1 = (TextView) findViewById(R.id.text_high1);
        TextView h2 = (TextView) findViewById(R.id.text_high2);
        TextView h3 = (TextView) findViewById(R.id.text_high3);
        TextView h4 = (TextView) findViewById(R.id.text_high4);
        TextView h5 = (TextView) findViewById(R.id.text_high5);

        h1.setText(Integer.toString(high[0]));
        h2.setText(Integer.toString(high[1]));
        h3.setText(Integer.toString(high[2]));
        h4.setText(Integer.toString(high[3]));
        h5.setText(Integer.toString(high[4]));

        // update lowest scores
        TextView l1 = (TextView) findViewById(R.id.text_low1);
        TextView l2 = (TextView) findViewById(R.id.text_low2);
        TextView l3 = (TextView) findViewById(R.id.text_low3);
        TextView l4 = (TextView) findViewById(R.id.text_low4);
        TextView l5 = (TextView) findViewById(R.id.text_low5);

        l1.setText(Integer.toString(low[0]));
        l2.setText(Integer.toString(low[1]));
        l3.setText(Integer.toString(low[2]));
        l4.setText(Integer.toString(low[3]));
        l5.setText(Integer.toString(low[4]));

        // update average scores
        TextView a1 = (TextView) findViewById(R.id.text_avg1);
        TextView a2 = (TextView) findViewById(R.id.text_avg2);
        TextView a3 = (TextView) findViewById(R.id.text_avg3);
        TextView a4 = (TextView) findViewById(R.id.text_avg4);
        TextView a5 = (TextView) findViewById(R.id.text_avg5);

        a1.setText(String.format("%.1f", avg[0]));
        a2.setText(String.format("%.1f", avg[1]));
        a3.setText(String.format("%.1f", avg[2]));
        a4.setText(String.format("%.1f", avg[3]));
        a5.setText(String.format("%.1f", avg[4]));
    }

    /**
     * Add the student record into db
     */
    private void addToDB(Student student) {
        Database db = new Database(this);
        db.insertStudentScore(student);
    }
}
