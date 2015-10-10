/**
 * @author Xi Lin
 */

package proj1.adapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

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
	 * Extract properties from the object and create an auto object
	 */
	public void buildAutoFromProperties(Properties prop) {
		String modelName = prop.getProperty("ModelName");
		if (modelName != null) {
			float basePrice = Float.parseFloat(prop.getProperty("BasePrice"));
			Automobile auto = new Automobile(modelName, basePrice);
			
			// prefix
			String optionSetPre = "Option";
			String optionPre = "OptionValue";
			String optionPricePre = "OptionPrice";
			int optionSetIdx = 1;
			int optionIdx;
			
			// extract option sets and options one by one
			while (true) {
				optionIdx = 1;
				String optionSetName = prop.getProperty(optionSetPre + optionSetIdx);
				if (optionSetName == null) break;
				while (true) {
					String optionName = prop.getProperty(optionPre + optionSetIdx + "-" + optionIdx);
					if (optionName == null) break;
					float optionPrice = Float.parseFloat(prop.getProperty(optionPricePre + optionSetIdx + "-" + optionIdx));
					auto.setOption(optionSetName, optionName, optionPrice);
					++optionIdx;
				}
				++optionSetIdx;
			}
			
			// insert the auto instance to the LinkedHashMap
			autos.put(auto.getName(), auto);
		}
	}
	
	/**
	 * Return an ArrayList that contains all the model names.
	 */
	public ArrayList<String> getModelList() {
		ArrayList<String> result = new ArrayList<String>();
		for (Entry<String, Automobile> entry : autos.entrySet()) {
			result.add(entry.getKey());
		}
		return result;
	}
	
	/**
	 * Get the auto instance from HashMap according to the name
	 */
	public Automobile getModelByRequest(String name) {
		return autos.get(name);
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
