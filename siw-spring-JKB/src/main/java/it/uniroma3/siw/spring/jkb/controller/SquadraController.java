package it.uniroma3.siw.spring.jkb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.jkb.model.Credentials;
import it.uniroma3.siw.spring.jkb.model.Giocatore;
import it.uniroma3.siw.spring.jkb.model.Squadra;
import it.uniroma3.siw.spring.jkb.service.CredentialsService;
import it.uniroma3.siw.spring.jkb.service.GiocatoreService;
import it.uniroma3.siw.spring.jkb.service.SquadraService;

@Controller
public class SquadraController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private SquadraService squadraService;
	
	@Autowired
	private GiocatoreService giocatoreService;
	
	@RequestMapping(value="/creaSquadra", method=RequestMethod.GET)
	public String creaSquadra(Model model){
		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		
		Giocatore capitano = credentials.getGiocatore();
		
		if(capitano.getSquadra() != null)
			return "error.html";
		
		model.addAttribute("squadra", new Squadra());
		model.addAttribute("capitano", capitano);
		
		return "/giocatore/creaSquadra.html";
	}
	
	@RequestMapping(value="/verificaSquadra", method=RequestMethod.POST)
	public String verificaSquadra(@ModelAttribute("squadra") Squadra squadra,@RequestParam("capitano") String capitano,
									@RequestParam("giocatore2") String giocatore2, @RequestParam("giocatore3") String giocatore3,
									@RequestParam("giocatore4") String giocatore4, Model model) {
		
		if(this.giocatoreService.reclutaGiocatore(giocatore2).isEmpty() || this.giocatoreService.reclutaGiocatore(giocatore2).isEmpty()
				|| this.giocatoreService.reclutaGiocatore(giocatore3).isEmpty())
			return "";
		
		Giocatore c = this.giocatoreService.reclutaGiocatore(capitano).get(0);
		Giocatore g2 = this.giocatoreService.reclutaGiocatore(giocatore2).get(0);
		Giocatore g3 = this.giocatoreService.reclutaGiocatore(giocatore3).get(0);
		Giocatore g4 = this.giocatoreService.reclutaGiocatore(giocatore4).get(0);
		
		model.addAttribute("squadra", squadra);
		model.addAttribute("capitano", c);
		model.addAttribute("giocatore2", g2);
		model.addAttribute("giocatore3", g3);
		model.addAttribute("giocatore4", g4);
		
		return "/giocatore/verificaSquadra.html";
		
	}
	
	 @RequestMapping(value="/controlloSquadra", method=RequestMethod.POST)
	 public String indietroSquadra(@ModelAttribute("squadra") Squadra squadra,@RequestParam("capitano") String capitano,
									@RequestParam("giocatore2") String giocatore2, @RequestParam("giocatore3") String giocatore3,
									@RequestParam("giocatore4") String giocatore4, 
									@RequestParam(value = "avanti", required = false) String avanti,
									@RequestParam(value = "indietro", required = false) String indietro,
									Model model) {
		
		if(avanti != null && indietro == null) {
			
			List<Giocatore> team = new ArrayList<>();
			team.add(this.giocatoreService.reclutaGiocatore(capitano).get(0));
			team.add(this.giocatoreService.reclutaGiocatore(giocatore2).get(0));
			team.add(this.giocatoreService.reclutaGiocatore(giocatore3).get(0));
			team.add(this.giocatoreService.reclutaGiocatore(giocatore4).get(0));
			
			squadra.setGiocatori(team);
			squadra.setTorneiVinti(0);
			
			for(Giocatore giocatore : team) {
				giocatore.setSquadra(squadra);
				giocatore.setIsCapitano(false);
			}
			
			this.giocatoreService.reclutaGiocatore(capitano).get(0).setIsCapitano(true);
			
			this.squadraService.inserisci(squadra);
			
			return "/giocatore/confermaSquadra.html";
			
		}

		Giocatore c = this.giocatoreService.reclutaGiocatore(capitano).get(0);
		Giocatore g2 = this.giocatoreService.reclutaGiocatore(giocatore2).get(0);
		Giocatore g3 = this.giocatoreService.reclutaGiocatore(giocatore3).get(0);
		Giocatore g4 = this.giocatoreService.reclutaGiocatore(giocatore4).get(0); 
		 
		model.addAttribute("squadra", squadra);
		model.addAttribute("capitano", c);
		model.addAttribute("giocatore2", g2);
		model.addAttribute("giocatore3", g3);
		model.addAttribute("giocatore4", g4);
		
		return "/giocatore/creaSquadra.html";
		 
	 }
	

}
