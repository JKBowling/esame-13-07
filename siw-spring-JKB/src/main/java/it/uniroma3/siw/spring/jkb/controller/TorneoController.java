package it.uniroma3.siw.spring.jkb.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.jkb.model.Torneo;
import it.uniroma3.siw.spring.jkb.service.SalaDaBowlingService;
import it.uniroma3.siw.spring.jkb.service.TorneoService;
import it.uniroma3.siw.spring.jkb.validator.TorneoValidator;

@Controller
public class TorneoController {

	Integer cont=0;

	@Autowired
	private TorneoService torneoService;

	@Autowired
	private SalaDaBowlingService salaDaBowlingService;

	@Autowired
	private TorneoValidator torneoValidator;

	@RequestMapping(value ="/admin/addTorneo",method=RequestMethod.GET)
	public String addTorneo(Model model) {
		model.addAttribute("torneo",new Torneo());
		if(this.torneoService.torneoUltimo()!=null)
			model.addAttribute("lastCode",this.torneoService.torneoUltimo().toString());
		model.addAttribute("sale",this.salaDaBowlingService.tutti());
		return "admin/torneo/torneoForm.html";
	}

	@RequestMapping(value = "/torneo/{id}", method = RequestMethod.GET)
	public String getOpera(@PathVariable("id") Long id, Model model) {
		model.addAttribute("torneo", this.torneoService.getTorneo(id));
		return "torneo.html";
		
	}

	@RequestMapping(value= "/tornei" ,method=RequestMethod.GET)
	public String tornei(Model model) {
		model.addAttribute("tornei",this.torneoService.tutti());
		return "tornei.html";
	}

	

	@RequestMapping(value = "/admin/nuovoTorneo", method = RequestMethod.POST)
	public String newTorneo(@ModelAttribute("torneo") Torneo torneo/*,@RequestParam("sala")String sala*/,
			Model model, BindingResult bindingResult) {
		this.torneoValidator.validate(torneo, bindingResult);
//		sala.trim();
		
		if(!bindingResult.hasErrors()) {
//			torneo.setSala(this.salaDaBowlingService.salaPerNome(sala).get(0));
			this.torneoService.saveTorneo(torneo);
			model.addAttribute("tornei", this.torneoService.tutti());
			return "tornei.html";
		}
		return "admin/torneo/torneoForm.html";
	}  
	
}
