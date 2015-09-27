/**
 * @author Xi Lin
 */

package hw2.exception;

@SuppressWarnings("serial")
public class TooManyRecordsException extends Exception{

	public TooManyRecordsException() {
		super();
	}
	
	public TooManyRecordsException(String msg) {
		super(msg);
	}
	
	public void fixProblem() {
		System.out.println("Exception: Should be at most 40 records.");
	}
	
}
