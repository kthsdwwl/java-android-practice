/**
 * @author Xi Lin
 */

package proj1.server;

import java.util.ArrayList;
import java.util.Properties;

import proj1.model.Automobile;

public interface AutoServer {
	void buildAutoFromProperties(Properties prop);
	
	ArrayList<String> getModelList();
	
	Automobile getModelByRequest(String name);
}
