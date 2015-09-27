/**
 * @author Xi Lin
 */

package hw2.driver;

import hw2.model.Statistics;
import hw2.model.Student;
import hw2.util.Util;

public class Main {
	public static void main(String[] args) {
		int i = 0;
		final String filename = "src/data/scores20.txt";
		Student[] students = new Student[40];
		Statistics stat = new Statistics(5);
		
		// read scores from file
		students = Util.readFile(filename, students);
		
		// print quiz scores for each student
		System.out.println("Stud\t\tQ1\tQ2\tQ3\tQ4\tQ5");
		while (i < 40 && students[i] != null) {
			students[i].printSID();
			System.out.print("\t\t");
			students[i].printScores();
			++i;
		}
		System.out.println();
		
		// get low, high, and average scores
		stat.findlow(students);
		stat.findhigh(students);
		stat.findavg(students);
		
		// print low, high, and average scores
		System.out.print("High Score\t");
		stat.printHighScores();
		System.out.print("Low Score\t");
		stat.printLowScores();
		System.out.print("Avg Score\t");
		stat.printAvgScores();
		
	}
}
