package proj1.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import proj1.model.Automobile;

public class CarModelOptionsIO {
	/**
	 * Create a Properties object using the input file
	 */
	public Properties BuildPropertiesFromFile(String filename) {
		try {
			FileInputStream in = new FileInputStream(filename);
			Properties prop = new Properties();
			prop.load(in);
			return prop;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Ask user to input choices and then output the choices
	 */
	@SuppressWarnings("resource")
	public void selectCarOptions(Automobile auto) {
		Scanner scanner = new Scanner(System.in);
		String userInput;
		ArrayList<String> optionSetList = auto.getOptionSetList();
		System.out.println("Car information:");
		auto.print();
		
		for (String optionSetName : optionSetList) {
			System.out.println("Please enter your choice for " + optionSetName);
			
			// if user did not enter legal choices, ask him to enter again
			while (true) {
				userInput = scanner.nextLine();
				boolean result = auto.setOptionChoice(optionSetName, userInput);
				if (result) break;
				System.out.println("Illegal input, please enter again");
			}
		}
		auto.printChoices();
		System.out.println("Total price is: " + auto.getTotalPrice());
	}
}
