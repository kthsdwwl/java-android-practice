package com.mini2assignment3a.model;

import com.mini2assignment3a.exception.ExceedException;
import com.mini2assignment3a.exception.ExistException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlin2 on 2015/11/10.
 */
public class Statistics {
    private int quizNum;
    private int listSize;
    private List<Student> students;

    public Statistics(int quizNum) {
        this.quizNum = quizNum;

        students = new ArrayList<Student>();
        listSize = 0;
    }

    /**
     * Find the number of students according to the array
     * @return i: Number of student instances in array
     */
    private int studentsNum() {
        return listSize;
    }

    /**
     * Add student to the list
     * @param student
     * @throws ExistException
     * @throws ExceedException
     */
    public void addStudent(Student student) throws ExistException, ExceedException {
        if (exist(student.getSID()))
            throw new ExistException();

        // less than 40, insert
        if (listSize < 40) {
            students.add(student);
            ++listSize;
        }
        // else throw exception
        else
            throw new ExceedException();
    }

    /**
     * Whether a student exist in the list
     * @param id
     * @return
     */
    private boolean exist(int id) {
        for (int i = 0; i < studentsNum(); ++i)
            if (students.get(i).getSID() == id)
                return true;
        return false;
    }

    /**
     * Return all the score records
     * @return
     */
    public List<String> getAllScores() {
        List<String> ret = new ArrayList<String>();
        int size = studentsNum();

        for (int i = 0; i < size; i++) {
            StringBuilder builder = new StringBuilder();
            Student student = students.get(i);
            builder.append(student.getSID() + "        ");
            int[] scores = student.getScores();
            for (int j = 0; j < scores.length; ++j) {
                builder.append(scores[j] + "    ");
            }
            ret.add(builder.toString());
        }
        return ret;
    }

    /**
     * Find the lowest score of each quiz among all students
     */
    public int[] findlow() {
        int i;
        int j;
        int lowest = 0;
        int size = studentsNum();
        int[] lowscores = new int[quizNum];

        if (size == 0) return null;

        for (i = 0; i < quizNum; ++i) {
            lowest = students.get(0).getScores()[i];
            for (j = 1; j < size; ++j) {
                if (students.get(j).getScores()[i] < lowest)
                    lowest = students.get(j).getScores()[i];
            }
            lowscores[i] = lowest;
        }

        return lowscores;
    }

    /**
     * Find the highest score of each quiz among all students
     */
    public int[] findhigh() {
        int i;
        int j;
        int highest = 0;
        int size = studentsNum();
        int[] highscores = new int[quizNum];

        if (size == 0) return null;

        for (i = 0; i < quizNum; ++i) {
            highest = students.get(0).getScores()[i];
            for (j = 1; j < size; ++j) {
                if (students.get(j).getScores()[i] > highest)
                    highest = students.get(j).getScores()[i];
            }
            highscores[i] = highest;
        }

        return highscores;
    }

    /**
     * Calculate the average score for each quiz
     */
    public float[] findavg() {
        int i;
        int j;
        int size = studentsNum();
        float average;
        float[] avgscores = new float[quizNum];

        if (size == 0) return null;

        for (i = 0; i < quizNum; ++i) {
            average = 0;
            for (j = 0; j < size; ++j) {
                average += students.get(j).getScores()[i];
            }
            avgscores[i] = average / (float)size;
        }

        return avgscores;
    }

}
