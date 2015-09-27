package proj1.adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String modelName, String setName, String newName);
	
	public void updateOptionPrice(String modelName, String setName, String optionName, float price);
}
