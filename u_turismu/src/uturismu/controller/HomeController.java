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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uturismu.bean.Credential;
import uturismu.dto.Account;
import uturismu.dto.enumtype.AccountType;
import uturismu.exception.AccountException;
import uturismu.service.UserService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String showIndex(Model model) {
		model.addAttribute("credential", new Credential());
		return "index";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String login(Credential credential, Model model) {
		Account account = null;
		try {
			account = userService.logIn(credential.getEmail(), credential.getPassword());
		} catch (AccountException e) {
			return "errorPage";
		}
		//model.addAttribute("page", "booker/bookerContent.jsp");
		return "home";
		
	}

	private String onSubmit(@ModelAttribute("credential") Credential credential, Model model) {
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