package uturismu.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ContextLoader;

import uturismu.bean.AccountBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.dto.HolidayPackage;
import uturismu.dto.Service;
import uturismu.service.TourOperatorService;

@Controller
@RequestMapping("to/")
//@SessionAttributes("account")
public class TourOperatorController {

	@Autowired
	private TourOperatorService touroperatorService;

	@RequestMapping("home")
	public String showHomePage(HttpSession webSession,Model model ) {
		
		AccountBean account = (AccountBean) webSession.getAttribute("account");
		
		System.out.println("###"+account.getEmail()+"###");
		
		List<HolidayPackageBean> beanPack=new LinkedList<HolidayPackageBean>();
		model.addAttribute("packs", beanPack);
		
		try {
			List<HolidayPackage> packs=touroperatorService.getAllHolidayPackages(account.getUserId());
			
			for (HolidayPackage holidayPackage : packs) { 
				HolidayPackageBean pack=new HolidayPackageBean();
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

	/*
	 * @RequestMapping("/home") public String showHomePage(@RequestParam("id")
	 * Long id,Model model){
	 * 
	 * System.out.println("TOUROPERATOR CONTROLLER");
	 * 
	 * try{ List<HolidayPackage>
	 * holiPack=touroperatorService.getAllHolidayPackages(id); 
	 * List<Pack>
	 * packs=new LinkedList<Pack>();
	 * 
	 * for (HolidayPackage holidayPackage : holiPack) { Pack pack=new Pack();
	 * pack.setId(holidayPackage.getId());
	 * pack.setName(holidayPackage.getName());
	 * pack.setDescription(holidayPackage.getDescription());
	 * pack.setPrize(getPackPrize(holidayPackage)); packs.add(pack); }
	 * 
	 * for (Pack pack : packs) { System.out.println(pack); }
	 * 
	 * model.addAttribute("packs", packs); }catch (Exception e) {
	 * System.out.println("si � verificato un errore"); }
	 * 
	 * System.out.println("TourOperator Controller"); return "home"; }
	 */

	private Double getPackPrice(HolidayPackage holidayPackage) {
		Double prize = 0D;
		Set<Service> set = holidayPackage.getServices();
		for (Service service : set) {
			prize += service.getPrice();
		}
		return prize;
	}

}
