package uturismu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import uturismu.bean.AccountBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.bean.util.BeanMapping;
import uturismu.dto.HolidayPackage;
import uturismu.dto.enumtype.HolidayType;
import uturismu.dto.enumtype.Status;
import uturismu.service.TourOperatorService;
import static uturismu.controller.util.SessionCheck.isActiveSession;

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
		
		model.addAttribute("menu","defaultMenu.jsp");
		model.addAttribute("content", "content.jsp");
		model.addAttribute("packs", packs);
		
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String showHomePage(HttpSession webSession, ModelMap model) {
		AccountBean account = (AccountBean) webSession.getAttribute("account");
		List<HolidayPackage> result = touroperatorService.getAllHolidayPackages(account.getUserId());
		List<HolidayPackageBean> packs = BeanMapping.encode(result);
		model.addAttribute("menu","defaultMenu.jsp");
		model.addAttribute("content", "content.jsp");
		model.addAttribute("packs", packs);
		return "home";
	}
	
	@RequestMapping(value="/packages",method=RequestMethod.GET)
	public String showPackages(@RequestParam(value="type", required=true) String type,HttpSession session,Model model){
		
		if(!isActiveSession(session)){
			return "redirect:/";
		}
		System.out.println("cia co");
		
		AccountBean account=(AccountBean) session.getAttribute("account");
		List<HolidayPackage> result;
		
		if(type.equals("PUBLISHED")){
			result=touroperatorService.getPublishedHolidayPackages(account.getUserId());
		}else if(type.equals("DRAFT")){
			result=touroperatorService.getDraftHolidayPackages(account.getUserId());
		}else if(type.equals("EXPIRED")){
			result=touroperatorService.getExpiredHolidayPackages(account.getUserId());
		}else {
			result=touroperatorService.getAllHolidayPackages(account.getUserId());
		}
		
		List<HolidayPackageBean> packs=BeanMapping.encode(result);
		model.addAttribute("menu", "defaultMenu.jsp");
		model.addAttribute("content", "content.jsp");
		model.addAttribute("packs", packs);
		return "home";
	}

}
