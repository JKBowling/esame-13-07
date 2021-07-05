package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.repository.CollezioneRepository;

@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Transactional
	public Collezione inserisci(Collezione collezione) {
		return collezioneRepository.save(collezione);
	}
	
	@Transactional
	public List<Collezione> tutti() {
		return (List<Collezione>) collezioneRepository.findAll();
	}
	
	@Transactional
	public Collezione collezionePerId(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Collezione> collezionePerNome(String nome) {
		return collezioneRepository.findByNomeIgnoreCaseContaining(nome);
	}
	
	@Transactional
	public List<Collezione> collezionePerCodice(String codice){
		return collezioneRepository.findByCode(codice);
	}
	
	@Transactional
	public boolean alreadyExists(Collezione collezione) {
		List<Collezione> collezioni = this.collezioneRepository.findByNomeIgnoreCaseContaining(collezione.getNome());
		if (collezioni.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public Boolean rimuoviCollezione(String nome) {
		List<Collezione> collezioneRemove = this.collezioneRepository.findByNomeIgnoreCaseContaining(nome);
		
			if(collezioneRemove.size()!=0) {
				
				this.collezioneRepository.deleteAll(collezioneRemove);
				return true;
				
			}
			
		return false;
		
	}
	
}
