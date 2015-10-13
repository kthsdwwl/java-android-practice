package proj1.client;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import proj1.model.Automobile;

public class SocketIO {
	private String strHost;
    private int iPort;
    
    public SocketIO(String strHost, int iPort) {
    	this.setStrHost(strHost);
    	this.setiPort(iPort);
    }
    
	public void setStrHost(String strHost) {
		this.strHost = strHost;
	}
	
	public void setiPort(int iPort) {
		this.iPort = iPort;
	}
	
	/**
	 * Send request to server and get the available model list
	 */
	@SuppressWarnings({ "unchecked" })
	public ArrayList<String> getModelList() {
		ArrayList<String> list = new ArrayList<String>();
	
		try {
			Socket socket = new Socket(strHost, iPort);
			ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
			writer.writeObject("configuration");
			writer.flush();
			list = (ArrayList<String>) reader.readObject();	
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}
	
	/**
	 * Send request to server and get the specific automobile object
	 */
	public Automobile getModel(String modelName) {
		Automobile auto = null;
		
		try {
			Socket socket = new Socket(strHost, iPort);
			ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
			writer.writeObject(modelName);
			writer.flush();
			auto = (Automobile) reader.readObject();	
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			return auto;
		}
		return auto;
	}
	
	/**
	 * Send properties object to server
	 */
	public void sendProperties(FileInputStream fis) {
		try {
			Socket socket = new Socket(strHost, iPort);
			ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
			
			// build properties object
			Properties prop = new Properties();
			prop.load(fis);
			System.out.println(prop.getProperty("ModelName"));
			
			// send object to server
			writer.writeObject(prop);
			writer.flush();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
