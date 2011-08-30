package com.maven.ssh;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.concurrent.ExecutionException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;





/**
 * Goal which runs a command on a server.
 *
 * @goal ssh
 * 
 * 
 */


public class MavenSSH
    extends AbstractMojo
{
	/**
	 * The host to run cmd in.
	 *
	 * @parameter host="host" default-value=""
	 * @required
	 */
	private String host;
	/**
	 * The user to run cmd as.
	 *
	 * @parameter user="user" default-value=""
	 * @required
	 */
	private String user;
	/**
	 * The password.
	 *
	 * @parameter password="password" default-value=""
	 * 
	 */
	private String password;
	/**
	 * The keyFile.
	 *
	 * @parameter keyFile="string pointing to the keyfile" default-value=""
	 * 
	 */
	private String keyFile;
	/**
	 * The cmd to run.
	 *
	 * @parameter command="command" default-value=""
	 * 
	 */
	private String command;
	/**
	 * Trust the remote.
	 *
	 * @parameter trust="true/false" default-value="false"
	 */
	private Boolean trust;
	/**
	 * The port to connect.
	 *
	 * @parameter port="port" default-value="22"
	 */
	private Integer port;
	/**
	 * The timeout for connection.
	 *
	 * @parameter timeout=timeout default-value="0"
	 */
	private Integer timeout;
	/**
	 * The timeout for connection.
	 *
	 * @parameter knownHosts=string pointing to known hosts file default-value=""
	 */
	private String knownHosts;
	/**
	 * The timeout for connection.
	 *
	 * @parameter failonError=true/false default-value=""
	 */
	private Boolean failonError;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		if(verifyPassword())
		{
		SetSSHExecParameters ssh=new SetSSHExecParameters();
		try{
			ssh.Sshexec(host,user,password,command,port,trust,timeout,knownHosts,failonError);	
		}
		catch (ExecutionException e){
			//System.out.println(e.getCause().getMessage());
			throw new MojoExecutionException("There was a problem executing the command");
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new MojoExecutionException("There was a problem connecting to the server");
		}
		}
	
	}
    /**
     * 
     * 
     * @throws MojoExecutionException 
     * 
     * 
     */
   
	private boolean verifyPassword() throws MojoExecutionException {
		// TODO Auto-generated method stub
		
			if(!checkPassword()){
				if(!checkKeyFile()){
					return false;
				}
			}
				return true;
			
	}
	private boolean checkKeyFile() {
		// TODO Auto-generated method stub
		if(keyFile.isEmpty()){
			return false;
		}
		return true;
	}
	private Boolean checkPassword() throws MojoExecutionException {
		// TODO Auto-generated method stub
		if(password.isEmpty()){
			return false;
		}
		return true;
	
	}
}
