package bpmis.pxc.system.manager;

import bpmis.pxc.system.pojo.TUser;
import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	private TUser user;
	private String ip;
	private Date logindatetime;

	public TUser getUser() {
		return this.user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLogindatetime() {
		return this.logindatetime;
	}

	public void setLogindatetime(Date logindatetime) {
		this.logindatetime = logindatetime;
	}
}
