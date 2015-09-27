/**
 * @author Xi Lin
 */

package proj1.driver;

import proj1.model.Automotive;
import proj1.util.FileIO;

public class Driver {
	private static final String filename = "src/data/FordZTW.txt";
	private static final String objFilename = "src/data/FordZTW.ser";
	
	public static void main(String[] args) {
		FileIO fileio = new FileIO();
		Automotive auto = fileio.buildAutoObject(filename);
		Automotive readAuto;
		
		// Print Automotive object 
		System.out.println("Before serializing: ");
		auto.print();
		
		// Write the object to file and read back and print it
		System.out.println("\nAfter serializing: ");
		fileio.serializeAutoObject(objFilename, auto);
		readAuto = fileio.readAutoObject(objFilename);
		readAuto.print();
		
		// Test: update the name of an optionset
		System.out.println("\nUpdate 'color' to 'coating': ");
		auto.updateOptionSet("color", "coating");
		auto.print();
		
		// Test: update the name of an option
		System.out.println("\nUpdate 'Pitch Black Clearcoat' to 'Brown Clearcoat': ");
		auto.updateOption("coating", "Pitch Black Clearcoat", "Brown Clearcoat");
		auto.print();
		
		// Test: delete an element in optionset array
		System.out.println("\nDelete 'power-moonroof': ");
		auto.deleteOptionSet("power-moonroof");
		auto.print();
		
		// Test: delete an option
		System.out.println("\nDelete 'Cloud 9 White Clearcoat': ");
		auto.deleteOption("coating", "Cloud 9 White Clearcoat");
		auto.print();
		
	}
}
