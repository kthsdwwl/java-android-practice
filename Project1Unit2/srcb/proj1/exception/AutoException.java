package proj1.exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class AutoException extends Exception {
	private final String logFilename = "src/log/logfile";
	private int errNo;
	private String errMsg;
	
	public AutoException(ErrType type) {
		setErrNo(type.ordinal());
		setErrMsg(type.toString());
	}

	public int getErrNo() {
		return errNo;
	}

	public void setErrNo(int errNo) {
		this.errNo = errNo;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/**
	 * Fix the exception by calling methods in helper class 
	 */
	public String fix() {
		Fix1to5 helper = new Fix1to5();
		log();
		return helper.fix(errNo);
	}

	/**
	 * Write the information to a log file
	 */
	public void log() {
		try {
			FileWriter file = new FileWriter(logFilename, true);
			BufferedWriter writer = new BufferedWriter(file);
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String dateString = dateFormat.format(now);
			
			writer.write(dateString + " ");
			switch(errNo) {
			case 0:
				writer.write("Miss model name.");
				break;
			case 1:
				writer.write("Miss base price.");
				break;
			case 2:
				writer.write("Miss option set nae.");
				break;
			case 3:
				writer.write("Miss option name.");
				break;
			case 4:
				writer.write("Miss option price.");
				break;
			default:
				writer.write("Unknown error.");
				break;
			}
			
			writer.write("\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
