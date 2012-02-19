package uturismu.controller.util;

import javax.servlet.http.HttpSession;

import uturismu.bean.AccountBean;

public class SessionCheck {
	
	private SessionCheck(){
		
	}
	
	public static boolean isActiveSession(HttpSession session){
		AccountBean account=null;
		account=(AccountBean) session.getAttribute("account");
		if(account == null){
			return false;
		}
		return true;
	}
	
}
