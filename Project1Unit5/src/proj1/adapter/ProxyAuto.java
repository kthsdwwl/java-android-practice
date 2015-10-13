/**
 * @author Xi Lin
 */

package proj1.adapter;

import java.util.LinkedHashMap;
import java.util.Map;

import proj1.exception.AutoException;
import proj1.exception.ErrType;
import proj1.model.Automobile;
import proj1.util.FileIO;

public abstract class ProxyAuto {
	private static Map<String, Automobile> autos = new LinkedHashMap<String, Automobile>();
	
	/**
	 * Create an auto instance from file and insert it into hashmap
	 */
	public void buildAuto(String filename) {
		FileIO fileio = new FileIO();
		Automobile auto = fileio.buildAutoObject(filename);
		autos.put(auto.getName(), auto);
	}
	
	/**
	 * Print the information of a specific auto instance with the given name
	 */
	public void printAuto(String modelName) {
		Automobile auto = autos.get(modelName);
		if (auto == null)
			System.out.println("Could not find the model with given name!");
		else
			auto.print();
	}
	
	/**
	 * Print the total price of a specific auto instance with the given name
	 */
	public void printTotalPrice(String modelName) {
		Automobile auto = autos.get(modelName);
		if (auto == null)
			System.out.println("Could not find the model with given name!");
		else
			System.out.println("Total price is: " + auto.getTotalPrice());
	}
	
	/**
	 * Update the option set name of an auto instance in the map
	 */
	public void updateOptionSetName(String modelName, String setName, String newName) {
		Automobile auto = autos.get(modelName);
		if (auto == null)
			System.out.println("Could not find the model with given name!");
		else
			auto.updateOptionSet(setName, newName);
	}
	
	/**
	 * Update the option price of an auto instance in the map
	 */
	public void updateOptionPrice(String modelName, String setName, String optionName, float price) {
		Automobile auto = autos.get(modelName);
		if (auto == null)
			System.out.println("Could not find the model with given name!");
		else
			auto.updateOption(setName, optionName, price);
	}
	
	/**
	 * Set the option choice of an auto instance after finding it by its name
	 */
	public void setOptionChoice(String modelName, String setName, String optionName) {
		Automobile auto = autos.get(modelName);
		if (auto == null)
			System.out.println("Could not find the model with given name!");
		else
			auto.setOptionChoice(setName, optionName);
	}
	
	/**
	 * Return the price of an option in a specific auto instance with the given name
	 */
	public synchronized float getOptionPrice(String modelName, String setName, String optionName) {
		Automobile auto = autos.get(modelName);
		if (auto == null)
			return -1;
		else 
			return auto.findOptionPrice(setName, optionName);
	}
	
	/**
	 * Update the option price of an auto instance in the map
	 */
	public synchronized void editOptionPrice(String modelName, String setName, String optionName, float price) {
		Automobile auto = autos.get(modelName);
		if (auto == null)
			System.out.println("Could not find the model with given name!");
		else
			auto.updateOption(setName, optionName, price);
	}
	
	/**
	 * Print the price of an option in a specific auto instance with the given name
	 */
	public synchronized void printOptionPrice(String modelName, String setName, String optionName) {
		Automobile auto = autos.get(modelName);
		if (auto == null)
			System.out.println("Could not find the model with given name!");
		else {
			float price = auto.findOptionPrice(setName, optionName);
			System.out.println("Option " + optionName + "'s price is: " + price);
		}
	}

	/**
	 * Fix a specific exception 
	 */
	public String fix(ErrType type) {
		AutoException e = new AutoException(type);
		return e.fix();
	}
}
