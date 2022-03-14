package com.sdi21.socialnetwork.validators;

import com.sdi21.socialnetwork.entities.Publication;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PublicationValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Publication.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Publication publication = (Publication) target;
    }
}
