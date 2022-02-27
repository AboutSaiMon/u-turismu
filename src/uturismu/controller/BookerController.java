package uturismu.controller;

import static uturismu.controller.util.SessionCheck.isBooker;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import uturismu.bean.AccountBean;
import uturismu.bean.BookerBean;
import uturismu.bean.BookerUpdate;
import uturismu.bean.CityBean;
import uturismu.bean.HolidayPackageSummaryBean;
import uturismu.bean.util.BeanMapping;
import uturismu.bean.util.DateGenerator;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.City;
import uturismu.dto.HolidayPackage;
import uturismu.service.AdministratorService;
import uturismu.service.BookerService;
import uturismu.service.UserService;

@Controller
@SessionAttributes("account")
public class BookerController {

	@Autowired
	private UserService userService;
	@Autowired
	private AdministratorService adminService;
	@Autowired
	private BookerService bookerService;

	@RequestMapping(value = "book", method = RequestMethod.POST)
	public String book(@RequestParam("id") String id, HttpSession session, Model model) {
		if (isBooker(session)) {
			AccountBean account = (AccountBean) session.getAttribute("account");
			Booker booker = userService.getBookerById(account.getUserId());
			HolidayPackage holiday = userService.getHolidayPackageByID(Long.valueOf(id));
			bookerService.book(booker, holiday);
		}
		return "redirect:home";
	}

	@RequestMapping(value = "/updateBo", method = RequestMethod.GET)
	public String prepareUpdate(HttpSession session, Model model) {
		if (!isBooker(session)) {
			return "redirect:/";
		}
		BookerBean beanAccount = (BookerBean) session.getAttribute("account");

		Account account = userService.getAccountByEmail(beanAccount.getEmail());
		Booker booker = account.getBooker();
		BookerUpdate bean = BeanMapping.encodeBookerUpdate(account, booker);
		model.addAttribute("updateData", bean);
		List<CityBean> cities = BeanMapping.encode(adminService.getCities());
		model.addAttribute("cities", cities);
		model.addAttribute("days", DateGenerator.getDays());
		model.addAttribute("months", DateGenerator.getMonths());
		model.addAttribute("years", DateGenerator.getYears());
		model.addAttribute("image", "resources/img/testata2.gif");
		return "booker/updateAccount";
	}

	@RequestMapping(value = "/updateBo", params = "doUpdate", method = RequestMethod.POST)
	public String doUpdate(@Valid BookerUpdate updateData, BindingResult result,
			HttpSession session, Model model) {

		if (!isBooker(session)) {
			return "redirect:/";
		}
		BookerBean beanAccount = (BookerBean) session.getAttribute("account");
		boolean passwordErr = false;
		String pwd = updateData.getPassword();
		if (!pwd.isEmpty()) {
			if (pwd.length() < 3 || pwd.length() > 15) {
				passwordErr = true;
			}
		}
		if (result.hasErrors() || passwordErr) {
			if (passwordErr) {
				result.addError(new ObjectError("password", " campo password vuoto "));
			}
			updateData.setEmail(beanAccount.getEmail());
			List<CityBean> cities = BeanMapping.encode(adminService.getCities());
			model.addAttribute("cities", cities);
			model.addAttribute("days", DateGenerator.getDays());
			model.addAttribute("months", DateGenerator.getMonths());
			model.addAttribute("years", DateGenerator.getYears());
			model.addAttribute("updateData", updateData);
			model.addAttribute("image", "resources/img/testata2.gif");

			System.out.println("ABBIAMO ERRORI ");
			return "booker/updateAccount";
		}
		Long birhId = updateData.getBirthPlace();
		Long residenceId = updateData.getCity();
		City birthPlace = adminService.getCityById(birhId);
		City residenceCity = adminService.getCityById(residenceId);
		Account account = userService.getAccountByEmail(beanAccount.getEmail());

		Booker bookerTMP = account.getBooker();
		Booker booker = BeanMapping.getBooker(updateData, birthPlace, residenceCity, bookerTMP);
		if (!updateData.getPassword().isEmpty()) {
			account.setPassword(updateData.getPassword());
		}

		try {
			System.out.println("PREPARO IL SALVATAGGIO ");
			userService.update(account, booker);
			System.out.println("conludo IL SALVATAGGIO ");
		} catch (DataIntegrityViolationException e) {
			updateData.setEmail(beanAccount.getEmail());
			List<CityBean> cities = BeanMapping.encode(adminService.getCities());
			model.addAttribute("cities", cities);
			model.addAttribute("days", DateGenerator.getDays());
			model.addAttribute("months", DateGenerator.getMonths());
			model.addAttribute("years", DateGenerator.getYears());
			model.addAttribute("errMessage", "Tax Code esistente");
			model.addAttribute("updateData", updateData);
			System.out.println("ABBIAMO ERRORI ");
			model.addAttribute("image", "resources/img/testata2.gif");
			return "booker/updateAccount";
		}
		System.out.println("ricarico i dati ");
		beanAccount = BeanMapping.encode(account, booker);
		model.addAttribute("account", beanAccount);
		return "redirect:/";
	}

	
	@RequestMapping(value="bo/packages",method=RequestMethod.GET) 
	public String showPackages(@RequestParam(value = "type", required = true) String type,HttpSession session, Model model) {
		if(!isBooker(session)){
			return "redirect:/";
		}
		BookerBean bean=(BookerBean)session.getAttribute("account");
		List<HolidayPackage> result=new LinkedList<HolidayPackage>();
		
		if(type.equals("ALL")){
			result=userService.getHolidayPackages();
		}else if(type.equals("BOOKED")){
			result=bookerService.getBookedHolidayPackage(bean.getUserId());
		}else if(type.equals("EXP_BOOKED")){
			
		}else{
			result=userService.getHolidayPackages();
		}
		
		List<HolidayPackageSummaryBean> holidayList=BeanMapping.encode(result);
		model.addAttribute("holidayList", holidayList);
		model.addAttribute("image", "resources/img/testata2.gif");
		model.addAttribute("menu", "defaultMenu.jsp");
		model.addAttribute("content", "content.jsp");
		return "home";
	}
}
