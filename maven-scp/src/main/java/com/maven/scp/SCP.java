package com.maven.scp;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SCP
{
    String host;

    String user;

    String password;

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

    public JSch getJsch()
    {
        return jsch;
    }

    public void setJsch( JSch jsch )
    {
        this.jsch = jsch;
    }

    public Session getSession()
    {
        return session;
    }

    public void setSession( Session session )
    {
        this.session = session;
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

    public String getOutput()
    {
        return output;
    }

    public void setOutput( String output )
    {
        this.output = output;
    }

    public Boolean getAppend()
    {
        return append;
    }

    public void setAppend( Boolean append )
    {
        this.append = append;
    }

    public Boolean getVerbose()
    {
        return verbose;
    }

    public void setVerbose( Boolean verbose )
    {
        this.verbose = verbose;
    }

    
    
}
