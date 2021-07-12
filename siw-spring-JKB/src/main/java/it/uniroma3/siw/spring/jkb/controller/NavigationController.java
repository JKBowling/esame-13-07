package it.uniroma3.siw.spring.jkb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.siw.spring.jkb.service.SalaDaBowlingService;

@Controller
public class NavigationController {
	
	@Autowired
	private SalaDaBowlingService salaDaBowlingService;
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		return "index.html";
	}
	
	@RequestMapping(value = "/informazioni", method = RequestMethod.GET)
	public String informazioni(Model model) {
		return "informazioni.html";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		return "admin/adminHome.html";
	}

	@RequestMapping(value = "/salePartner", method = RequestMethod.GET)
	public String salePartner(Model model) {
		
		model.addAttribute("saleDaBowling", this.salaDaBowlingService.tutti());
		return "salePartner.html";
		
	}
	
	@RequestMapping(value = "/HoF", method = RequestMethod.GET)
		public String hallOfFame(Model model) {
			return "HoF.html";
		}
	
}
