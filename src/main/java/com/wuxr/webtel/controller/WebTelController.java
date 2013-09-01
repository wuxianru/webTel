package com.wuxr.webtel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuxr.webtel.bean.Command;
import com.wuxr.webtel.bean.Host;
import com.wuxr.webtel.bean.Result;
import com.wuxr.webtel.bean.Screen;
import com.wuxr.webtel.bean.Telnet;

@Controller
public class WebTelController {
	
	private final static String WEBTEL="_WEBTEL";
	
	@RequestMapping(method=RequestMethod.POST,value="login")
	public @ResponseBody Result login(@RequestBody Host host, HttpSession session){
		Result rs =new Result();
		Telnet tn = new Telnet();
		System.out.println(host.getIp());
		tn.setIp(host.getIp());
		tn.setPort(host.getPort());
		tn.setUser(host.getUserName());
		tn.setPwd(host.getPasswd());
		try {
			tn.connect();
		} catch (Exception e) {
			rs.setResMsg(e.getMessage());
			rs.setResCode("9999");
			e.printStackTrace();
			return rs;
		}
		rs.setResMsg("AAAA");
		session.setAttribute(WEBTEL, tn);
		
		return rs;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="refresh")
	public @ResponseBody String refresh(HttpSession session){
		
		Telnet tn=(Telnet)session.getAttribute(WEBTEL);
		String sc=tn.getOutput();
		return sc.replaceAll("\\r\\n", "<\\br>").replace("\\r", "<\\br>").replace("\\n", "<\\br>");
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="execute")
	public @ResponseBody Result execute(@RequestBody Command cmd ,HttpSession session){
		Result rs= new Result();
		Telnet tn = (Telnet) session.getAttribute(WEBTEL);
		if(null==tn){
			rs.setResCode("9001");
			rs.setResMsg("not logged in yet");
		}else{
			tn.execute(cmd.getLine());
			rs.setResCode("AAAA");
		}
		return rs;
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="test")
	public @ResponseBody Screen test(){
		System.out.println("test...");
		Screen sc = new Screen();
		sc.setLine("aaa");
		return sc;
	}
}
