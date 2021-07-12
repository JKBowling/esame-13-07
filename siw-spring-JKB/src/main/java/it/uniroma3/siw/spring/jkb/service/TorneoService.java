package it.uniroma3.siw.spring.jkb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.jkb.model.Torneo;
import it.uniroma3.siw.spring.jkb.repository.TorneoRepository;

@Service
public class TorneoService {

	@Autowired
	private TorneoRepository torneoRepository;
	
	@Transactional
	public Torneo getTorneo(Long id) {
		
		Optional<Torneo> result = this.torneoRepository.findById(id);
		return result.orElse(null);
		
	}
	
	@Transactional
	public Torneo saveTorneo(Torneo torneo) {
		List<Torneo> tornei = (List<Torneo>) this.torneoRepository.findAll();
		if(tornei.size()!=0) {
			if(this.torneoRepository.findByCode(torneoRepository.findTorneoUltimo()).getYes()!=null) {
				torneo.setYes(null);
			}
		}
		return this.torneoRepository.save(torneo);
	}
	
	@Transactional
	public List<Torneo> tutti(){
		return (List<Torneo>) this.torneoRepository.findAll();
	}
	
	@Transactional
	public Torneo torneoPerCodice(Integer code) {
		return this.torneoRepository.findByCode(code);
	}
	
	@Transactional
	public List<Torneo> torneoPerNome(String nome) {
		return this.torneoRepository.findByNomeLike(nome);
	}
	
	@Transactional
	public Integer torneoUltimo() {
		return this.torneoRepository.findTorneoUltimo();
	}
	
	@Transactional
	public boolean alreadyExists(Torneo torneo) {
		List<Torneo> tornei = this.torneoRepository.findByNomeLike(torneo.getNome());
		if (tornei.size() > 0)
			return true;
		else 
			return false;
	} 
	
	@Transactional
	public void cambiaNome(String nomeNuovo,String nomeVecchio) {
		this.torneoRepository.modificaNome(nomeNuovo,nomeVecchio);
	}
}
