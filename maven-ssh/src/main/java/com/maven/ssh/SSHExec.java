package com.maven.ssh;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import com.jcraft.jsch.*;

public class SSHExec {
	String host;
	String user;
	String password;
	String command;
	Integer port;
	Boolean trust;
	Integer timeout;
	JSch jsch;
	Session session;
	String knownHosts;
	Boolean failonError;
	
	public Boolean getFailonError() {
		return failonError;
	}
	public void setFailonError(Boolean failonError) {
		this.failonError = failonError;
	}
	public String getKnownHosts() {
		return knownHosts;
	}
	public void setKnownHosts(String knownHosts) {
		this.knownHosts = knownHosts;
	}
	public SSHExec() {
		host="";
		user="";
		password="";
		command="";
		port=22;
		trust=false;
		timeout=0;
		knownHosts=System.getProperty("user.home")+".ssh/known_hosts";
		failonError=false;
	}
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
	public void getChannel() throws JSchException{
		try{
		jsch = new JSch();
		
		java.util.Properties config = new java.util.Properties();
	      if(trust==true)
	      {
	    	  config.put("StrictHostKeyChecking", "no");
	      }
	      
	      
	   
	      if(port!=22){
	    	  session=jsch.getSession(user, host, port);
	      }
	      else{
	    	  session=jsch.getSession(user, host, 22);  
	      }
	      if(timeout!=0){
	    	  session.setTimeout(timeout);
	      }
	      try{
	      session.setConfig(config);
	      session.setPassword(password);
	      }
	      catch (Exception e) {
			// TODO: handle exception
	    	 
		}
		}
		catch (Exception e) {
			System.out.println(e);
		      e.printStackTrace();
		      throw new JSchException();
		}
	}
	
	public void connectAndExecute() throws JSchException, ExecutionException {
		try {
			session.connect();
			Channel channel=session.openChannel("exec");
		    ((ChannelExec)channel).setCommand(command);
		    channel.setInputStream(null);
		    ((ChannelExec)channel).setErrStream(System.err);

		      InputStream in=channel.getInputStream();

		      channel.connect();
		      
		      byte[] tmp=new byte[1024];
		      while(true){
		        while(in.available()>0){
		          int i=in.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
		        }
		        if(channel.isClosed()){
		          if(channel.getExitStatus()>0){
		        	  System.out.println("exit-status: "+channel.getExitStatus());
		        	  //System.exit(channel.getExitStatus());
		        	  if(failonError){
		        		  Throwable cause=new Throwable("Remote command failed");
						throw new ExecutionException(cause);
		        	  }
		        		  
		        		  
		          }
		        	
		          
		          break;
		        }
		        try{
		        	Thread.sleep(1000);
		        }
		        catch(Exception ee){}
		      }
		      channel.disconnect();
		      session.disconnect();
			
		} 
		catch (ExecutionException e){
			throw new ExecutionException(null);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new JSchException();
		}
	}

	
}
