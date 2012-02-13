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

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import uturismu.command.Credenzial;
import uturismu.dto.Account;
import uturismu.dto.enumtype.AccountType;
import uturismu.service.UserService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@RequestMapping({ "/", "home" })
	public String showHomePage(Model model) {
		Credenzial credenzial=new Credenzial();
		model.addAttribute("credenzial", credenzial);
//		model.addAttribute("packageList", userService.getHolidayPackages());
		return "home";
	}
	
	@RequestMapping(value="/holidayDetails")
	public String showHolidayPackageDetail(@RequestParam("holidayId") Long id, Model model) {
		return "holidayDetail";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String onSubmit(@ModelAttribute("credenzial") Credenzial credenzial,Model model ){
		System.out.println(credenzial.getEmail()+" : "+credenzial.getPassword());
		String redirectPage="";
		Account account=userService.logIn(credenzial.getEmail(), credenzial.getPassword());
		if(account.getType().equals(AccountType.TOUR_OPERATOR)){
			redirectPage="tourOperator/home";
		} else if(account.getType().equals(AccountType.BOOKER)){
			redirectPage="tourOperator/home";
		}
		model.addAttribute("loginFlag",true);
		model.addAttribute("account", account);
		return "homeAccount";
	}
	
}