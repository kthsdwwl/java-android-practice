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

import proj1.exception.ErrType;
import proj1.exception.AutoException;
import proj1.model.Automobile;

public class FileIO {
	public FileIO() {
		
	}
	
	/**
	 * Check whether a string is empty
	 */
	private boolean isEmpty(String s) {
		String str = s.replaceAll("\\s*", "");
		return str.length() == 0;
	}
	
	/**
	 * Parse model name, base price and array size from the line and create
	 * an Automotive object
	 * @param line: A line read from input file
	 * @return return an Automotive object with the information read from file
	 */
	private Automobile parseBasicInfo(String line) {
		String name;
		float baseprice;
		String[] info = line.split(";");
		int len = info.length;
		
		try {
			if (len > 0 && !isEmpty(info[0]))
				name = info[0];
			else
				throw new AutoException(ErrType.NO_MODEL_NAME);
		} catch (AutoException e) {
			name = e.fix();
		}
		
		try {
			if (len > 1 && !isEmpty(info[1]))
				baseprice = Float.parseFloat(info[1]);
			else
				throw new AutoException(ErrType.NO_BASE_PRICE);
		} catch (AutoException e) {
			baseprice = Float.parseFloat(e.fix());
		}
		
		return new Automobile(name, baseprice);
	}
	
	/**
	 * Parse option name and price from the line and pass them into Automotive
	 * object
	 * @param line: A line read from input file
	 * @param object: Automotive object to be set
	 */
	private void parseOption(String line, Automobile object) {
		String optionSetName;
		String optionName;
		float price;
		String[] info = line.split(";");
		int len = info.length;
		
		try {
			if (len > 0 && !isEmpty(info[0]))
				optionSetName = info[0];
			else
				throw new AutoException(ErrType.NO_SET_NAME);
		} catch (AutoException e) {
			optionSetName = e.fix();
		}
		
		try {
			if (len > 1 && !isEmpty(info[1]))
				optionName = info[1];
			else
				throw new AutoException(ErrType.NO_OPT_NAME);
		} catch (AutoException e) {
			optionName = e.fix();
		}
		
		try {
			if (len > 2 && !isEmpty(info[2]))
				price = Float.parseFloat(info[2]);
			else
				throw new AutoException(ErrType.NO_OPT_PRICE);
		} catch (AutoException e) {
			price = Float.parseFloat(e.fix());
		}
		
		object.setOption(optionSetName, optionName, price);
	}
	
	/**
	 * Read information from file and use it to create an Automotive object 
	 * @param filename: path of the file
	 */
	public Automobile buildAutoObject(String filename) {
		Automobile object = null;
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
	public void serializeAutoObject(String filename, Automobile object) {
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
	public Automobile readAutoObject(String filename) {
		Automobile object = null;
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			object = (Automobile) in.readObject();
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
