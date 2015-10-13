/**
 * @author Xi Lin
 */

package proj1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			while (true) {
				Socket socket = serverSocket.accept();
				DefaultSocketClient socketThread = new DefaultSocketClient(socket);
				socketThread.start();
			}
		} catch (IOException socketError) {
			System.err.println("Unable to connect");
		}
	}
}
