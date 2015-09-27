/**
 * @author Xi Lin
 */

package hw1.coin;

import java.util.Random;

public class Coin {
	private String sideUp;
	
	public Coin() {
		toss();
	}
	
	public String getSideUp() {
		return sideUp;
	}
	
	public void toss() {
		Random random = new Random();
		int randomNum = Math.abs(random.nextInt()) % 2;
		if (randomNum == 0)
			sideUp = "heads";
		else
			sideUp = "tails";
	}
}
