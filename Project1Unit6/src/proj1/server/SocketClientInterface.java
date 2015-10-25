/**
 * @author Xi Lin
 */

package proj1.server;

public interface SocketClientInterface {
	boolean openConnection();
    void handleSession();
    void closeSession();
}
