package it.uniroma3.siw.spring.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

public interface OperaRepository extends CrudRepository<Opera, Long> {
	
	public List<Opera>  findByTitoloIgnoreCaseContaining(String name);
	
	public List<Opera> findByAutore(Artista autore);
	
	public List<Opera> findByCollezione(Collezione collezione);
	
	/* Metodi per le opere del mese */
	
	@Query("SELECT o FROM Opera o WHERE o.delMese ='true'")
	public Collection<Opera> findOpereDelMese();

	@Modifying
	@Query("update Opera o set o.delMese = ?1 where o.id = ?2")
	public void setOperaDelMese(Boolean value, Long operaId);
	
}
