/**
 * @author Xi Lin
 */

package proj1.model;

import java.io.Serializable;

import proj1.model.OptionSet.Option;

public class Automotive implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final int defaultSize = 50;
	private String name;
	private float baseprice;
	private OptionSet[] optionsets;
	
	public Automotive() {
		this.optionsets = new OptionSet[defaultSize];
	}
	
	public Automotive(String name, float baseprice, int setSize) {
		this.setName(name);
		this.setBaseprice(baseprice);
		this.optionsets = new OptionSet[setSize];
	}

	// ========== getters ==========
	public String getName() {
		return name;
	}

	public float getBaseprice() {
		return baseprice;
	}
	
	public OptionSet getOptionSet(int index) {
		if (index > optionsets.length) {
			System.out.println("Index out of bound!");
			return null;
		}
		return optionsets[index];
	}
	
	// ========== find ==========
	/**
	 * Find optionset using name. If could not find, return null
	 */
	public OptionSet findOptionSet(String setName) {
		int size = optionsets.length;
		int i = 0;
		while (i < size && optionsets[i] != null) {
			if (optionsets[i].getName().equals(setName))
				return optionsets[i];
			++i;
		}
		return null;
	}
	
	/**
	 * Find option in an optionset. If could not find, return null
	 * @param setName: the name of optionset
	 * @param optionName: the name of option
	 */
	public Option findOption(String setName, String optionName) {
		OptionSet targetSet = findOptionSet(setName);
		if (targetSet == null) {
			return null;
		}
		return targetSet.findOption(optionName);
	}

	// ========== setters ==========
	public void setName(String name) {
		this.name = name;
	}

	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
	
	/**
	 * First look for optionset with given name, if could not find, insert
	 * new optionset into the array
	 * @param setName: the name of optionset
	 */
	public void setOptionSet(String setName) {
		int size = optionsets.length;
		int i = 0;
		while (i < size && optionsets[i] != null) {
			if (optionsets[i].getName().equals(setName)) {
				return;
			}
			++i;
		}
		
		/*
		 * if cannot find optionset by name, insert the value
		 * to a null position in the array
		 */
		if (i < size)
			optionsets[i] = new OptionSet(setName);
		else 
			System.out.println("Array is full, cannot insert");
	}
	
	/**
	 * Insert option to a specific optionset. If could not find the optionset,
	 * create a new optionset.
	 * @param setName: the name of optionset
	 * @param optionName: the name of option
	 * @param price: price of option
	 */
	public void setOption(String setName, String optionName, float price) {
		OptionSet target = findOptionSet(setName);
		
		if (target == null) {
			setOptionSet(setName);
			target = findOptionSet(setName);
			if (target == null)
				System.out.println("Array is full, cannot insert");
		}
		target.setOption(optionName, price);
	}
	
	// ========== update ==========
	/**
	 * Update optionset's name using newName
	 */
	public void updateOptionSet(String setName, String newName) {
		OptionSet target = findOptionSet(setName);
		if (target == null)
			System.out.println("Cannot find the option set with given name");
		else
			target.setName(newName);
	}
	
	/**
	 * Update option's name in a specific optionset using newOptionName
	 */
	public void updateOption(String setName, String optionName, String newOptionName) {
		OptionSet target = findOptionSet(setName);
		if (target == null)
			System.out.println("Cannot find the option set with given name");
		else
			target.updateOption(optionName, newOptionName);
	}
	
	/**
	 * Update option's price in a specific optionset
	 */
	public void updateOption(String setName, String optionName, float price) {
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
	public void deleteOptionSet(int index) {
		if (index > optionsets.length) {
			System.out.println("Index out of bound");
			return;
		}
		optionsets[index] = null;
	}
	
	/**
	 * Set an optionset to null according to its name
	 */
	public void deleteOptionSet(String name) {
		int size = optionsets.length;
		int i = 0;
		while (i < size && optionsets[i] != null) {
			if (optionsets[i].getName().equals(name)) {
				optionsets[i] = null;
				return;
			}
			++i;
		}
	}
	
	/**
	 * Set an option to null according to its optionset and its name
	 */
	public void deleteOption(String setName, String optionName) {
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
	public void print() {
		System.out.println("Model: " + this.name);
		System.out.println("Base price: " + this.baseprice);
		int i = 0;
		while (i < optionsets.length && optionsets[i] != null) {
			optionsets[i].print();
			++i;
		}
	}
}
