/**
 * @author Xi Lin
 */

package proj1.driver;

import proj1.adapter.BuildAuto;
import proj1.adapter.EditAndPrintAuto;
import proj1.adapter.ProxyAuto;
import proj1.scale.EditOptions;

public class Driver {
	private static String filename1 = "src/data/FordZTW.txt";
	private static String filename2 = "src/data/Audi.txt";
	private static String filename3 = "src/data/Volkswagen.txt";
	
	public static void main(String[] args) {
		ProxyAuto auto = new BuildAuto();
		EditAndPrintAuto eauto = new BuildAuto();
		
		// Build 3 auto instances
		auto.buildAuto(filename1);
		auto.buildAuto(filename2);
		auto.buildAuto(filename3);
		
		EditOptions run1 = new EditOptions(eauto, "Audi Q7", "brakes", "abs");
		EditOptions run2 = new EditOptions(eauto, "Audi Q7", "brakes", "abs");
		
		Thread t1 = new Thread(run1);
		Thread t2 = new Thread(run2);
		
		t1.start();
		t2.start();
	}
}
