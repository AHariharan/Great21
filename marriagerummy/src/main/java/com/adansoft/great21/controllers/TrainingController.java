package com.adansoft.great21.controllers;

import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.controller.helpers.TrainingHelper;
import com.adansoft.great21.training.schemas.AdvanceTrainingValidationRequest;
import com.adansoft.great21.training.schemas.ValidationTypes;

@RestController
@RequestMapping(FacadeControllerURLs.TRAINING_BASE)
@EnableWebMvcSecurity
public class TrainingController {
	
	@RequestMapping( value = FacadeControllerURLs.ADVANCE_TRAINING_VALID, method = RequestMethod.POST)
	public boolean validateAdvanceTrainingRequest(@RequestBody AdvanceTrainingValidationRequest incomingrequest)
	{
		if(incomingrequest.getValidationTypes().equals(ValidationTypes.SEQ_VAL_TYPE))
		     return TrainingHelper.validateSequence(incomingrequest.getCardInstanceID());
		else if(incomingrequest.getValidationTypes().equals(ValidationTypes.TRIP_VAL_TYPE))
			return TrainingHelper.validateTriplets(incomingrequest.getCardInstanceID());
		else if(incomingrequest.getValidationTypes().equals(ValidationTypes.SEQ_VAL_JOKER_TYPE))
			return TrainingHelper.validateSequenceWithJoker(incomingrequest.getCardInstanceID());
		else if(incomingrequest.getValidationTypes().equals(ValidationTypes.TRIP_VAL_JOKER_TYPE))
			return TrainingHelper.validateTripletWithJoker(incomingrequest.getCardInstanceID());
		else
			return false;
	}

}
