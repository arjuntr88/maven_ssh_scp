/**
 * 
 */
package com.maven.ssh;



import java.util.concurrent.ExecutionException;

import com.jcraft.jsch.*;

/**
 * @author arames
 *
 */
public class SetSSHExecParameters {
	public void Sshexec(String host, String user, String password, String command, Integer port, Boolean trust, Integer timeout, String knownHosts, Boolean failonError) throws JSchException, ExecutionException{
		SSHExec ssh= new SSHExec();
		ssh.setCommand(command);
		ssh.setHost(host);
		ssh.setUser(user);
		ssh.setPassword(password);
		ssh.setCommand(command);
		if(trust){
			ssh.setTrust(trust);
		}
		if(port!=22){
			ssh.setPort(port);
		}
		if(timeout!=0){
			ssh.setTimeout(timeout);
		}
		if(knownHosts!=""){
			ssh.setKnownHosts(knownHosts);
		}
		if(failonError){
			ssh.setFailonError(failonError);
		}
		try{
			ssh.getChannel();
			ssh.connectAndExecute();
		}
		catch(ExecutionException e){
			throw new ExecutionException(e.getCause());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new JSchException();
		}
		
	}

}
