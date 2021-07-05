package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.User;

public interface UserRepository  extends CrudRepository<User, Long>{
	
	public List<User> findByNome(String nome);
	
	public List<User> findByCognome(String cognome);
	
	public List<User> findByNomeAndCognome(String nome, String cognome);

}
