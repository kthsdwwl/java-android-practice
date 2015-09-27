package proj1.adapter;

import proj1.exception.AutoException;
import proj1.exception.ErrType;
import proj1.model.Automobile;
import proj1.util.FileIO;

public abstract class ProxyAuto {
	private static Automobile a1;
	
	/**
	 * Create an auto instance from file
	 */
	public void buildAuto(String filename) {
		FileIO fileio = new FileIO();
		a1 = fileio.buildAutoObject(filename);
	}
	
	/**
	 * Print the information of the auto instance
	 */
	public void printAuto(String modelName) {
		a1.print();
	}
	
	/**
	 * Update the option set name of the auto instance
	 */
	public void updateOptionSetName(String modelName, String setName, String newName) {
		a1.updateOptionSet(setName, newName);
	}
	
	/**
	 * Update the option price of the auto instance
	 */
	public void updateOptionPrice(String modelName, String setName, String optionName, float price) {
		a1.updateOption(setName, optionName, price);
	}
	
	/**
	 * Fix a specific exception 
	 */
	public String fix(ErrType type) {
		AutoException e = new AutoException(type);
		return e.fix();
	}
}
