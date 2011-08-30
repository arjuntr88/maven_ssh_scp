package com.sapient.ssh_scp;

public class SSHExec {
	String host;
	String user;
	String password;
	String command;
	Integer port;
	Boolean trust;
	Integer timeout;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Boolean getTrust() {
		return trust;
	}
	public void setTrust(Boolean trust) {
		this.trust = trust;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}


	
}
