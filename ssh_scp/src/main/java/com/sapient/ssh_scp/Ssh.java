/**
 * 
 */
package com.sapient.ssh_scp;

import java.io.*;

import com.jcraft.jsch.*;

/**
 * @author arames
 *
 */
public class Ssh {
	public void Sshexec(){
		try{
		JSch jsch=new JSch();  
		 
	      String host=null;
	     /* if(arg.length>0){
	        host=arg[0];
	      }
	      else{
	        host=JOptionPane.showInputDialog("Enter username@hostname",
	                                         System.getProperty("user.name")+
	                                         "@localhost"); 
	      }*/
	      String user="root";
	      host="10.209.6.22";
	      java.util.Properties config = new java.util.Properties(); config.put("StrictHostKeyChecking", "no");
	      
	      Session session=jsch.getSession(user, host, 22);
	      session.setConfig(config);
	      session.setPassword("root123");
	      /*
	      String xhost="127.0.0.1";
	      int xport=0;
	      String display=JOptionPane.showInputDialog("Enter display name", 
	                                                 xhost+":"+xport);
	      xhost=display.substring(0, display.indexOf(':'));
	      xport=Integer.parseInt(display.substring(display.indexOf(':')+1));
	      session.setX11Host(xhost);
	      session.setX11Port(xport+6000);
	      */

	      // username and password will be given via UserInfo interface.
	      //UserInfo ui=new MyUserInfo();
	      //session.setUserInfo(ui);
	      session.connect();

	      //String command=JOptionPane.showInputDialog("Enter command", 
	                                                 //"set|grep SSH");
	      String command="whoami";
	      Channel channel=session.openChannel("exec");
	      ((ChannelExec)channel).setCommand(command);

	      // X Forwarding
	      // channel.setXForwarding(true);

	      //channel.setInputStream(System.in);
	      channel.setInputStream(null);

	      //channel.setOutputStream(System.out);

	      //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
	      //((ChannelExec)channel).setErrStream(fos);
	      ((ChannelExec)channel).setErrStream(System.err);

	      InputStream in=channel.getInputStream();

	      channel.connect();

	      byte[] tmp=new byte[1024];
	      while(true){
	        while(in.available()>0){
	          int i=in.read(tmp, 0, 1024);
	          if(i<0)break;
	          System.out.print(new String(tmp, 0, i));
	        }
	        if(channel.isClosed()){
	          //System.out.println("exit-status: "+channel.getExitStatus());
	          break;
	        }
	        try{Thread.sleep(1000);}catch(Exception ee){}
	      }
	      channel.disconnect();
	      session.disconnect();
	    }
	    catch(Exception e){
	      System.out.println(e);
	    }
	}

}
