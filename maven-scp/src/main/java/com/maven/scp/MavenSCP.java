package com.maven.scp;

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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Goal which touches a timestamp file.
 *
 * @goal scp
 * 
 * 
 */
public class MavenSCP
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
     */
    private String password;

    /**
     * The keyFile.
     * 
     * @parameter keyFile="string pointing to the keyfile" default-value=""
     */
    private String keyFile;

    /**
     * The passPhrase.
     * 
     * @parameter passPhrase="" default-value=""
     */
    private String passPhrase;

    /**
     * The command to run.
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
     * @parameter timeout="timeout" default-value="0"
     */
    private Integer timeout;

    /**
     * The timeout for connection.
     * 
     * @parameter knownHosts="string pointing to known hosts file" default-value=""
     */
    private String knownHosts;

    /**
     * The timeout for connection.
     * 
     * @parameter failonError="true/false" default-value="true"
     */
    private Boolean failonError;

    /**
     * The output file
     * 
     * @parameter output="string pointing to output file" default-value=""
     */
    private String output;

    /**
     * append output to the file
     * 
     * @parameter append="true/false" default-value="false"
     */
    private Boolean append;

    /**
     * verbosity of the output
     * 
     * @parameter verbose="true/false" default-value="false"
     */
    private Boolean verbose;

    public void execute()
        throws MojoExecutionException
    {
       SCP scp=new SCP();
       System.out.println("Maven SCP Plugin");
       verifyPassword();
       try
       {
           if ( checkKeyFile() )
           {
               File file = new File( keyFile );
               if ( file.exists() )
               {
                   // getLog().info("meth 1" + timeout);
                   scp.setHost( host );
                   scp.setVerbose( verbose );
                   scp.setUser( user );
                   scp.setPort( port );
                   scp.setTrust( trust );
                   scp.setTimeout( timeout );
                   scp.setKnownHosts( knownHosts );
                   scp.setFailonError( failonError );
                   scp.setOutput( output );
                   scp.setAppend( append );

                   //scp.Sshexec( keyFile, passPhrase );

               }
           }
           else if ( checkPassword() )
           {
               scp.setHost( host );
               scp.setVerbose( verbose );
               scp.setUser( user );
               scp.setPort( port );
               scp.setTrust( trust );
               scp.setTimeout( timeout );
               scp.setKnownHosts( knownHosts );
               scp.setFailonError( failonError );
               scp.setOutput( output );
               scp.setAppend( append );

               //scp.Sshexec( password );

               // getLog().info("meth 1" + timeout);
           }

       }
       /*catch ( ExecutionException e )
       {
           e.printStackTrace();
           System.out.println( "cause" + e.getCause().toString() );
           throw new MojoExecutionException( "There was a problem executing the command" );
       }*/
       catch ( Exception e )
       {
           // TODO: handle exception
           e.printStackTrace();
           System.out.println( "cause" + e.getMessage().toString() );
           throw new MojoExecutionException( "There was a problem connecting to the server" );
       }

    }
    
    private void verifyPassword()
    throws MojoExecutionException
{
    // TODO Auto-generated method stub

    if ( !checkPassword() )
    {
        if ( !checkKeyFile() )
        {
            throw new MojoExecutionException( "Please provide a password or a keyfile" );
        }
    }
    if ( checkPassword() )
    {
        if ( checkKeyFile() )
        {
            throw new MojoExecutionException( "Please provide either a password or keyfile not both" );
        }
    }

}

private boolean checkKeyFile()
{
    // TODO Auto-generated method stub
    try
    {

        if ( keyFile.isEmpty() )
        {
            return false;
        }
    }
    catch ( Exception e )
    {
        return false;
    }
    return true;
}

private Boolean checkPassword()
    throws MojoExecutionException
{
    // TODO Auto-generated method stub
    try
    {
        if ( password.isEmpty() )
        {
            return false;
        }
    }
    catch ( Exception e )
    {
        return false;
    }
    return true;

}
}
