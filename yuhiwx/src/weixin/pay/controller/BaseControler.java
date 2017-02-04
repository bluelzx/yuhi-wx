package weixin.pay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseControler {

	@ModelAttribute
	public void init(){
		System.out.println("");
	}
	
	public String getRemortIP(HttpServletRequest request) {
		  if (request.getHeader("x-forwarded-for") == null) {
		   return request.getRemoteAddr();
		  }
		  return request.getHeader("x-forwarded-for");
	}
}
