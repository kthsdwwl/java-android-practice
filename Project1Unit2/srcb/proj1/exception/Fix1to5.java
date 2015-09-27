package proj1.exception;

import java.util.Scanner;

public class Fix1to5 {
	/**
	 * Fix missing model name 
	 */
	@SuppressWarnings("resource")
	private String fix1() {
		System.out.println("Miss model name!");
		System.out.println("Please re-enter the name!");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	/**
	 * Fix missing base price
	 */
	@SuppressWarnings("resource")
	private String fix2() {
		System.out.println("Miss base price!");
		System.out.println("Please re-enter the price!");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	/**
	 * Fix missing option set name
	 */
	@SuppressWarnings("resource")
	private String fix3() {
		System.out.println("Miss option set name!");
		System.out.println("Please re-enter the name!");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	/**
	 * Fix missing option name
	 */
	@SuppressWarnings("resource")
	private String fix4() {
		System.out.println("Miss option name!");
		System.out.println("Please re-enter the name!");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	/**
	 * Fix missing price 
	 */
	@SuppressWarnings("resource")
	private String fix5() {
		System.out.println("Miss option price!");
		System.out.println("Please re-enter the price!");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	/**
	 * Fix problems according to the given error number 
	 */
	public String fix(int errNo) {
		switch(errNo) {
		case 0:
			return fix1();
		case 1:
			return fix2();
		case 2:
			return fix3();
		case 3:
			return fix4();
		case 4:
			return fix5();
		default:
			System.out.println("Unknown error!");
			break;
		}
		return null;
	}
}
