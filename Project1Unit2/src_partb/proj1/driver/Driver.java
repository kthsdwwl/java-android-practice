package proj1.driver;

import proj1.adapter.BuildAuto;
import proj1.adapter.ProxyAuto;

public class Driver {
	private static String filename1 = "src/data/FordZTW.txt";
	private static String filename2 = "src/data/Audi.txt";
	private static String filename3 = "src/data/Volkswagen.txt";
	
	public static void main(String[] args) {
		ProxyAuto auto = new BuildAuto();
		
		// Build 3 auto instances
		auto.buildAuto(filename1);
		auto.buildAuto(filename2);
		auto.buildAuto(filename3);
		
		// Print out information
		System.out.println("Origin information: ");
		auto.printAuto("Audi Q7");
		
		// Update option set name
		System.out.println("\nUpdate brakes: ");
		auto.updateOptionSetName("Audi Q7", "brakes", "braking");
		auto.printAuto("Audi Q7");
		
		// Update option price
		System.out.println("\nUpdate price for standard braking: ");
		auto.updateOptionPrice("Audi Q7", "braking", "standard", 100);
		auto.printAuto("Audi Q7");
		
		// Set option choice and calculate the price
		System.out.println("\nSet the airbag of Audi to be 'selected': ");
		auto.setOptionChoice("Audi Q7", "airbags", "selected");
		auto.printTotalPrice("Audi Q7");
	}
}
