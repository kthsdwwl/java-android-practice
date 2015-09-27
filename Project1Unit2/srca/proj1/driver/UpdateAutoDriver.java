package proj1.driver;

import proj1.adapter.BuildAuto;
import proj1.adapter.CreateAuto;
import proj1.adapter.UpdateAuto;

public class UpdateAutoDriver {
	private static String filename = "src/data/FordZTW.txt";
	
	public static void main(String[] args) {
		CreateAuto auto = new BuildAuto();
		UpdateAuto upauto = new BuildAuto();

		// Create auto instance and print it
		System.out.println("Create auto instance through CreateAuto: ");
		auto.buildAuto(filename);
		auto.printAuto("modelName");
		
		// Update the optionset name of the instance
		System.out.println("\nUpdate option set name through UpdateAuto: ");
		upauto.updateOptionSetName("modelName", "color", "coating");
		auto.printAuto("modelName");
		
		// Update the option price of the instance
		System.out.println("\nUpdate option price through UpdateAuto: ");
		upauto.updateOptionPrice("modelName", "brakes", "abs", 36000);
		auto.printAuto("modelName");
		
	}
}
