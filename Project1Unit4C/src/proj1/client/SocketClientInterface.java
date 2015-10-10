/**
 * @author Xi Lin
 */

package proj1.client;

public interface SocketClientInterface {
	boolean openConnection();
    void handleSession();
    void closeSession();
}
