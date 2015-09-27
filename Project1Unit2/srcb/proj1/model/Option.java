package proj1.model;

import java.io.Serializable;

public class Option implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private float price;
	
	protected Option() {
		this.setName(null);
		this.setPrice(0);
	}
	
	protected Option(String name, float price) {
		this.setName(name);
		this.setPrice(price);
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected float getPrice() {
		return price;
	}

	protected void setPrice(float price) {
		this.price = price;
	}
	
	protected void print() {
		System.out.println("Option: " + name + ", Price: " + price);
	}
}
