package uturismu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bo")
public class BookerController {

	@RequestMapping("/home")
	public String showHomePage() {
		return "home";
	}

}
