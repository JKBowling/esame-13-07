package it.uniroma3.siw.spring.jkb.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.jkb.model.Torneo;
import it.uniroma3.siw.spring.jkb.service.TorneoService;

@Component
public class TorneoValidator implements Validator {
	
	@Autowired
	private TorneoService torneoService;
	
	private final static Logger logger = LoggerFactory.getLogger(TorneoValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return Torneo.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Torneo o = (Torneo) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataInizio", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataFine", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataInizioIscrizioni", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataFineIscrizioni", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sala", "required");
		
		if(!errors.hasErrors()) {
			logger.debug("valori validi");
			if(this.torneoService.alreadyExists(o)) {
				logger.debug("opera duplicata");
				errors.reject("duplicato");
			}
		}
	}
}
