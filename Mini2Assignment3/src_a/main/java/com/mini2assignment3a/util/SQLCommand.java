package com.mini2assignment3a.util;

/**
 * Created by xlin2 on 2015/11/10.
 */
public class SQLCommand {
    private final String TABLE_STUDENTS = "STUDENTS";

    private final String CREATE_TABLE_STUDENTS = "CREATE TABLE IF NOT EXISTS STUDENTS(" +
                                                     "id int PRIMARY KEY, " +
                                                     "q1 int NOT NULL, " +
                                                     "q2 int NOT NULL, " +
                                                     "q3 int NOT NULL, " +
                                                     "q4 int NOT NULL, " +
                                                     "q5 int NOT NULL);";

    private final String ID = "id";
    private final String Q1 = "q1";
    private final String Q2 = "q2";
    private final String Q3 = "q3";
    private final String Q4 = "q4";
    private final String Q5 = "q5";

    public String createTableStudents() {
        return CREATE_TABLE_STUDENTS;
    }

    public String getStudentsTableName() {
        return TABLE_STUDENTS;
    }

    public String getID() {
        return ID;
    }

    public String getQ1() {
        return Q1;
    }

    public String getQ2() {
        return Q2;
    }

    public String getQ3() {
        return Q3;
    }

    public String getQ4() {
        return Q4;
    }

    public String getQ5() {
        return Q5;
    }
}
