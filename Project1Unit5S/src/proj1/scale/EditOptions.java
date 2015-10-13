/**
 * @author Xi Lin
 */

package proj1.scale;

import proj1.adapter.EditAndPrintAuto;

public class EditOptions implements Runnable {
	private EditAndPrintAuto auto;
	private String targetModel;
	private String targetOptionSet;
	private String targetOption;
	
	public EditOptions(EditAndPrintAuto auto, String model, String set, String option) {
		this.auto = auto;
		this.targetModel = model;
		this.targetOptionSet = set;
		this.targetOption = option;
	}
	
	/**
	 * Run a loop 5 times to increase the option price by 5
	 */
	public void run() {
		synchronized (auto) {
			float price = auto.getOptionPrice(targetModel, targetOptionSet, targetOption);
			long id = Thread.currentThread().getId();
			for (int i = 0; i < 5; ++i) {
				++price;
				auto.editOptionPrice(targetModel, targetOptionSet, targetOption, price);
				System.out.println("Thread " + id + " increased the option price");
			}
			
			System.out.println("Thread " + id + " finished. The price is " 
			                   + auto.getOptionPrice(targetModel, targetOptionSet, targetOption));
		}
	}
}
