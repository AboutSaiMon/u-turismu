package uturismu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import uturismu.dto.HolidayPackage;
import uturismu.dto.Service;
import uturismu.service.TourOperatorService;

@RequestMapping("to")
public class TourOperatorController {

	@Autowired
	private TourOperatorService touroperatorService;

	@RequestMapping("/home")
	public String showHomePage() {
		return "home";
	}

	/*
	 * @RequestMapping("/home") public String showHomePage(@RequestParam("id")
	 * Long id,Model model){
	 * 
	 * System.out.println("TOUROPERATOR CONTROLLER");
	 * 
	 * try{ List<HolidayPackage>
	 * holiPack=touroperatorService.getAllHolidayPackages(id); List<Pack>
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

	private Double getPackPrize(HolidayPackage holidayPackage) {
		Double prize = 0D;
		Set<Service> set = holidayPackage.getServices();
		for (Service service : set) {
			prize += service.getPrice();
		}
		return prize;
	}

}
