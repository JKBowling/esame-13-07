package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;
import it.uniroma3.siw.spring.validator.CollezioneValidator;

@Controller
public class CollezioneController {
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private CuratoreService curatoreService;
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private CollezioneValidator collezioneValidator;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/admin/addCollezione", method = RequestMethod.GET)
	public String addCollezione(Model model) {
		
		logger.debug("addCollezione");
		model.addAttribute("collezione", new Collezione());
		model.addAttribute("curatori", this.curatoreService.tutti());
		return "admin/collezione/collezioneForm.html";
		
	}
	
	@RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
	public String getCollezione(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
		model.addAttribute("opereColl", this.operaService.operaPerCollezione((Collezione)this.collezioneService.collezionePerId(id)));
		return "collezione.html";
		
	}
	
	@RequestMapping(value = "/collezioni", method = RequestMethod.GET)
	public String getCollezioni(Model model) {
		model.addAttribute("collezioni",this.collezioneService.tutti());
		return "collezioni.html";
		
	}
	
	@RequestMapping(value = "/admin/collezioni", method = RequestMethod.GET)
	public String getCollezioniAdmin(Model model) {
		model.addAttribute("collezioni", this.collezioneService.tutti());
		return "admin/collezione/collezioniTable.html";
		
	}
	
	@RequestMapping(value = "/admin/rimuoviCollezione" , method = RequestMethod.GET)
	public String rimuoviCollezione (Model model) {
		
		model.addAttribute("collezioni", this.collezioneService.tutti());
		return "admin/collezione/rimuoviCollezioneForm.html";
		
	}
	
	@RequestMapping(value = "/admin/rimuoviCollezione" , method = RequestMethod.POST)
	public String rimuoviCollezione(@RequestParam("collezione")String collezione, Model model) {
		
		Boolean v = this.collezioneService.rimuoviCollezione(collezione);
		
		if(v) {
			model.addAttribute("collezioni", this.collezioneService.tutti());
			return "admin/collezione/successRmvCollezione.html";
		}
		return "admin/collezione/rimuoviCollezioneForm.html";
	}
	
	@RequestMapping(value = "/admin/collezione", method = RequestMethod.POST)
	public String newCollezione(@ModelAttribute("collezione") Collezione collezione, @RequestParam("curator") String curatore,
									Model model, BindingResult bindingResult) {
		
		this.collezioneValidator.validate(collezione, bindingResult);
		curatore.trim();
		String[] s = curatore.split("\\s+");
		
		if(!bindingResult.hasErrors()){
			
			collezione.setCuratore(this.curatoreService.curatorePerMatricola(s[0]).get(0));
			this.collezioneService.inserisci(collezione);
			model.addAttribute("collezioni", this.collezioneService.tutti());
			return "admin/collezione/successInsCollezione.html";
			
		}

		return "admin/collezione/collezioneForm.html";
		
	}

	@RequestMapping(value = "/admin/aggiornaCuratoreCerca", method = RequestMethod.GET)
	public String aggiornaCollezione(Model model) {
		model.addAttribute("collezioni", this.collezioneService.tutti());
		return "admin/collezione/aggiornaCuratoreCerca.html";
	}
	
	@RequestMapping(value = "/admin/aggiornaCuratore", method = RequestMethod.GET)
	public String aggiornaCollezione(@RequestParam("collection") String collezione, Model model) {
		
		model.addAttribute("collezioneMod", this.collezioneService.collezionePerNome(collezione));
		model.addAttribute("curatori", this.curatoreService.tutti());
		return "admin/collezione/aggiornaCuratore.html";
		
	}
	
	@RequestMapping(value = "/admin/aggiornaCuratore", method = RequestMethod.POST)
	public String aggiornaCollezione(@ModelAttribute("collezioneMod") String collezione, @RequestParam("curatore") String curatore, Model model) {
		
		curatore.trim();
		String[] s = curatore.split("\\s+");
		
		this.collezioneService.collezionePerNome(collezione).get(0).setCuratore(this.curatoreService.curatorePerMatricola(s[0]).get(0));
		
		model.addAttribute("collezioni", this.collezioneService.tutti());
		
		return "admin/collezione/collezioniTable.html";
		
	}
	
}
