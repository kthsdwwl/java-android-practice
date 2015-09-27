/**
 * @author Xi Lin
 */

package hw2.model;

public class Statistics extends StatisticsAnalyzer implements PrintStatistics {
	private int quizNum;
	private int[] lowscores;
	private int[] highscores;
	private float[] avgscores;
	
	public Statistics(int quizNum) {
		this.quizNum = quizNum;
		this.lowscores = new int[quizNum];
		this.highscores = new int[quizNum];
		this.avgscores = new float[quizNum];
	}
	
	/**
	 * Find the number of students according to the array
	 * @param students: Student array including student instances
	 * @return i: Number of student instances in array
	 */
	private int studentsNum(Student[] students) {
		int i = 0;
		while (i < students.length && students[i] != null)
			++i;
		return i;
	}
	
	@Override
	/**
	 * Find the lowest score of each quiz among all students
	 */
	public void findlow(Student[] students) {
		int i;
		int j;
		int lowest = 0;
		int size = studentsNum(students);
		
		if (size == 0) return;
		
		for (i = 0; i < quizNum; ++i) {
			lowest = students[0].getScores()[i];
			for (j = 1; j < size; ++j) {
				if (students[j].getScores()[i] < lowest)
					lowest = students[j].getScores()[i];
			}
			lowscores[i] = lowest;
		}
	}
	
	@Override
	/**
	 * Find the highest score of each quiz among all students
	 */
	public void findhigh(Student[] students) {
		int i;
		int j;
		int highest = 0;
		int size = studentsNum(students);
		
		if (size == 0) return;
		
		for (i = 0; i < quizNum; ++i) {
			highest = students[0].getScores()[i];
			for (j = 1; j < size; ++j) {
				if (students[j].getScores()[i] > highest)
					highest = students[j].getScores()[i];
			}
			highscores[i] = highest;
		}
	}
	
	@Override
	/**
	 * Calculate the average score for each quiz
	 */
	public void findavg(Student[] students) {
		int i;
		int j;
		int size = studentsNum(students);
		float average;
		
		if (size == 0) return;
		
		for (i = 0; i < quizNum; ++i) {
			average = 0;
			for (j = 0; j < size; ++j) {
				average += students[j].getScores()[i];
			}
			avgscores[i] = average / (float)size;
		}
	}
	
	@Override
	/**
	 * Print the lowest score for each quiz
	 */
	public void printLowScores() {
		for (int i = 0; i < quizNum; ++i)
			System.out.printf("%d\t", lowscores[i]);
		System.out.println();
	}
	
	@Override
	/**
	 * Print the highest score for each quiz
	 */
	public void printHighScores() {
		for (int i = 0; i < quizNum; ++i)
			System.out.printf("%d\t", highscores[i]);
		System.out.println();
	}
	
	@Override
	/**
	 * Print the average score for each quiz
	 */
	public void printAvgScores() {
		for (int i = 0; i < quizNum; ++i)
			System.out.printf("%.1f\t", avgscores[i]);
		System.out.println();
	}
}
