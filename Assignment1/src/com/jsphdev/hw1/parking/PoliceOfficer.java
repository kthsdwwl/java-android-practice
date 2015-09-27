/**
 * @author Xi Lin
 */

package com.jsphdev.hw1.parking;

public class PoliceOfficer {
	private String name;
	private String badgeNum;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBadgeNum() {
		return badgeNum;
	}
	
	public void setBadgeNum(String badgeNum) {
		this.badgeNum = badgeNum;
	}
	
	private double calculateFine(int parkedMinutes, int purchasedMinutes) {
		int exceedMinutes = parkedMinutes - purchasedMinutes;
		double fine = 25.0 + 10.0 * ((exceedMinutes + 59) / 60 - 1);
		return fine;
	}
	
	public boolean checkExpiration(ParkedCar car, ParkingMeter meter) {
		return car.getParkedMinutes() > meter.getPurchasedMinutes();
	}
	
	public void issueTicket(ParkedCar car, ParkingMeter meter) {
		if (checkExpiration(car, meter)) {
			double fine = calculateFine(car.getParkedMinutes(), 
				                        meter.getPurchasedMinutes());
			ParkingTicket ticket = new ParkingTicket();
			ticket.report(car, fine, this);
		}
		else {
			System.out.println("Legal, no ticket.");
		}
	}
}
