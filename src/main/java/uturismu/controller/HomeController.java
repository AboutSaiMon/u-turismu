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

import static uturismu.controller.util.SessionCheck.isActiveSession;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import uturismu.bean.AccountBean;
import uturismu.bean.BookerBean;
import uturismu.bean.BookerSignup;
import uturismu.bean.CityBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.bean.Login;
import uturismu.bean.Signup;
import uturismu.bean.TourOperatorBean;
import uturismu.bean.TourOperatorSignup;
import uturismu.bean.UserSignup;
import uturismu.bean.util.BeanMapping;
import uturismu.bean.util.DateGenerator;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.City;
import uturismu.dto.HolidayPackage;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;
import uturismu.exception.AccountException;
import uturismu.service.AdministratorService;
import uturismu.service.TourOperatorService;
import uturismu.service.UserService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Controller
@SessionAttributes({ "account" })
public class HomeController {

	@Autowired
	private TourOperatorService touroperatorService;
	@Autowired
	private UserService userService;
	@Autowired
	private AdministratorService adminService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String showIndex(HttpSession session, Model model) {
		AccountBean account = null;
		account = (AccountBean) session.getAttribute("account");
		if (account != null) {
			List<HolidayPackage> holidayResult = null;
			if (account.getType().equals(AccountType.TOUR_OPERATOR)) {
				holidayResult = touroperatorService.getAllHolidayPackages(account.getUserId());
			} else if (account.getType().equals(AccountType.BOOKER)) {
				holidayResult = userService.getHolidayPackages();
			}
			List<HolidayPackageBean> packs = BeanMapping.encode(holidayResult);
			model.addAttribute("menu", "defaultMenu.jsp");
			model.addAttribute("content", "content.jsp");
			model.addAttribute("packs", packs);
			return "home";
		}
		model.addAttribute("login", new Login());
		model.addAttribute("signup", new Signup());
		List<HolidayPackageBean> list = BeanMapping.encode(userService.getHolidayPackages());
		model.addAttribute("holidayList", list);
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid Login login, BindingResult result, Model model) {
		// se ci sono errori nella compilazione dei campi
		if (result.hasErrors()) {
			model.addAttribute("signup", new Signup());
			// restituisce il nome della pagina iniziale con errore
			return "index";
		}
		try {
			// effettua il login
			Account account = userService.logIn(login.getLoginEmail(), login.getLoginPassword());
			// dichiara l'account che verrà settato nella sessione
			AccountBean bean = null;
			// verifica se è un TourOperator o un Booker
			if (account.isTourOperator()) {
				// acquisisce il TourOperator
				TourOperator tourOperator = account.getTourOperator();
				// codifica i due oggetti DTO in un bean
				bean = BeanMapping.encode(account, tourOperator);
			} else if (account.isBooker()) {
				// acquisisce il booker
				Booker booker = account.getBooker();
				// codifica i due oggetti DTO in un bean
				bean = BeanMapping.encode(account, booker);
			}
			model.addAttribute("account", bean);
		} catch (AccountException e) {
			model.addAttribute("loginMessage", "L'account non esiste");
			model.addAttribute("signup", new Signup());
			return "index";
		}
		return "redirect:home";
	}

	@RequestMapping(value = "logout")
	public String logOut(HttpSession session, SessionStatus status, ModelMap model) {
		model.remove("account");
		status.setComplete();
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "signup", params = "newTo", method = RequestMethod.POST)
	public String signup(@Valid TourOperatorSignup signup, BindingResult result, Model model,
			HttpSession session) {
		// se ci sono errori nella validazione o la città non è stata selezionata
		if (result.hasErrors() || signup.getCity() == 0) {
			List<CityBean> cities = BeanMapping.encode(adminService.getCities());
			model.addAttribute("cities", cities);

			TourOperatorSignup bean = (TourOperatorSignup) session.getAttribute("signup");
			signup.setEmail(bean.getEmail());
			signup.setPassword(bean.getPassword());
			model.addAttribute("signup", signup);

			return "touroperator/signup";
		}
		// altrimenti recupera l'oggetto City in base all'id specificato nel bean
		// "signup"
		City city = adminService.getCityById(signup.getCity());
		// recupera un oggetto "TourOperator" mediante il bean "signup"
		TourOperator tourOperator = BeanMapping.getTourOperator(signup, city);
		// recupera l'oggetto account
		Account account = BeanMapping.getAccount((UserSignup) session.getAttribute("signup"));
		// rende l'account persistente
		userService.createAccount(account, tourOperator);
		// crea il bean da settare nella sessione (mediante il modello)
		TourOperatorBean accountBean = BeanMapping.encode(account, tourOperator);
		model.addAttribute("account", accountBean);
		return "redirect:home";
	}

	@RequestMapping(value = "signup", params = "newBo", method = RequestMethod.POST)
	public String signup(@Valid BookerSignup signup, BindingResult result, Model model,
			HttpSession session) {
		// se ci sono errori nella validazione
		if (result.hasErrors()) {
			// setta il bean per il form
			BookerSignup bean = (BookerSignup) session.getAttribute("signup");
			signup.setEmail(bean.getEmail());
			signup.setPassword(bean.getPassword());
			model.addAttribute("signup", signup);
			// setta le città
			List<CityBean> cities = BeanMapping.encode(adminService.getCities());
			model.addAttribute("cities", cities);
			// setta i campi data
			model.addAttribute("days", DateGenerator.getDays());
			model.addAttribute("months", DateGenerator.getMonths());
			model.addAttribute("years", DateGenerator.getYears());
			return "booker/signup";
		}
		Long birthPlaceId = signup.getBirthPlace();
		Long residenceCityId = signup.getCity();
		Booker booker = null;
		if (birthPlaceId == residenceCityId) {
			City city = adminService.getCityById(birthPlaceId);
			booker = BeanMapping.getBooker(signup, city, city);
		} else {
			City birthPlace = adminService.getCityById(birthPlaceId);
			City residenceCity = adminService.getCityById(residenceCityId);
			booker = BeanMapping.getBooker(signup, birthPlace, residenceCity);
		}
		// recupera l'oggetto account
		Account account = BeanMapping.getAccount((UserSignup) session.getAttribute("signup"));
		// rende l'account persistente
		userService.createAccount(account, booker);
		// crea il bean da settare nella sessione (mediante il modello)
		BookerBean accountBean = BeanMapping.encode(account, booker);
		model.addAttribute("account", accountBean);
		return "redirect:home";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String tryToSignup(@Valid Signup signup, BindingResult result, String user, Model model,
			HttpSession session) {
		Account account = userService.getAccountByEmail(signup.getSignupEmail());
		// se ci sono errori nella compilazione dei campi
		if (result.hasErrors()) {
			// setta il bean per il form login nella index
			model.addAttribute("login", new Login());
			// setta la lista dei pacchetti da visualizzare nella index
			List<HolidayPackageBean> list = BeanMapping.encode(userService.getHolidayPackages());
			model.addAttribute("holidayList", list);
			// restituisce il nome della pagina iniziale con errore
			return "index";
		}
		// se l'account esiste già
		if (account != null) {
			// setta il messaggio di errore
			model.addAttribute("signupMessage", "Scegli una mail diversa");
			// setta il bean per il form login nella index
			model.addAttribute("login", new Login());
			// setta la lista dei pacchetti da visualizzare nella index
			List<HolidayPackageBean> list = BeanMapping.encode(userService.getHolidayPackages());
			model.addAttribute("holidayList", list);
			// restituisce il nome della pagina iniziale con errore
			return "index";
		}
		if (user.equals("Booker")) {
			BookerSignup bean = new BookerSignup();
			bean.setEmail(signup.getSignupEmail());
			bean.setPassword(signup.getSignupPassword());
			model.addAttribute("signup", bean);
			session.setAttribute("signup", bean);
			// setta le città
			List<CityBean> cities = BeanMapping.encode(adminService.getCities());
			model.addAttribute("cities", cities);
			// setta i campi data
			model.addAttribute("days", DateGenerator.getDays());
			model.addAttribute("months", DateGenerator.getMonths());
			model.addAttribute("years", DateGenerator.getYears());
			return "booker/signup";
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

	@RequestMapping(value = "/showPackage", method = RequestMethod.GET)
	public String getPackages(@RequestParam(value = "type", required = true) String type,
			HttpSession session, Model model) {
		if (!isActiveSession(session)) {
			return "forward:/";
		}

		StringBuffer page = new StringBuffer("forward:");
		AccountBean account = (AccountBean) session.getAttribute("account");
		if (account.getType().equals(AccountType.TOUR_OPERATOR)) {
			page.append("to/packages");
		} else if (account.getType().equals(AccountType.BOOKER)) {
			page.append("bo/packages");
		} else {
			page.append("/");
		}

		return page.toString();
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody
	String jQuery(@ModelAttribute("id") String id, BindingResult result) {
		System.out.println("JQUERY controller");
		String res = new String();
		if (!result.hasErrors()) {
			res = "SUCCESS";
		} else {
			res = "FAILS";
		}
		return res;
	}

}