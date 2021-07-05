package it.uniroma3.siw.spring.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.ArtistaRepository;


@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Transactional
	public Artista inserisci(Artista artista) {
		return artistaRepository.save(artista);
	}
	
	@Transactional
	public List<Artista> tutti() {
		return (List<Artista>)artistaRepository.findAll();
	}
	
	@Transactional
	public Artista artistaPerId(Long id) {
		Optional<Artista> optional = artistaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Artista> artistaPerNomeAndCognome(String nome, String cognome) {
		return artistaRepository.findByNomeAndCognomeIgnoreCaseContaining(nome, cognome);
	}
	
	@Transactional
	public List<Artista> artistaPerCognome(String cognome) {
		return artistaRepository.findByCognomeIgnoreCaseContaining(cognome);
	}
	
	@Transactional
	public List<Artista> artistaPerNazione(String nazione){
		return artistaRepository.findByNazione(nazione);
	}
	
	@Transactional
	public boolean alreadyExists(Artista artista) {
		List<Artista> artisti = this.artistaRepository.findByNomeAndCognomeIgnoreCaseContaining(artista.getNome(), artista.getCognome());
		if (artisti.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public Boolean eliminaArtista(String nome,String cognome) {
		List<Artista> artistaElimina = this.artistaRepository.findByNomeAndCognomeIgnoreCaseContaining(nome, cognome);
			if(artistaElimina.size()!=0) {
				this.artistaRepository.deleteAll(artistaElimina);
				return true;
			}
		return false;
	}
	
}
