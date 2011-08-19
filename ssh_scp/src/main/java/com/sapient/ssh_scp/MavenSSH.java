package com.sapient.ssh_scp;

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
	 */
	private String host;
	/**
	 * The user to run cmd as.
	 *
	 * @parameter user="user" default-value=""
	 */
	private String user;
	/**
	 * The password.
	 *
	 * @parameter password="password" default-value=""
	 */
	private String password;
	/**
	 * The cmd to run.
	 *
	 * @parameter command="command" default-value=""
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
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		if(verifyInput())
		{
		SshExec ssh=new SshExec();
		ssh.Sshexec(host,user,password,command,port,trust,timeout);
		}
	
	}
    /**
     * 
     * 
     * @throws MojoExecutionException 
     * 
     * 
     */
   
	private boolean verifyInput() throws MojoExecutionException {
		// TODO Auto-generated method stub
		
			checkFeild(host,"host");
				
			checkFeild(user,"user");
				
			checkFeild(password,"password");
				
			checkFeild(command,"command");
				
		/*if(file.isEmpty())
		{		
		getLog().info("Please set a file");	
		return false;
		}
		if(host.isEmpty())
		{
			getLog().info("Please set a host");
			
		}
		if(user.isEmpty())
		{
			getLog().info("Please set a user");
			
		}
		if(password.isEmpty())
		{
			getLog().info("Please set a password");
			
		}
		if(command.isEmpty())
		{
			getLog().info("Please set a command");
		}*/
	
	
	return true;
	}
	private void checkFeild(String feild, String feildName) throws MojoExecutionException {
		// TODO Auto-generated method stub
		try
		{
			if(feild.isEmpty())
			{
				getLog().info("SSH: Please enter a value for "+ feildName);
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			getLog().info("SSH: Please enter a value for "+ feildName);
			throw new MojoExecutionException("SSH: Please enter a value for "+ feildName);
		}
	
	}
}
