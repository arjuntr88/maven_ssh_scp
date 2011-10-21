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
    /**
     * 
     * @param host
     * @param user
     * @param password
     * @param command
     * @param port
     * @param trust
     * @param timeout
     * @param knownHosts
     * @param failonError
     * @param output
     * @throws JSchException
     * @throws ExecutionException
     */
    
    String host;
    String user;
     String command; 
     Integer port; 
     Boolean trust; 
     Integer timeout; 
     String knownHosts;
     Boolean failonError;
     String output;
     Boolean append;
     
    
	public Boolean getAppend()
    {
        return append;
    }
    public void setAppend( Boolean append )
    {
        this.append = append;
    }
    public String getHost()
    {
        return host;
    }
    public void setHost( String host )
    {
        this.host = host;
    }
    public String getUser()
    {
        return user;
    }
    public void setUser( String user )
    {
        this.user = user;
    }
    public String getCommand()
    {
        return command;
    }
    public void setCommand( String command )
    {
        this.command = command;
    }
    public Integer getPort()
    {
        return port;
    }
    public void setPort( Integer port )
    {
        this.port = port;
    }
    public Boolean getTrust()
    {
        return trust;
    }
    public void setTrust( Boolean trust )
    {
        this.trust = trust;
    }
    public Integer getTimeout()
    {
        return timeout;
    }
    public void setTimeout( Integer timeout )
    {
        this.timeout = timeout;
    }
    public String getKnownHosts()
    {
        return knownHosts;
    }
    public void setKnownHosts( String knownHosts )
    {
        this.knownHosts = knownHosts;
    }
    public Boolean getFailonError()
    {
        return failonError;
    }
    public void setFailonError( Boolean failonError )
    {
        this.failonError = failonError;
    }
    public String getOutput()
    {
        return output;
    }
    public void setOutput( String output )
    {
        this.output = output;
    }
    public void Sshexec(String password) throws JSchException, ExecutionException{
		SSHExec ssh= new SSHExec();
		ssh.setCommand(command);
		ssh.setHost(host);
		ssh.setUser(user);
		ssh.setPassword(password);
		ssh.setCommand(command);		
		ssh.setAppend( append );
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
		if(!output.isEmpty())
		{
		    ssh.setOutput( output );
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
			//e.printStackTrace();
		    System.out.println(e.getMessage().toString());
			throw new JSchException();
		}
		
	}
	
    /**
	 * with keyfile and passphrase
	 *
	 */
	public void Sshexec( String keyFile, String passPhrase) throws JSchException, ExecutionException{
		SSHExec ssh= new SSHExec();
		ssh.setCommand(command);
		ssh.setHost(host);
		ssh.setUser(user);
		//ssh.setPassword(password);
		ssh.setKeyFile(keyFile);
		ssh.setCommand(command);
		ssh.setAppend( append );
		//System.out.println("method 2");
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
		if(passPhrase!=""){
			ssh.setPassPhrase(passPhrase);
		}
		if(!output.isEmpty())
		{
		    ssh.setOutput( output );
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
			//e.printStackTrace();
			throw new JSchException(e.getMessage().toString());
		}
		
	}
	

}
