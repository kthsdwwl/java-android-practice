package proj1.driver;

import proj1.adapter.BuildAuto;
import proj1.database.Database;

public class Driver {
	public static void main(String[] args) {
		Database db = new Database();
		db.initialize();
		
		// test insert
		BuildAuto auto = new BuildAuto();
		auto.buildAuto("src/data/Volkswagen.txt");
		auto.buildAuto("src/data/Audi.txt");
		
		System.out.println("Print first car:");
		auto.printAuto("Volkswagen");
		System.out.println();
		
		System.out.println("Print second car:");
		auto.printAuto("Audi Q7");
		System.out.println();
		
		// test update
		System.out.println("Update Volkswagen");
        auto.updateOptionName("Volkswagen", "color", "Infra-Red Clearcoat", "Red");
        auto.updateOptionPrice("Volkswagen", "trasmission", "automatic", 500);
        System.out.println("Update result:");
        auto.printAuto("Volkswagen");
		System.out.println();
        
        // test delete
        System.out.println("Delete Volkswagen");
        auto.deleteAutomobile("Volkswagen");
        System.out.println("Try to print Volkswagen after deletion:");
        auto.printAuto("Volkswagen");
	}
}
