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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 * 
 * @phase process-sources
 */


public class MavenSshScp
    extends AbstractMojo
{
	/**
	 * The file to copy.
	 *
	 * @parameter file="file" default-value=""
	 */
	private String file;
	/**
	 * The file to copy.
	 *
	 * @parameter host="host" default-value=""
	 */
	private String host;
	/**
	 * The file to copy.
	 *
	 * @parameter user="user" default-value=""
	 */
	private String user;
	/**
	 * The file to copy.
	 *
	 * @parameter password="password" default-value=""
	 */
	private String password;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		if(verifyInput())
		{
		Ssh ssh=new Ssh();
		ssh.Sshexec();
		}
	
	}
    /**
     * Location of the file.
     * @return 
     * @parameter expression="${project.build.directory}"
     * @required
     */
   /* private File outputDirectory;

    public void execute()
        throws MojoExecutionException
    {
        File f = outputDirectory;

        if ( !f.exists() )
        {
            f.mkdirs();
        }

        File touch = new File( f, "touch.txt" );

        FileWriter w = null;
        try
        {
            w = new FileWriter( touch );

            w.write( "touch.txt" );
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error creating file " + touch, e );
        }
        finally
        {
            if ( w != null )
            {
                try
                {
                    w.close();
                }
                catch ( IOException e )
                {
                    // ignore
                }
            }
        }
    }*/

	private boolean verifyInput() {
		// TODO Auto-generated method stub
		try
		{
		if(file.isEmpty())
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
			getLog().info("Please set a host");
			
		}
		if(password.isEmpty())
		{
			getLog().info("Please set a host");
			
		}
	}
	
	catch (Exception e) {
		// TODO: handle exception
		//getLog().info(e.toString());
		return false;
	}
	return true;
	}
}
