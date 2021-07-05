package it.uniroma3.siw.spring.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.User;

@Component
public class UserValidator implements Validator {

	final Integer MAX_NAME_LENGHT = 100;
	final Integer MIN_NAME_LENGHT = 2;
	
	private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		
		User user = (User) obj;
		String nome = user.getNome().trim();
		String cognome = user.getCognome().trim();
		
		if(nome.length() < MIN_NAME_LENGHT || nome.length() > MAX_NAME_LENGHT )
			errors.rejectValue("nome", "size");
		
		if(cognome.length() < MIN_NAME_LENGHT || nome.length() > MAX_NAME_LENGHT )
			errors.rejectValue("cognome", "size");
		
		if(!errors.hasErrors())
			logger.debug("valori validi");
	
	}
	
}
