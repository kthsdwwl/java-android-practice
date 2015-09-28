/**
 * @author Xi Lin
 */

package proj1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Automobile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private float baseprice;
	private List<OptionSet> optionsets;
	
	public Automobile() {
		this.optionsets = new ArrayList<OptionSet>();
	}
	
	public Automobile(String name, float baseprice) {
		this.setName(name);
		this.setBaseprice(baseprice);
		this.optionsets = new ArrayList<OptionSet>();
	}

	// ========== getters ==========
	public synchronized String getName() {
		return name;
	}

	public synchronized float getBaseprice() {
		return baseprice;
	}
	
	public synchronized OptionSet getOptionSet(int index) {
		if (index > optionsets.size()) {
			System.out.println("Index out of bound!");
			return null;
		}
		return optionsets.get(index);
	}
	
	public synchronized String getOptionChoice(String setName) {
		OptionSet optionset = findOptionSet(setName);
		if (optionset == null)
			return null;
		else
			return optionset.getOptionChoice().getName();
	}
	
	public synchronized float getOptionChoicePrice(String setName) {
		OptionSet optionset = findOptionSet(setName);
		if (optionset == null)
			return -1;
		else
			return optionset.getOptionChoice().getPrice();
	}
	
	public synchronized float getTotalPrice() {
		float result = baseprice;
		int size = optionsets.size();
		int i = 0;
		Option choice;
		
		while (i < size) {
			choice = optionsets.get(i).getOptionChoice();
			if (choice != null)
				result += optionsets.get(i).getOptionChoice().getPrice();
			++i;
		}
		return result;
	}
	
	// ========== find ==========
	/**
	 * Find optionset using name. If could not find, return null
	 */
	public synchronized OptionSet findOptionSet(String setName) {
		int size = optionsets.size();
		int i = 0;
		OptionSet optionset = null;
		
		while (i < size) {
			optionset = optionsets.get(i);
			if (optionset.getName().equals(setName))
				return optionset;
			++i;
		}
		return null;
	}
	
	/**
	 * Find option in an optionset. If could not find, return null
	 * @param setName: the name of optionset
	 * @param optionName: the name of option
	 */
	public synchronized Option findOption(String setName, String optionName) {
		OptionSet targetSet = findOptionSet(setName);
		if (targetSet == null) {
			return null;
		}
		return targetSet.findOption(optionName);
	}
	
	/**
	 * Find option in an optionset, then return its price. If could not find, return null
	 * @param setName: the name of optionset
	 * @param optionName: the name of option
	 */
	public synchronized float findOptionPrice(String setName, String optionName) {
		OptionSet targetSet = findOptionSet(setName);
		if (targetSet == null) {
			return 0;
		}
		return targetSet.findOption(optionName).getPrice();
	}

	// ========== setters ==========
	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
	
	/**
	 * First look for optionset with given name, if could not find, insert
	 * new optionset into the array
	 * @param setName: the name of optionset
	 */
	public synchronized void setOptionSet(String setName) {
		int size = optionsets.size();
		int i = 0;
		OptionSet optionset = null;
		
		while (i < size) {
			optionset = optionsets.get(i);
			if (optionset.getName().equals(setName)) {
				return;
			}
			++i;
		}
		
		/*
		 * if cannot find optionset by name, insert the value to arraylist
		 */
		optionsets.add(new OptionSet(setName));
	}
	
	/**
	 * Insert option to a specific optionset. If could not find the optionset,
	 * create a new optionset.
	 * @param setName: the name of optionset
	 * @param optionName: the name of option
	 * @param price: price of option
	 */
	public synchronized void setOption(String setName, String optionName, float price) {
		OptionSet target = findOptionSet(setName);
		
		if (target == null) {
			setOptionSet(setName);
			target = optionsets.get(optionsets.size() - 1);
		}
		target.setOption(optionName, price);
	}
	
	/**
	 * New method
	 */
	public synchronized void setOptionChoice(String setName, String optionName) {
		OptionSet target = findOptionSet(setName);
		if (target == null)
			System.out.println("Cannot find the option set with given name");
		else
			target.setOptionChoice(optionName);
	}
	
	// ========== update ==========
	/**
	 * Update optionset's name using newName
	 */
	public synchronized void updateOptionSet(String setName, String newName) {
		OptionSet target = findOptionSet(setName);
		if (target == null)
			System.out.println("Cannot find the option set with given name");
		else
			target.setName(newName);
	}
	
	/**
	 * Update option's name in a specific optionset using newOptionName
	 */
	public synchronized void updateOption(String setName, String optionName, String newOptionName) {
		OptionSet target = findOptionSet(setName);
		if (target == null)
			System.out.println("Cannot find the option set with given name");
		else
			target.updateOption(optionName, newOptionName);
	}
	
	/**
	 * Update option's price in a specific optionset
	 */
	public synchronized void updateOption(String setName, String optionName, float price) {
		OptionSet target = findOptionSet(setName);
		if (target == null)
			System.out.println("Cannot find the option set with given name");
		else
			target.updateOption(optionName, price);
	}
	
	// ========== delete ==========	
	/**
	 * Set an optionset to null according to index
	 */
	public synchronized void deleteOptionSet(int index) {
		if (index > optionsets.size()) {
			System.out.println("Index out of bound");
			return;
		}
		optionsets.remove(index);
	}
	
	/**
	 * Set an optionset to null according to its name
	 */
	public synchronized void deleteOptionSet(String name) {
		int size = optionsets.size();
		int i = 0;
		OptionSet optionset = null;
		
		while (i < size) {
			optionset = optionsets.get(i);
			if (optionset.getName().equals(name)) {
				optionsets.remove(i);
				return;
			}
			++i;
		}
	}
	
	/**
	 * Set an option to null according to its optionset and its name
	 */
	public synchronized void deleteOption(String setName, String optionName) {
		OptionSet target = findOptionSet(setName);
		if (target == null)
			System.out.println("Cannot find the option set with given name");
		else
			target.deleteOption(optionName);
	}
	
	// ========== print ==========
	/**
	 * Print name, base price, and optionset array 
	 */
	public synchronized void print() {
		int size = optionsets.size();
		int i = 0;
		System.out.println("Model: " + this.name);
		System.out.println("Base price: " + this.baseprice);
		
		while (i < size) {
			optionsets.get(i).print();
			++i;
		}
	}
}
