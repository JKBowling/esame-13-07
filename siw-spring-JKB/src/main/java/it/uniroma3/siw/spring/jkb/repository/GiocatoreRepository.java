package it.uniroma3.siw.spring.jkb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.jkb.model.Giocatore;

public interface GiocatoreRepository extends CrudRepository<Giocatore,Long> {
	
	public List<Giocatore> findByCodiceSquadra(String codiceSquadra);

}
