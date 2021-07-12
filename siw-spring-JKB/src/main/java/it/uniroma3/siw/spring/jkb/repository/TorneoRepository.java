package it.uniroma3.siw.spring.jkb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.jkb.model.Torneo;

public interface TorneoRepository  extends CrudRepository<Torneo,Long> {
	
	public List<Torneo> findByNomeLike(String nome);
	
	@Query("SELECT max(code) FROM Torneo")
	public Integer findTorneoUltimo();
	
	public Torneo findByCode(Integer code);
	
	@Modifying
	@Query("update Torneo t set t.nome=?1 where t.nome=?2")
	public void modificaNome(String nuovoNome,String vecchioNome);
	
	@Modifying
	@Query("update Torneo t set t.descrizione=?1 where t.nome=?2")
	public void modificaDescrizione(String descrizione,String nome);
}
