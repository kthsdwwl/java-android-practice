/**
 * @author Xi Lin
 */

package com.jsphdev.hw1.coin;

public class Simulation {
	public static void simulate() {
		int i;
		int headsCount = 0;
		int tailsCount = 0;
		Coin coin = new Coin();
		
		System.out.println("The coin's initial side is: " + coin.getSideUp());
		
		for (i = 0; i < 20; ++i) {
			coin.toss();
			System.out.println("Now the coin's side is: " + coin.getSideUp());
			if (coin.getSideUp().equals("heads"))
				++headsCount;
			else
				++tailsCount;
		}
		
		System.out.println("The number of times heads is facing up is: " 
			               + headsCount);
		System.out.println("The number of times tails is facing up is: " 
	                       + tailsCount);
	}
}
