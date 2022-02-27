package uturismu.controller;

import static uturismu.controller.util.SessionCheck.isActiveSession;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uturismu.bean.HolidayPackageBean;
import uturismu.bean.Login;
import uturismu.bean.Signup;
import uturismu.bean.util.BeanMapping;
import uturismu.dto.HolidayPackage;
import uturismu.service.UserService;

@Controller
public class HolidayController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "{name}.details", method = RequestMethod.POST)
	public String showDetailsForRegisteredUser(@PathVariable("name") String name,
			@RequestParam("id") String id, Model model, HttpSession session) {

		HolidayPackage holiday = null;
		model.addAttribute("message", name);
		model.addAttribute("menu", "defaultMenu.jsp");
		// setta l'immagine dell'header
		model.addAttribute("image", "resources/img/testata2.gif");
		try {
			holiday = userService.getHolidayPackageByID(Long.valueOf(id));
		} catch (NumberFormatException e) {
			if (isActiveSession(session)) {
				model.addAttribute("content", "../holidaypackage/notFound.jsp");
				model.addAttribute("menu", "defaultMenu.jsp");
				return "home";
			} else {
				model.addAttribute("content", "holidaypackage/notFound.jsp");
				model.addAttribute("login", new Login());
				model.addAttribute("signup", new Signup());
				return "index";
			}
		}
		if (isActiveSession(session)) {
			if (holiday != null) {
				HolidayPackageBean bean = BeanMapping.getHolidayPackage(holiday);
				model.addAttribute("content", "../holidaypackage/details.jsp");
				model.addAttribute("holiday", bean);
			} else {
				model.addAttribute("content", "../holidaypackage/notFound.jsp");
			}
			model.addAttribute("menu", "defaultMenu.jsp");
			return "home";
		} else {
			if (holiday != null) {
				HolidayPackageBean bean = BeanMapping.getHolidayPackage(holiday);
				model.addAttribute("content", "holidaypackage/details.jsp");
				model.addAttribute("holiday", bean);
			} else {
				model.addAttribute("content", "holidaypackage/notFound.jsp");
			}
			model.addAttribute("login", new Login());
			model.addAttribute("signup", new Signup());
			return "index";
		}
	}

}