package com.wuxr.webtel.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

public class Telnet {
	 private String ip = ""; // 要telnet的IP地址  
	  
     private String port = "23"; //端口号，默认23

	private String user = "";//用户名  

     private String pwd = ""; //用户密码  

     private String osTag = "$";// 系统标示符号  

     private TelnetClient tc = new TelnetClient(); //新建一个 TelnetClient对象，此对象是 commons-net-2.0.jar包提供  

     private InputStream in; // 输入流，接收返回信息  

     private PrintStream out; //像 服务器写入 命令  

     public void connect() throws Exception {  

            try {  

                   tc.connect(ip, Integer.parseInt(port));

                   in = tc.getInputStream();  

                   out = new PrintStream(tc.getOutputStream());  

            } catch (Exception e) {  

                   System.out.println("connect error !");  
                   throw e;

            }  

     }  


     public String getOutput(){
    	 StringBuffer sb= new StringBuffer();
    	 
         try {  

             byte[] ch=new byte[5000];
             
             while(in.available()>0){
            	 in.read(ch);
            	 sb.append(new String(ch));
             }
      } catch (IOException e) {  

             e.printStackTrace();  

      }  

      return sb.toString();
    	 
     }

     public void execute(String command) {  

            out.println(command);  

            out.flush();  
     }



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public String getPort() {
		return port;
	}



	public void setPort(String port) {
		this.port = port;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public static void main(String[] args) {
		byte[] ch=new byte[200];
		System.out.println(ch.length);
	}
}
