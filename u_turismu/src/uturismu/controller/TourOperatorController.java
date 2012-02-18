package uturismu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import uturismu.bean.AccountBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.bean.util.BeanMapping;
import uturismu.dto.HolidayPackage;
import uturismu.service.TourOperatorService;

@Controller
@RequestMapping("to")
@SessionAttributes("account")
public class TourOperatorController {

	@Autowired
	private TourOperatorService touroperatorService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String prova(HttpSession webSession, ModelMap model) {
		AccountBean account = (AccountBean) webSession.getAttribute("account");
		if (account == null) {
			return "redirect:/";
		}
		List<HolidayPackage> result = touroperatorService.getAllHolidayPackages(account.getUserId());
		List<HolidayPackageBean> packs = BeanMapping.encode(result);
		model.addAttribute("packs", packs);
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String showHomePage(HttpSession webSession, ModelMap model) {
		AccountBean account = (AccountBean) webSession.getAttribute("account");
		List<HolidayPackage> result = touroperatorService.getAllHolidayPackages(account.getUserId());
		List<HolidayPackageBean> packs = BeanMapping.encode(result);
		model.addAttribute("packs", packs);
		return "home";
	}

}
