package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User getUser(Long id) {
		
		Optional<User> result = this.userRepository.findById(id);
		return result.orElse(null);
		
	}
	
	@Transactional
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
	
	@Transactional
	public List<User> getAllUsers(){
		
		List<User> result = new ArrayList<>();
		Iterable<User> iterable = this.userRepository.findAll();
		
		for(User user : iterable)
			result.add(user);
		
		return result;
		
	}
	
	@Transactional
	public List<User> getUserByNome(String nome){
		return this.userRepository.findByNome(nome);
	}
	
	public List<User> getUserByCognome(String cognome){
		return this.userRepository.findByCognome(cognome);
	}
	
	public List<User> getUserByNomeAndCognome(String nome, String cognome){
		return this.userRepository.findByNomeAndCognome(nome, cognome);
	}

}
