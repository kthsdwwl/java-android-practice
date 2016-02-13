package com.mini2assignment3a.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mini2assignment3a.model.Student;

/**
 * Created by xlin2 on 2015/11/10.
 */
public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "score.db";
    private static final int DATABASE_VERSION = 1;
    private SQLCommand cmd;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        cmd = new SQLCommand();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(cmd.createTableStudents());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Insert payoff date into database
     */
    public void insertStudentScore(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        int[] scores = student.getScores();
        values.put("id", student.getSID());
        values.put("q1", scores[0]);
        values.put("q2", scores[1]);
        values.put("q3", scores[2]);
        values.put("q4", scores[3]);
        values.put("q5", scores[4]);
        db.insert(cmd.getStudentsTableName(), null, values);
    }

    /**
     * Get all the scores fro the database
     */
    public String getAllScores() {
        SQLiteDatabase db = getWritableDatabase();
        StringBuilder builder = new StringBuilder();
        String[] columns = {cmd.getID(), cmd.getQ1(), cmd.getQ2(), cmd.getQ3(), cmd.getQ4(), cmd.getQ5()};

        Cursor cursor = db.query(cmd.getStudentsTableName(), columns, null, null, null, null, null, null);
        while (!cursor.isAfterLast()) {
            builder.append("ID: " + cursor.getString(cursor.getColumnIndexOrThrow(cmd.getID())) + " ");
            builder.append("Q1: " + cursor.getString(cursor.getColumnIndexOrThrow(cmd.getQ1())) + " ");
            builder.append("Q2: " + cursor.getString(cursor.getColumnIndexOrThrow(cmd.getQ2())) + " ");
            builder.append("Q3: " + cursor.getString(cursor.getColumnIndexOrThrow(cmd.getQ3())) + " ");
            builder.append("Q4: " + cursor.getString(cursor.getColumnIndexOrThrow(cmd.getQ4())) + " ");
            builder.append("Q5: " + cursor.getString(cursor.getColumnIndexOrThrow(cmd.getQ5())) + " ");
            builder.append("\n");
            cursor.moveToNext();
        }
        cursor.close();

        return builder.toString();
    }
}
