/**
 * @author Xi Lin
 */

package hw1.parking;

public class Test {
	public static void testParking(int parkedMinutes, int purchasedMinutes) {
		ParkedCar testCar = new ParkedCar();
		ParkingMeter testMeter = new ParkingMeter();
		PoliceOfficer testOfficer = new PoliceOfficer();
		boolean expire;
		
		// initialize the information of car and officer
		testCar.setMake("TOYOTA");
		testCar.setModel("Camry");
		testCar.setColor("Red");
		testCar.setLiceNum("ABC001");
		testOfficer.setName("Mike");
		testOfficer.setBadgeNum("DEF001");
		
		// set the parked minutes and purchased minutes and start testing
		System.out.println("The car has parked " + parkedMinutes + " min.");
		System.out.println("The car has purchased " + purchasedMinutes + " min.");
		
		testCar.setParkedMinutes(parkedMinutes);
		testMeter.setPurchasedMinutes(purchasedMinutes);
		expire = testOfficer.checkExpiration(testCar, testMeter);
		if (expire) {
			System.out.println("Expired, here is the ticket: ");
			testOfficer.issueTicket(testCar, testMeter);
		}
		else
			System.out.println("Legal, no ticket.");
	}
	
	public static void main(String[] args) {
		int i;
		int[] parkedMinutes = {10, 20, 40, 120, 180};
		int[] purchasedMinutes = {20, 20, 20, 20, 20};
		for (i = 0; i < 5; ++i) {
			testParking(parkedMinutes[i], purchasedMinutes[i]);
			System.out.println();
		}
	}
}
