/**
 * @author Xi Lin
 */

package proj1.client;

import java.io.IOException;

import proj1.client.DefaultSocketClient;

public class Client {
	public static void main(String[] args) throws IOException {
		DefaultSocketClient socketThread = new DefaultSocketClient("localhost", 8888);
		socketThread.start();
	}
}
