package uturismu.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import uturismu.bean.AccountBean;
import uturismu.bean.CityBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.bean.TourOperatorSignup;
import uturismu.bean.TourOperatorUpdate;
import uturismu.bean.util.BeanMapping;
import uturismu.controller.util.SessionCheck;
import uturismu.dto.Account;
import uturismu.dto.City;
import uturismu.dto.HolidayPackage;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.HolidayType;
import uturismu.dto.enumtype.Status;
import uturismu.service.AdministratorService;
import uturismu.service.TourOperatorService;
import uturismu.service.UserService;
import static uturismu.controller.util.SessionCheck.isTourOperator;

@Controller
//@RequestMapping("to")
@SessionAttributes("account")
public class TourOperatorController {

	@Autowired
	private TourOperatorService touroperatorService;
	@Autowired
	private UserService userService;
	@Autowired
	private AdministratorService adminservice;
	
	@RequestMapping(value = "to/home", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "to/home", method = RequestMethod.POST)
	public String showHomePage(HttpSession session, ModelMap model) {
		if(!isTourOperator(session)){
			return "redirect:/";
		}
		AccountBean account = (AccountBean) session.getAttribute("account");
		List<HolidayPackage> result = touroperatorService.getAllHolidayPackages(account.getUserId());
		List<HolidayPackageBean> packs = BeanMapping.encode(result);
		model.addAttribute("menu","defaultMenu.jsp");
		model.addAttribute("content", "content.jsp");
		model.addAttribute("packs", packs);
		return "home";
	}
	
	@RequestMapping(value="to/packages",method=RequestMethod.GET)
	public String showPackages(@RequestParam(value="type", required=true) String type,HttpSession session,Model model){
		
		if(!isTourOperator(session)){
			return "redirect:/";
		}
		
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
	
	@RequestMapping(value="/updateTo",method=RequestMethod.GET)
	public String prepareUpdate(HttpSession session,Model model){
		if(!SessionCheck.isTourOperator(session) ){
			return "redirect:/";
		}
		StringBuffer page=new StringBuffer("touroperator/updateAccount");
		AccountBean accountBean= (AccountBean) session.getAttribute("account");
		TourOperator tourOperator=userService.getTourOperatorById(accountBean.getUserId());
		Account account = tourOperator.getAccount();
		TourOperatorUpdate beanTouroperator= BeanMapping.encodeTourOperatorUpdate(account, tourOperator);		
		List<CityBean> cities=(BeanMapping.encode(adminservice.getCities()));
		model.addAttribute("update", beanTouroperator);
		model.addAttribute("cities", cities);
		return page.toString();
	}
	
	@RequestMapping(value="/updateTo", params="doUpdate",method=RequestMethod.POST )
	public  String  doUpdate(@Valid TourOperatorSignup update, BindingResult result,HttpSession session) {
		if(result.hasErrors()){
			return "updateAccount";
		}
		System.out.println("ciao ");
		//TODO: implementare la chiamata al service per l'update;
		return "redirect:/home";
	}

}
