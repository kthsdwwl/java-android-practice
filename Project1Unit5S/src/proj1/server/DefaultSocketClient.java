/**
 * @author Xi Lin
 */

package proj1.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants{

	private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Socket socket;

    public DefaultSocketClient(Socket socket) {
    	this.setSocket(socket);
    }
    
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
    
	public void run() {
		if (openConnection()){
			handleSession();
			closeSession();
		}
	}
    
	/**
	 * Create a socket and initialize reader and writer
	 */
	@Override
	public boolean openConnection() {
		try {
			writer = new ObjectOutputStream(socket.getOutputStream());
			reader = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e){
		   if (DEBUG) System.err.println("Unable to obtain stream to/from client");
		   return false;
		}
		return true;

	}

	/**
	 * Receive messages from client and handle them
	 */
	@Override
	public void handleSession() {
		try {
			Object object;
			while ((object = reader.readObject()) != null)
				handleInput(object);
			sendOutput("Bye bye!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			System.out.println("Server is processing...");
		} catch (EOFException e) {
			System.out.println("Server is processing...");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * According to client input, do the corresponding jobs
	 */
	private void handleInput(Object obj) throws IOException {
		BuildCarModelOptions build = new BuildCarModelOptions();
		String className = obj.getClass().getSimpleName();

		// client uploads a Properties object
		if (className.equals("Properties")) {
			Properties prop = (Properties) obj;
			build.buildAutoFromProperties(prop);
			sendOutput("Successfully created an Automobile instance");
		}
		
		// client wants to configure an Automobile object
		else if (className.equals("String")) {
			String request = (String) obj;
			if (request.equals("configuration")) {
				sendOutput(build.getModelList());
			}
			else {
				sendOutput(build.getModelByRequest(request));
			}
		}
	}

	/**
	 * Send message to client
	 */
	private void sendOutput(Object obj) throws IOException {
		writer.writeObject(obj);
		writer.flush();
	}

	/**
	 * Close the connection and set writer and reader to be null 
	 */
	@Override
	public void closeSession() {
		try {
			writer = null;
			reader = null;
			socket.close();
		} catch (IOException e) {
			if (DEBUG) System.err.println("Error closing socket to client");
		}
		
	}


}
