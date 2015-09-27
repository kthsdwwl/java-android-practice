package proj1.driver;

import proj1.adapter.BuildAuto;
import proj1.adapter.CreateAuto;

public class CreateAutoDriver {
	private static String filename = "src/data/FordZTW.txt";
	
	public static void main(String[] args) {
		CreateAuto auto = new BuildAuto();
		
		System.out.println("Create auto instance through interface CreateAuto: ");
		auto.buildAuto(filename);
		auto.printAuto("modelName");
	
	}
}
