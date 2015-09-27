package proj1.driver;

import proj1.adapter.BuildAuto;
import proj1.adapter.CreateAuto;

public class TestExceptionDriver {

	private static String filenameErr = "src/data/FordZTW_error.txt";
	
	public static void main(String[] args) {
		CreateAuto auto = new BuildAuto();
		System.out.println("Test exception: ");
		auto.buildAuto(filenameErr);
		auto.printAuto("modelName");
	}

}
