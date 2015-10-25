/**
 * @author Xi Lin
 */

package proj1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OptionSet implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Option> options;
	private String name;
	private String choice;
	
	protected OptionSet(String name) {
		this.name = name;
		this.options = new ArrayList<Option>();
		this.choice = null;
	}
	
	// ========== getters ==========
	protected String getName() {
		return name;
	}
	
	/**
	 * Get option using index
	 */
	protected Option getOption(int index) {
		if (index > options.size()) {
			System.out.println("Index out of bound!");
			return null;
		}
		return options.get(index);
	}
	
	/**
	 * New method: Get the option choice
	 */
	protected Option getOptionChoice() {
		if (this.choice == null) return null;
		return findOption(this.choice);
	}
	
	/**
	 * Get a list including all the options
	 */
	protected ArrayList<String> getOptionList() {
		ArrayList<String> result = new ArrayList<String>();
		for (Option item : options) {
			result.add(item.getName());
		}
		return result;
	}
	
	/**
	 * Get a list including all the option prices
	 */
	protected ArrayList<Float> getOptionPriceList() {
		ArrayList<Float> result = new ArrayList<Float>();
		for (Option item : options) {
			result.add(item.getPrice());
		}
		return result;
	}
	
	/**
	 * Get option using its name
	 */
	protected Option findOption(String optionName) {
		int i = 0;
		int size = options.size();
		Option option = null;
		
		while (i < size) {
			option = options.get(i);
			if (option.getName().equals(optionName))
				return option;
			++i;
		}
		return null;
	}
	
	// ========== setters ==========
	protected void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set the value of option after finding it using index
	 */
	protected void setOption(int index, Option option) {
		if (index > options.size()) {
			System.out.println("Index out of bound!");
			return;
		}
		options.set(index, option);
	}
	
	/**
	 * Set the value of option after finding it using its name
	 */
	protected void setOption(String optionName, float price) {
		int i = 0;
		int size = options.size();
		Option option = null;
		while (i < size) {
			option = options.get(i);
			if (option.getName().equals(optionName)) {
				option.setPrice(price);
				return;
			}
			++i;
		}

		/**
		 * if cannot find option by name, insert the value to the list array
		 */
		options.add(new Option(optionName, price));
	}
	
	/**
	 * New method: Set the value of choice using the given option name
	 */
	protected boolean setOptionChoice(String optionName) {
		Option target = findOption(optionName);
		if (target == null)
			return false;
		else {
			this.choice = optionName;
			return true;
		}
	}
	
	// ========== update ==========
	/**
	 * Update option's name with newName
	 */
	protected void updateOption(String name, String newName) {
		Option target = findOption(name);
		if (target == null)
			System.out.println("Cannot find the option with given name");
		else
			target.setName(newName);
	}
	
	/**
	 * Update option's price
	 */
	protected void updateOption(String name, float price) {
		Option target = findOption(name);
		if (target == null)
			System.out.println("Cannot find the option with given name");
		else
			target.setPrice(price);
	}
	
	// ========== delete ==========
	/**
	 * Set an option to null according to index
	 */
	protected void deleteOption(int index) {
		if (index > options.size()) {
			System.out.println("Index out of bound");
			return;
		}
		options.remove(index);
	}
	
	/**
	 * Set an option to null according to its name
	 */
	protected void deleteOption(String name) {
		int i = 0;
		int size = options.size();
		Option option = null;
		
		while (i < size) {
			option = options.get(i);
			if (option.getName().equals(name)) {
				options.remove(i);
				return;
			}
			++i;
		}
	}
	
	// ========== print ==========
	/**
	 * Print optionset's name and the option array
	 */
	protected void print() {
		int i = 0;
		int size = options.size();
		
		System.out.println("Option Set: " + name);
		while (i < size) {
			System.out.print("--");
			options.get(i).print();
			++i;
		}
	}
	
	/**
	 * Print optionset's name and the option array
	 */
	protected void printChoice() {
		System.out.println("Option Set: " + name);
		System.out.print("--Your choice: ");
		System.out.println(choice);
		
	}
}
