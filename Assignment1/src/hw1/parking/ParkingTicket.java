/**
 * @author Xi Lin
 */

package hw1.parking;

public class ParkingTicket {
	
	public void report(ParkedCar car, double fine, PoliceOfficer officer) {
		System.out.println("| Illegal Car Information: ");
		System.out.println("| Make: " + car.getMake());
		System.out.println("| Model: " + car.getModel());
		System.out.println("| Color: " + car.getColor());
		System.out.println("| Licence Number: " + car.getLiceNum());
		
		System.out.println("| Fine is: " + fine + " dollars.");
		
		System.out.println("| Police Officer Information: ");
		System.out.println("| Name: " + officer.getName());
		System.out.println("| Badge Number: " + officer.getBadgeNum());
	}
}
