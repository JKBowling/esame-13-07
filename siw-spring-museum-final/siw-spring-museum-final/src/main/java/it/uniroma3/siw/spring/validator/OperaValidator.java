package it.uniroma3.siw.spring.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;

@Component
public class OperaValidator implements Validator {
	
	@Autowired
	private OperaService operaService;
	
	private final static Logger logger = LoggerFactory.getLogger(OperaValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return Opera.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Opera o = (Opera) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "required");
		
		if(!errors.hasErrors()) {
			logger.debug("valori validi");
			if(this.operaService.alreadyExists(o)) {
				logger.debug("opera duplicata");
				errors.reject("duplicato");
			}
		}
	}
}
