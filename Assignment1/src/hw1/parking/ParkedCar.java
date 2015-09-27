/**
 * @author Xi Lin
 */

package hw1.parking;

public class ParkedCar {
	private String make;
	private String model;
	private String color;
	private String liceNum;
	private int parkedMinutes;
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getLiceNum() {
		return liceNum;
	}
	
	public void setLiceNum(String liceNum) {
		this.liceNum = liceNum;
	}
	
	public int getParkedMinutes() {
		return parkedMinutes;
	}
	
	public void setParkedMinutes(int parkedMinutes) {
		this.parkedMinutes = parkedMinutes;
	}
}
