package it.uniroma3.siw.spring.controller;

import java.util.List;
 
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

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;
import it.uniroma3.siw.spring.validator.OperaValidator;

@Controller
public class OperaController {
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private OperaValidator operaValidator;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/admin/addOpera", method = RequestMethod.GET)
	public String addOpera(Model model) {
		
		logger.debug("addOpera");
		model.addAttribute("opera", new Opera());
		model.addAttribute("artisti", this.artistaService.tutti());
		model.addAttribute("collezioni", this.collezioneService.tutti());
		return "admin/opera/operaForm.html";
		
	}
	
	@RequestMapping(value = "/opera/{id}", method = RequestMethod.GET)
	public String getOpera(@PathVariable("id") Long id, Model model) {
		model.addAttribute("opera", this.operaService.operaPerId(id));
		return "opera.html";
		
	}
	
	@RequestMapping(value = "/opere", method = RequestMethod.GET)
	public String getOpere(Model model) {

		model.addAttribute("opere", this.operaService.tutti());
		return "opere.html";
		
	}
	
	@RequestMapping(value = "/admin/opere", method = RequestMethod.GET)
	public String getOpereAdmin(Model model) {

		model.addAttribute("opere", this.operaService.tutti());
		return "admin/opera/opereTable.html";
		
	}
	
	@RequestMapping(value = "/admin/rimuoviOpera" , method = RequestMethod.GET)
	public String rimuoviOpera(Model model) {
		return "admin/opera/rimuoviOperaForm.html";
	}
	
	@RequestMapping(value = "/admin/cancellaOpera" , method = RequestMethod.POST)
	public String cancellaOpera(@RequestParam("opera")String cerca, Model model) {
		Boolean v = this.operaService.eliminaOpera(cerca);
		if(v) {
			model.addAttribute("opere",this.operaService.tutti());
			return "/admin/opera/successRmvOpera.html";
		}
	 return "admin/opera/rimuoviOperaForm.html";
	}
	
	
	@RequestMapping(value = "/admin/opera", method = RequestMethod.POST)
	public String newOpera(@ModelAttribute("opera") Opera opera,@RequestParam("artista")String artista,
			@RequestParam(value = "collection", required = false)String collezione,Model model, BindingResult bindingResult) {
		this.operaValidator.validate(opera, bindingResult);
		artista.trim();
		String[] s = artista.split("\\s+");
		
		if(!bindingResult.hasErrors()) {
			
			opera.setAutore(this.artistaService.artistaPerNomeAndCognome(s[0], s[1]).get(0));
			opera.setCollezione(this.collezioneService.collezionePerNome(collezione).get(0));
			
			this.operaService.inserisci(opera);
			model.addAttribute("opere", this.operaService.tutti());
			return "admin/opera/successInsOpera.html";
		}
		return "admin/opera/operaForm.html";
	}
	
	/* Metodi per le opere del mese */
	
	@RequestMapping(value = "/admin/operaMeseAdd", method = RequestMethod.GET)
	public String opereDelMese(Model model) {
		return "admin/opera/opereDelMeseForm.html";
	}
	
	@RequestMapping(value = "/admin/operaDelMese", method = RequestMethod.GET)
	public String setOperaDelMese(@RequestParam("opera") String opera,Model model) {
		
		List<Opera> operaTrovata = this.operaService.operaPerTitolo(opera);
		
		if(operaTrovata.size()!=0) {
			
			this.operaService.updateOperaDelMese(operaTrovata.get(0),true);
			model.addAttribute("opere",this.operaService.opereDelMese());
			return "admin/opera/successInsMese.html";
			
		}
		
		return "admin/opera/opereDelMeseForm.html";
		
	}
	
	@RequestMapping(value = "/admin/operaMeseRemove", method = RequestMethod.GET)
	public String rimuoviOperaDelMese(Model model) {
		return "admin/opera/rimuoviOperaDelMeseForm.html";
	}
	
	@RequestMapping(value = "/admin/cancellaOperaDelMese", method = RequestMethod.GET)
	public String cancellaOperaDelMese(@RequestParam("opera") String opera,Model model) {
		
		List<Opera> operaTrovata = this.operaService.operaPerTitolo(opera);
		
		if(operaTrovata.size()!=0) {
			
			this.operaService.updateOperaDelMese(operaTrovata.get(0),false);
			model.addAttribute("opere",this.operaService.opereDelMese());
			return "admin/opera/successRmvMese.html";
			
		}
		return "admin/opera/rimuoviOperaDelMeseForm.html";
	}
	
}
