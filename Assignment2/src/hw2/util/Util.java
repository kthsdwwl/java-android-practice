/**
 * @author Xi Lin
 */

package hw2.util;

import hw2.exception.TooManyRecordsException;
import hw2.model.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Util {
	@SuppressWarnings("resource")
	/**
	 * Read student information from file and set the ids and scores for
	 * each student according to the information
	 * @param filename: The name of record file
	 * @param students: Student array to store student information
	 * @return students: Student array 
	 */
	public static Student[] readFile(String filename, Student[] students) {		
		int i = 0;
		boolean eof;
		FileReader file;
		BufferedReader buff;
		String line;
		
		try {
			file = new FileReader(filename);
			buff = new BufferedReader(file);
			eof = false;
			while (!eof) {
				// each time read a line from the file
				line = buff.readLine();
				
				if (line == null)
					eof = true;
				
				// skip the first line
				else if (line.startsWith("Stud")) 
					continue;
				
				// if records over 40, thorws exception
				else if (i >= 40) { 
					throw new TooManyRecordsException();
				}
				
				else {
					students[i] = parseLine(line);
					++i;
				}
			}
			buff.close();
		} catch (FileNotFoundException e) {
			System.out.println("Exception: No such file.");
		} catch (IOException e) {
			System.out.println("Exception: IO exception.");
		} catch (TooManyRecordsException e) {
			e.fixProblem();
		}
		return students;
	}
	
	/**
	 * Input a line of student information, split and parse it,
	 * and create a student instance using the information
	 * @param line: One line of the record file
	 * @return student: The student instance created from line 
	 */
	private static Student parseLine(String line) {
		int sID;
		int i = 0;
		int[] scores = new int[5];
		Student student = new Student();
		StringTokenizer tokenizer = new StringTokenizer(line);
		
		// parse student id
		sID = Integer.parseInt(tokenizer.nextToken());
		
		// parse student scores
		while (i < 5 && tokenizer.hasMoreTokens()) {
			scores[i] = Integer.parseInt(tokenizer.nextToken());
			++i;
		}
		
		// set student information and return it
		student.setSID(sID);
		student.setScores(scores);
		return student;
	}
}
