/**
 * @author Xi Lin
 */

package proj1.model;

import java.io.Serializable;

public class OptionSet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final int optionSize = 50;
	private Option[] options;
	private String name;
	
	protected OptionSet(String name) {
		this.name = name;
		this.options = new Option[optionSize];
	}
	
	// ========== getters ==========
	protected String getName() {
		return name;
	}
	
	/**
	 * Get option using index
	 */
	protected Option getOption(int index) {
		if (index > optionSize) {
			System.out.println("Index out of bound!");
			return null;
		}
		return options[index];
	}
	
	/**
	 * Get option using its name
	 */
	protected Option findOption(String optionName) {
		int i = 0;
		while (i < optionSize && options[i] != null) {
			if (options[i].getName().equals(optionName))
				return options[i];
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
		if (index > optionSize) {
			System.out.println("Index out of bound!");
			return;
		}
		options[index] = option;
	}
	
	/**
	 * Set the value of option after finding it using its name
	 */
	protected void setOption(String optionName, float price) {
		int i = 0;
		while (i < optionSize && options[i] != null) {
			if (options[i].getName().equals(optionName)) {
				options[i].setPrice(price);;
				return;
			}
			++i;
		}

		/**
		 * if cannot find option by name, insert the value
		 * to a null position in the array
		 */
		if (i < optionSize)
			options[i] = new Option(optionName, price);
		else 
			System.out.println("Option array is full");
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
		if (index > optionSize) {
			System.out.println("Index out of bound");
			return;
		}
		options[index] = null;
	}
	
	/**
	 * Set an option to null according to its name
	 */
	protected void deleteOption(String name) {
		int i = 0;
		while (i < optionSize && options[i] != null) {
			if (options[i].getName().equals(name)) {
				options[i] = null;
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
		System.out.println("Option Set: " + name);
		while (i < optionSize && options[i] != null) {
			System.out.print("--");
			options[i].print();
			++i;
		}
	}
}
