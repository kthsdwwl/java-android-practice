/**
 * @author Xi Lin
 */

package proj1.adapter;

public interface EditAndPrintAuto {
	public float getOptionPrice(String modelName, String setName, String optionName);
	
	public void editOptionPrice(String modelName, String setName, String optionName, float price);

	public void printOptionPrice(String modelName, String setName, String optionName);
}
