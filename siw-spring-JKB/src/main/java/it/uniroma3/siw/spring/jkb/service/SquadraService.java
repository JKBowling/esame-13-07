package it.uniroma3.siw.spring.jkb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.jkb.model.Squadra;
import it.uniroma3.siw.spring.jkb.repository.SquadraRepository;

@Service
public class SquadraService {
	
	@Autowired
	private SquadraRepository squadraRepository;
	
	@Transactional
	public Squadra getSquadra(Long id) {
		
		Optional<Squadra> result = this.squadraRepository.findById(id);
		return result.orElse(null);
		
	}
	
	@Transactional
	public Squadra saveTorneo(Squadra squadra) {
		return this.squadraRepository.save(squadra);
	}
	
	@Transactional
	public List<Squadra> tutti(){
		return (List<Squadra>) this.squadraRepository.findAll();
	}
	
	@Transactional
	public void inserisci(Squadra squadra) {
		this.squadraRepository.save(squadra);
	}

}
