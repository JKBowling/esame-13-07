package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepository;
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}
	
	@Transactional
	public List<Opera> tutti() {
		return (List<Opera>) operaRepository.findAll();
	}
	
	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public Boolean eliminaOpera(String titolo) {
		List<Opera> operaElimina = this.operaRepository.findByTitoloIgnoreCaseContaining(titolo);
		if(operaElimina.size()!=0) {
			this.operaRepository.deleteAll(operaElimina);
			return true;
		}
		return false;
	}
	
	@Transactional
	public List<Opera> operaPerTitolo(String titolo) {
		return operaRepository.findByTitoloIgnoreCaseContaining(titolo);
	}
	
	@Transactional
	public List<Opera> operaPerAutore(Artista autore){
		return operaRepository.findByAutore(autore);
	}
	
	@Transactional
	public List<Opera> operaPerCollezione(Collezione collezione){
		return operaRepository.findByCollezione(collezione);
	}
	
	
	@Transactional
	public boolean alreadyExists(Opera opera) {
		List<Opera> opere = this.operaRepository.findByTitoloIgnoreCaseContaining(opera.getTitolo());
		if (opere.size() > 0)
			return true;
		else 
			return false;
	}
	

	
	/* Metodi per le opere del mese */
	
	@Transactional
	public List<Opera> opereDelMese(){
		return (List<Opera>) this.operaRepository.findOpereDelMese();
	}
	
	@Transactional
	public void updateOperaDelMese(Opera opera, Boolean value) {
		this.operaRepository.setOperaDelMese(value,opera.getId());
	}

}
