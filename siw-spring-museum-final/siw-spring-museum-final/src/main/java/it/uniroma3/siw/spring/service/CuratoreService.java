package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.repository.CuratoreRepository;

@Service
public class CuratoreService {
	
	@Autowired
	private CuratoreRepository curatoreRepository;;
	
	@Transactional
	public Curatore inserisci(Curatore curatore) {
		return curatoreRepository.save(curatore);
	}
	
	@Transactional
	public List<Curatore> tutti() {
		return (List<Curatore>) curatoreRepository.findAll();
	}
	
	@Transactional
	public Curatore curatorePerId(Long id) {
		Optional<Curatore> optional = curatoreRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Curatore> curatorePerNomeAndCognome(String nome, String cognome) {
		return curatoreRepository.findByNomeAndCognomeIgnoreCaseContaining(nome, cognome);
	}
	
	@Transactional
	public List<Curatore> curatorePerMatricola(String matricola){
		return curatoreRepository.findByMatricola(matricola);
	}
	
	@Transactional
	public boolean alreadyExists(Curatore curatore) {
		List<Curatore> curatori = this.curatoreRepository.findByMatricola(curatore.getMatricola());
		if (curatori.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public Boolean rimuoviCuratore(String nome, String cognome) {
		List<Curatore> curatoreRemove = this.curatoreRepository.findByNomeAndCognomeIgnoreCaseContaining(nome, cognome);
		
			if(curatoreRemove.size()!=0) {
				
				this.curatoreRepository.deleteAll(curatoreRemove);
				return true;
				
			}
			
		return false;
		
	}

}
