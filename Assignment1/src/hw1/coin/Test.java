/**
 * @author Xi Lin
 */

package hw1.coin;

public class Test {
	public static void main(String[] args) {
		int i;
		int cases = 5;
		for (i = 1; i <= cases; ++i) {
			System.out.println("Now it's test case " + i + ": ");
			Simulation.simulate();
			System.out.println();
		}
	}
}
