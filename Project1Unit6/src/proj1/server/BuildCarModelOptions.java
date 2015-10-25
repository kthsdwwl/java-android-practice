/**
 * @author Xi Lin
 */

package proj1.server;

import java.util.ArrayList;
import java.util.Properties;

import proj1.adapter.BuildAuto;
import proj1.model.Automobile;

public class BuildCarModelOptions implements AutoServer {

	@Override
	public void buildAutoFromProperties(Properties prop) {
		AutoServer s = new BuildAuto();
		s.buildAutoFromProperties(prop);
	}

	@Override
	public ArrayList<String> getModelList() {
		AutoServer s = new BuildAuto();
		return s.getModelList();
	}

	@Override
	public Automobile getModelInstance(String name) {
		AutoServer s = new BuildAuto();
		return s.getModelInstance(name);
	}

}
