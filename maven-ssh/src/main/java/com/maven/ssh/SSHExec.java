package com.maven.ssh;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.concurrent.ExecutionException;

import com.jcraft.jsch.*;

public class SSHExec
    implements Logger
{
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

    String keyFile;

    String passPhrase;

    String output;

    Boolean append;

    Boolean verbose;

    public Boolean getVerbose()
    {
        return verbose;
    }

    public void setVerbose( Boolean verbose )
    {
        this.verbose = verbose;
    }

    public Boolean getAppend()
    {
        return append;
    }

    public void setAppend( Boolean append )
    {
        this.append = append;
    }

    public String getOutput()
    {
        return output;
    }

    public void setOutput( String output )
    {
        this.output = output;
    }

    public String getKeyFile()
    {
        return keyFile;
    }

    public void setKeyFile( String keyFile )
    {
        this.keyFile = keyFile;
    }

    public String getPassPhrase()
    {
        return passPhrase;
    }

    public void setPassPhrase( String passPhrase )
    {
        this.passPhrase = passPhrase;
    }

    public Boolean getFailonError()
    {
        return failonError;
    }

    public void setFailonError( Boolean failonError )
    {
        this.failonError = failonError;
    }

    public String getKnownHosts()
    {
        return knownHosts;
    }

    public void setKnownHosts( String knownHosts )
    {
        this.knownHosts = knownHosts;
    }

    public SSHExec()
    {
        host = "";
        user = "";
        password = "";
        command = "";
        port = 22;
        trust = false;
        timeout = 0;
        knownHosts = System.getProperty( "user.home" ) + ".ssh/known_hosts";
        failonError = false;
        keyFile = "";
        passPhrase = "";
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
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

    public void getChannel()
        throws JSchException
    {
        try
        {
            jsch = new JSch();
            if ( verbose )
            {
                JSch.setLogger( this );
            }
            java.util.Properties config = new java.util.Properties();
            if ( trust == true )
            {
                config.put( "StrictHostKeyChecking", "no" );
            }
            if ( !keyFile.isEmpty() )
            {
                jsch.addIdentity( keyFile, passPhrase );

            }

            if ( port != 22 )
            {
                session = jsch.getSession( user, host, port );
            }
            else
            {
                session = jsch.getSession( user, host, 22 );
            }
            if ( !password.isEmpty() )
            {
                session.setPassword( password );
            }
            if ( timeout != 0 )
            {
                session.setTimeout( timeout );
            }
            try
            {
                session.setConfig( config );
                if ( passPhrase != "" )
                {
                    // session.setPassword(password);

                }

            }
            catch ( Exception e )
            {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        catch ( Exception e )
        {
            System.out.println( e );
            e.printStackTrace();
            throw new JSchException();
        }
    }

    public void connectAndExecute()
        throws JSchException, ExecutionException, IOException
    {
        Channel channel;
        FileWriter out = null;
        if ( output != null )
        {
            
            out = new FileWriter( output, append );
        }
        session.connect();
        channel = session.openChannel( "exec" );
        try
        {

            ( (ChannelExec) channel ).setCommand( command );
            channel.setInputStream( null );
            ( (ChannelExec) channel ).setErrStream( System.err );

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while ( true )
            {
                while ( in.available() > 0 )
                {

                    int i = in.read( tmp, 0, 1024 );
                    if ( i < 0 )
                        break;
                    StringReader ins = new StringReader( new String( tmp, 0, i ) );
                    if ( output != null )
                    {

                        writeToFile( out, i, ins );
                    }
                    System.out.print( new String( tmp, 0, i ) );
                }
                if ( channel.isClosed() )
                {
                    if ( channel.getExitStatus() > 0 )
                    {
                        // System.out.println("exit-status: "+channel.getExitStatus());
                        // System.exit(channel.getExitStatus());
                        if ( failonError )
                        {

                            Throwable cause = new Throwable( "Remote command failed" );
                            throw new ExecutionException( cause );
                        }

                    }

                    break;
                }
                try
                {
                    Thread.sleep( 100 );
                }
                catch ( Exception ee )
                {
                }
            }

        }
        catch ( ExecutionException e )
        {
            System.out.println( e.getCause().toString() );
            throw new ExecutionException( e.getCause() );
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // System.out.println(e.getCause().toString());
            throw new JSchException( e.getMessage() );
        }
        finally
        {
            if ( out != null )
                out.close();
            channel.disconnect();
            session.disconnect();
        }
    }

    private void writeToFile( FileWriter out, int i, StringReader ins )
        throws IOException
    {
        char[] buffer = new char[8192];
        ins.read( buffer );
        out.write( buffer, 0, i );
        out.flush();

    }

    public boolean isEnabled( int arg0 )
    {
        // TODO Auto-generated method stub
        return true;
    }

    public void log( int arg0, String arg1 )
    {
        // TODO Auto-generated method stub

        System.out.println( arg1 );
    }

}
