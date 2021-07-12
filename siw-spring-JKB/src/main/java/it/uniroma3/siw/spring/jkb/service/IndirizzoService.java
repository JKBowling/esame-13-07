package it.uniroma3.siw.spring.jkb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.jkb.model.Indirizzo;
import it.uniroma3.siw.spring.jkb.repository.IndirizzoRepository;

@Service
public class IndirizzoService {
	
	@Autowired
	private IndirizzoRepository indirizzoRepository;
	
	@Transactional
	public void inserisci(Indirizzo indirizzo) {
		this.indirizzoRepository.save(indirizzo);
	}
	
	@Transactional
	public Indirizzo getSquadra(Long id) {
		
		Optional<Indirizzo> result = this.indirizzoRepository.findById(id);
		return result.orElse(null);
		
	}
	
	@Transactional
	public Indirizzo saveTorneo(Indirizzo indirizzo) {
		return this.indirizzoRepository.save(indirizzo);
	}
	
	@Transactional
	public List<Indirizzo> tutti(){
		return (List<Indirizzo>) this.indirizzoRepository.findAll();
	}

}
