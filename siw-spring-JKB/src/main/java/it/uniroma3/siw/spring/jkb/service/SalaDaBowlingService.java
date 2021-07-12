package it.uniroma3.siw.spring.jkb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.jkb.model.SalaDaBowling;
import it.uniroma3.siw.spring.jkb.repository.SalaDaBowlingRepository;

@Service
public class SalaDaBowlingService {
	
	@Autowired
	private SalaDaBowlingRepository salaDaBowlingRepository;
	
	@Transactional
	public void inserisci(SalaDaBowling sala){
		this.salaDaBowlingRepository.save(sala);
	}
	
	@Transactional
	public SalaDaBowling getSquadra(Long id) {
		
		Optional<SalaDaBowling> result = this.salaDaBowlingRepository.findById(id);
		return result.orElse(null);
		
	}
	
	@Transactional
	public SalaDaBowling saveTorneo(SalaDaBowling salaDaBowling) {
		return this.salaDaBowlingRepository.save(salaDaBowling);
	}
	
	@Transactional
	public List<SalaDaBowling> tutti(){
		return (List<SalaDaBowling>) this.salaDaBowlingRepository.findAll();
	}

}
