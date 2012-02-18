package uturismu.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import uturismu.bean.AccountBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.dto.HolidayPackage;
import uturismu.dto.Service;
import uturismu.service.TourOperatorService;

@Controller
@RequestMapping("to/")
@SessionAttributes("account")
public class TourOperatorController {

	@Autowired
	private TourOperatorService touroperatorService;

	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String showHomePage(HttpSession webSession, ModelMap model) {
		AccountBean account = (AccountBean) webSession.getAttribute("account");
		System.out.println("###" + account.getEmail() + "###");
		List<HolidayPackageBean> beanPack = new LinkedList<HolidayPackageBean>();
		model.addAttribute("packs", beanPack);

		try {
			List<HolidayPackage> packs = touroperatorService
					.getAllHolidayPackages(account.getUserId());

			for (HolidayPackage holidayPackage : packs) {
				HolidayPackageBean pack = new HolidayPackageBean();
				pack.setId(holidayPackage.getId());
				pack.setName(holidayPackage.getName());
				pack.setDescription(holidayPackage.getDescription());
				pack.setPrice(getPackPrice(holidayPackage));
				beanPack.add(pack);
			}
		} catch (Exception e) {
			return "errorPage";
		}

		return "home";
	}

	private Double getPackPrice(HolidayPackage holidayPackage) {
		Double prize = 0D;
		Set<Service> set = holidayPackage.getServices();
		for (Service service : set) {
			prize += service.getPrice();
		}
		return prize;
	}

}
