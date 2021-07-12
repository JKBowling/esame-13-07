package it.uniroma3.siw.spring.jkb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.jkb.model.Squadra;

public interface SquadraRepository  extends CrudRepository<Squadra,Long>{

	public List<Squadra> findByNomeLike(String nome);
}
