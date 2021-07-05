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

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.OperaService;
import it.uniroma3.siw.spring.validator.ArtistaValidator;

@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private ArtistaValidator artistaValidator;
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/admin/addArtista", method = RequestMethod.GET)
	public String addArtista(Model model) {
		
			logger.debug("addArtista");
			model.addAttribute("artista", new Artista());
			return "admin/artista/artistaForm.html";
			
	}
	
	@RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
	public String getArtista(@PathVariable("id") Long id, Model model) {
		Artista a = this.artistaService.artistaPerId(id);
		model.addAttribute("artista",a);
		model.addAttribute("opere",this.operaService.operaPerAutore(a));
		return "artista.html";
		
	}
	
	@RequestMapping(value = "/artisti", method = RequestMethod.GET)
	public String getArtisti(Model model) {
		
		model.addAttribute("artisti", this.artistaService.tutti());
		return "artisti.html";
		
	}
	
	@RequestMapping(value = "/admin/artisti", method = RequestMethod.GET)
	public String getArtistiAdmin(Model model) {
		
		model.addAttribute("artisti", this.artistaService.tutti());
		return "admin/artista/artistiTable.html";
		
	}
	
	@RequestMapping(value = "/admin/rimuoviArtista" , method = RequestMethod.GET)
	public String rimuoviArtista(Model model) {
		model.addAttribute("artisti", this.artistaService.tutti());
		return "admin/artista/rimuoviArtistaForm.html";
	}
	
	@RequestMapping(value = "/admin/cancellaArtista" , method = RequestMethod.POST)
	public String cancellaArtista(@RequestParam("artista")String artista, Model model) {

		artista.trim();
		String[] s = artista.split("\\s+");
		Boolean v = this.artistaService.eliminaArtista(s[0],s[1]);
		if(v) {
			model.addAttribute("artisti",this.artistaService.tutti());
			return "admin/artista/successRmvArtista.html";
		}
		return "admin/artista/rimuoviArtistaForm.html";
	}
	
	
	@RequestMapping(value = "/admin/artista", method = RequestMethod.POST)
	public String newArtista(@ModelAttribute("artista") Artista artista, Model model, BindingResult bindingResult) {
			
			this.artistaValidator.validate(artista, bindingResult);
			if(!bindingResult.hasErrors()) {
					this.artistaService.inserisci(artista);
					model.addAttribute("artisti", this.artistaService.tutti());
					return "admin/artista/successInsArtista.html";
			}
			
			return "admin/artista/artistaForm.html";
			
	}

}
