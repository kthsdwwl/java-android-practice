/**
 * @author Xi Lin
 */

package proj1.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

import proj1.model.Automotive;

public class FileIO {
	public FileIO() {
		
	}
	
	/**
	 * Parse model name, base price and array size from the line and create
	 * an Automotive object
	 * @param line: A line read from input file
	 * @return return an Automotive object with the information read from file
	 */
	private Automotive parseBasicInfo(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line, ";");
		String name = tokenizer.nextToken();
		float baseprice = Float.parseFloat(tokenizer.nextToken());
		int size = Integer.parseInt(tokenizer.nextToken());
		
		return new Automotive(name, baseprice, size);
	}
	
	/**
	 * Parse option name and price from the line and pass them into Automotive
	 * object
	 * @param line: A line read from input file
	 * @param object: Automotive object to be set
	 */
	private void parseOption(String line, Automotive object) {
		StringTokenizer tokenizer = new StringTokenizer(line, ";");
		String optionSetName = tokenizer.nextToken();
		String optionName = tokenizer.nextToken();
		float price = Float.parseFloat(tokenizer.nextToken());
		
		object.setOption(optionSetName, optionName, price);
	}
	
	/**
	 * Read information from file and use it to create an Automotive object 
	 * @param filename: path of the file
	 */
	public Automotive buildAutoObject(String filename) {
		Automotive object = null;
		int i = 0;
		boolean eof = false;
		String line;
		
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			while (!eof) {
				line = buff.readLine();
				if (line == null)
					eof = true;
				else if (i == 0) /* first line is name, price, and array size */
					object = parseBasicInfo(line);
				else  /* other lines are options */
					parseOption(line, object);
				++i;	
			}
			buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * Output object into a file 
	 * @param filename: path of the file
	 * @param object: output object
	 */
	public void serializeAutoObject(String filename, Automotive object) {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(object);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read object from file   
	 * @param filename: path of the file
	 */
	public Automotive readAutoObject(String filename) {
		Automotive object = null;
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			object = (Automotive) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}
}
