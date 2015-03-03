package bpmis.pxc.system.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClientManager {
	private static ClientManager instance = new ClientManager();

	public static ClientManager getInstance() {
		return instance;
	}

	private Map<String, Client> map = new HashMap();

	public void addClinet(String sessionId, Client client) {
		this.map.put(sessionId, client);
	}

	public void removeClinet(String sessionId) {
		this.map.remove(sessionId);
	}

	public Client getClient(String sessionId) {
		return (Client) this.map.get(sessionId);
	}

	public Collection<Client> getAllClient() {
		return this.map.values();
	}
}
