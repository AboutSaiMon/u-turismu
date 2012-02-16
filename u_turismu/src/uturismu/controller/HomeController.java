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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import uturismu.bean.EmailPasswordBean;
import uturismu.bean.UTurismuBean;
import uturismu.bean.util.BeanMapping;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;
import uturismu.exception.AccountException;
import uturismu.service.UserService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Controller
@SessionAttributes("account")
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String showIndex(Model model) {
		model.addAttribute("credential", new EmailPasswordBean());
		model.addAttribute("signup", new EmailPasswordBean());
		return "index";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String login(@Valid EmailPasswordBean credential, BindingResult result, Model model) {
		// se ci sono errori nella compilazione dei campi
		if (result.hasErrors()) {
			// si ritorna alla pagina iniziale con errore
			return "index";
		}
		try {
			// effettua il login
			Account account = userService.logIn(credential.getEmail(), credential.getPassword());
			// dichiara il bean che verrà passato nella sessione
			UTurismuBean bean = null;
			// se è un tour operator
			if (account.getType().equals(AccountType.TOUR_OPERATOR)) {
				// acquisisce il tour operator mediante il suo id
				TourOperator tourOperator = userService.getTourOperatorById(account.getTourOperator().getId());
				// codifica i due oggetti DTO in un bean
				bean = BeanMapping.encode(account, tourOperator);
			} else if (account.getType().equals(AccountType.BOOKER)) {
				// acquisisce il booker mediante il suo id
				Booker booker = userService.getBookerById(account.getBooker().getId());
				// codifica i due oggetti DTO in un bean
				bean = BeanMapping.encode(account, booker);
			}
			model.addAttribute("account", bean);
		} catch (AccountException e) {
			model.addAttribute("message", e.getMessage());
			return "errorPage";
		}
		return "redirect:";
	}

	@RequestMapping(value = "/home", params = "new", method = RequestMethod.POST)
	public String signup(@Valid EmailPasswordBean signup, BindingResult result) {
		return "index";
	}

	private String onSubmit(@ModelAttribute("credential") EmailPasswordBean credential, Model model) {
		System.out.println("######### ciao ###########");
		// System.out.println("---------"+ test + "---------" );
		System.out.println(credential.getEmail() + " : " + credential.getPassword());

		String redirectPage = "redirect:";
		redirectPage = "";
		try {
			Account account = userService.logIn(credential.getEmail(), credential.getPassword());
			System.out.println(account.getEmail() + " " + account.getSalt() + " "
					+ account.getLastAccessTimestamp());
			if (account.getType().equals(AccountType.TOUR_OPERATOR)) {
				redirectPage += "tourOperator/home";
			} else if (account.getType().equals(AccountType.BOOKER)) {
				redirectPage += "booker/home";
			}
			model.addAttribute("account", account);
		} catch (Exception e) {
			redirectPage = "errorPage";
		}
		return redirectPage;

	}

}