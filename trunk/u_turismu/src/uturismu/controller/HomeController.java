/*
 * This file is part of "U Turismu" project. 
 * 
 * U Turismu is an enterprise application in support of calabrian tour operators.
 * This system aims to promote tourist services provided by the operators
 * and to develop and improve tourism in Calabria.
 *
 * Copyright (C) 2012 "LagrecaSpaccarotella" team.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uturismu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import uturismu.bean.AccountBean;
import uturismu.bean.CityBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.bean.Login;
import uturismu.bean.Signup;
import uturismu.bean.TourOperatorBean;
import uturismu.bean.TourOperatorSignup;
import uturismu.bean.UTurismuBean;
import uturismu.bean.util.BeanMapping;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.City;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;
import uturismu.exception.AccountException;
import uturismu.service.AdministratorService;
import uturismu.service.UserService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Controller
@SessionAttributes({ "account" })
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private AdministratorService adminService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String showIndex(HttpSession session, Model model) {
		AccountBean account = null;
		account = (AccountBean) session.getAttribute("account");
		if (account != null) {
			if (account.getType().equals(AccountType.TOUR_OPERATOR)) {
				return "forward:to/home";
			} else if (account.getType().equals(AccountType.BOOKER)) {
				return "forward:bo/home";
			}
		}
		model.addAttribute("login", new Login());
		model.addAttribute("signup", new Signup());
		List<HolidayPackageBean> list = BeanMapping.encode(userService.getHolidayPackages());
		model.addAttribute("holidayList", list);
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid Login login, BindingResult result, Model model) {
		StringBuffer forwardPage = new StringBuffer("forward:");
		// se ci sono errori nella compilazione dei campi
		if (result.hasErrors()) {
			model.addAttribute("signup", new Signup());
			// restituisce il nome della pagina iniziale con errore
			return "index";
		}
		try {
			// effettua il login
			Account account = userService.logIn(login.getLoginEmail(), login.getLoginPassword());
			// dichiara il bean che verrà passato nella sessione
			UTurismuBean bean = null;
			// se è un tour operator
			if (account.getType().equals(AccountType.TOUR_OPERATOR)) {
				TourOperator tourOperator = account.getTourOperator();
				// codifica i due oggetti DTO in un bean
				bean = BeanMapping.encode(account, tourOperator);
				forwardPage.append("to/home");
			} else if (account.getType().equals(AccountType.BOOKER)) {
				// acquisisce il booker mediante il suo id
				Booker booker = userService.getBookerById(account.getBooker().getId());
				// codifica i due oggetti DTO in un bean
				bean = BeanMapping.encode(account, booker);
				forwardPage.append("bo/home");
			}
			model.addAttribute("account", bean);
		} catch (AccountException e) {
			model.addAttribute("message", e.getMessage());
			return "errorPage";
		}
		return forwardPage.toString();
	}

	@RequestMapping(value = "logout")
	public String logOut(HttpSession session, SessionStatus status, ModelMap model) {
		model.remove("account");
		status.setComplete();
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "signup", params = "newTo", method = RequestMethod.POST)
	public String signup(@Valid TourOperatorSignup signup, BindingResult result, Model model, HttpSession session) {
		// se ci sono errori nella validazione o la città non è stata selezionata
		if (result.hasErrors() || signup.getCity() == 0) {
			List<CityBean> cities = BeanMapping.encode(adminService.getCities());
			model.addAttribute("cities", cities);
			
			TourOperatorSignup bean = (TourOperatorSignup) session.getAttribute("signup");
			model.addAttribute("signup", bean);
			
			return "touroperatorSignup";
		}
		// altrimenti recupera l'oggetto City in base all'id specificato nel bean
		// "signup"
		City city = adminService.getCityById(signup.getCity());
		// recupera un oggetto "TourOperator" mediante il bean "signup"
		TourOperator tourOperator = BeanMapping.getTourOperator(signup, city);
		// recupera l'oggetto account
		Account account = BeanMapping.getAccount((TourOperatorSignup) session.getAttribute("signup"));
		// rende l'account persistente
		userService.createAccount(account, tourOperator);
		// crea il bean da settare nella sessione
		TourOperatorBean accountBean = BeanMapping.encode(account, tourOperator);
		model.addAttribute("account", accountBean);
		return "redirect:home";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String tryToSignup(@Valid Signup signup, BindingResult result, String user, Model model,
			HttpSession session) {
		// se ci sono errori nella compilazione dei campi
		if (result.hasErrors()) {
			model.addAttribute("login", new Login());
			// restituisce il nome della pagina iniziale con errore
			return "index";
		}
		if (user.equals("Booker")) {

			return "";
		} else {
			TourOperatorSignup bean = new TourOperatorSignup();
			bean.setEmail(signup.getSignupEmail());
			bean.setPassword(signup.getSignupPassword());
			model.addAttribute("signup", bean);
			session.setAttribute("signup", bean);
			List<CityBean> cities = BeanMapping.encode(adminService.getCities());
			model.addAttribute("cities", cities);
			return "touroperator/signup";
		}
	}
	
	@RequestMapping(value="/showPackage",method=RequestMethod.GET)
	public String getPackages(@RequestParam(value="type", required=true) String type,HttpSession session,Model model) {
		if(!isActiveSession(session)){
			return "forward:";
		}
		
		StringBuffer page=new StringBuffer("forward:");
		AccountBean account=(AccountBean) session.getAttribute("account");
		if(account.getType().equals(AccountType.TOUR_OPERATOR)){
			page.append("to/packages");
		}else if(account.getType().equals(AccountType.BOOKER)){
			page.append("bo/packages");
		}else{
			page.append("/");
		}
		
		return page.toString();
	} 

}