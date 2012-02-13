package com.maven.scp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
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
    
    String host;
    
    String user;

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

    public void copyFiles( String keyFile, String passPhrase ) throws ExecutionException
    {
        // TODO Auto-generated method stub

        
        
    }

    public void copyFiles( String password ) 
    {
    	System.out.println("istoremote:"+isToRemote + " isfromremote: "+ isFromRemote);
    	try {
    		if(isFromRemote && !isToRemote)
    			download();
    		else if(isToRemote && !isFromRemote)
    			upload();
    		
    		else if(isFromRemote && isToRemote)
			throw new ExecutionException("Remote to remote",new Throwable("SCP from remote to remote not supported"));
			
    		else
    			throw new ExecutionException("Input is incorrect", new Throwable("Please check the input values for to and from"));
    	
    	} catch (ExecutionException e) {
				
				//e.printStackTrace();
				System.out.println(e.getCause().toString());
				
			}
        
        
        
    }
    private void upload() {

    	host=gethost(todir);
    	String rlocation=getlocation(todir);
    	setUser(todir);
		System.out.println("Uploading file "+ sourcefile +" to " +user+"@" + host+':' +rlocation);
		 java.util.Properties config = new java.util.Properties();

		FileInputStream fis=null;
	    try{

	      JSch jsch=new JSch();
	      Session session=jsch.getSession(user, host, 22);

	      // username and password will be given via UserInfo interface.
	      
	     	try{
	            config.put( "StrictHostKeyChecking", "no" );
	            session.setConfig(config);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
	     // System.out.println("before connect" + user+":"+password);
	      session.setPassword(password);
		  session.setTimeout(timeout);
			
	      session.connect();

	      //System.out.println("right before exec");
	      // exec 'scp -t rfile' remotely
	      String command="scp -p -t "+rlocation;
	      Channel channel=session.openChannel("exec");
	      ((ChannelExec)channel).setCommand(command);

	      // get I/O streams for remote scp
	      OutputStream out=channel.getOutputStream();
	      InputStream in=channel.getInputStream();

	      channel.connect();

	      if(checkAck(in)!=0){
		System.exit(0);
	      }

	      // send "C0644 filesize filename", where filename should not include '/'
	      long filesize=(new File(sourcefile)).length();
	      command="C0644 "+filesize+" ";
	      if(sourcefile.lastIndexOf('/')>0){
	        command+=sourcefile.substring(sourcefile.lastIndexOf('/')+1);
	      }
	      else{
	        command+=sourcefile;
	      }
	      command+="\n";
	      out.write(command.getBytes()); out.flush();
	      if(checkAck(in)!=0){
		System.exit(0);
	      }

	      // send a content of sourcefile
	      fis=new FileInputStream(sourcefile);
	      byte[] buf=new byte[1024];
	      while(true){
	        int len=fis.read(buf, 0, buf.length);
		if(len<=0) break;
	        out.write(buf, 0, len); //out.flush();
	      }
	      fis.close();
	      fis=null;
	      // send '\0'
	      buf[0]=0; out.write(buf, 0, 1); out.flush();
	      if(checkAck(in)!=0){
		System.exit(0);
	      }
	      out.close();

	      channel.disconnect();
	      session.disconnect();

	      System.exit(0);
	    }
	    catch(Exception e){
	      System.out.println(e.toString());
	      e.printStackTrace();
	      try{if(fis!=null)fis.close();}catch(Exception ee){}
	    }
		
	}

	private String getlocation(String uri) {
		String location;
		location=uri.substring(uri.indexOf(':')+1);
		System.out.println(location+"abcd");
		return location;
	}

	private String gethost(String uri) {
		
		String hostname;
		hostname=uri.substring(uri.indexOf('@')+1);
		hostname=hostname.substring(0,hostname.indexOf(':'));
		//System.out.println(hostname);
		return hostname;
	}
	private void setUser(String uri)
	{
		user=uri.substring(0,uri.indexOf('@'));
		//System.out.println(user);
	}
	private void download() {

		System.out.println("download");
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
