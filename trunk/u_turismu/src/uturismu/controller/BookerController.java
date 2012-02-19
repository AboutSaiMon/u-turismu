package uturismu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uturismu.bean.AccountBean;

@Controller
public class BookerController {

	
	
	@RequestMapping(value="bo/home", method=RequestMethod.POST)
	public String showHomePage(HttpSession webSession) {
		AccountBean account = (AccountBean) webSession.getAttribute("account");
		if (account == null) {
			return "redirect:/";
		}
		return "home";
	}

}
