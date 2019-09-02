package automation.utilities;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class GridInfo {

	private URL proxyId;
	private String host;
	private int port;
	private String internalKey;
	private String session;
	private String inactivityTime;
	private String msg;
	private String success;

	public URL getProxyId() {
		return proxyId;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getInternalKey() {
		return internalKey;
	}

	public String getSession() {
		return session;
	}

	public String getInactivityTime() {
		return inactivityTime;
	}

	public String getMsg() {
		return msg;
	}

	public String getSuccess() {
		return success;
	}

	public GridInfo(Object object) throws MalformedURLException {
		try {
			String nodeIP = "";
			if(nodeIP.replace("http","").toLowerCase().matches(".*[a-z].*")){
				InetAddress address = InetAddress.getByName(nodeIP);
				proxyId = new URL("http://" + address.getHostAddress());
			}
			else proxyId = new URL(nodeIP);
			if ((proxyId.getHost() != null)) {
				host = proxyId.getHost();
			}
			port = proxyId.getPort();

		} catch (UnknownHostException e) {
		}

	}
}