package com.maven.scp;

import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SCP
{
    String sourcefile;
    
    String todir;

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

    Boolean isToRemote, isFromRemote, isRecursive;

    public String getSourcefile()
    {
        return sourcefile;
    }

    public void setSourcefile( String sourcefile )
    {
        
        this.sourcefile = sourcefile;
        this.isFromRemote = isRemote(this.sourcefile);
        this.isRecursive = this.sourcefile.endsWith( "*" );
    }



    public String getTodir()
    {
        return todir;
    }

    public void setTodir( String todir )
    {
        this.todir = todir;
        this.isToRemote = isRemote(this.todir);
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

    public void copyFiles( String keyFile, String passPhrase )
    {
        // TODO Auto-generated method stub
        
        
        
    }

    public void copyFiles( String password )
    {
        // TODO Auto-generated method stub
        
    }
    private Boolean isRemote( String URI )
    {
        // TODO Auto-generated method stub
        int index = URI.indexOf( '@' );
        if(index < 0)return false;
        return true;
    }
    
    static int checkAck(InputStream in) throws IOException{
        int b=in.read();
        // b may be 0 for success,
        //          1 for error,
        //          2 for fatal error,
        //          -1
        if(b==0) return b;
        if(b==-1) return b;

        if(b==1 || b==2){
          StringBuffer sb=new StringBuffer();
          int c;
          do {
        c=in.read();
        sb.append((char)c);
          }
          while(c!='\n');
          if(b==1){ // error
        System.out.print(sb.toString());
          }
          if(b==2){ // fatal error
        System.out.print(sb.toString());
          }
        }
        return b;
      }
    
}
