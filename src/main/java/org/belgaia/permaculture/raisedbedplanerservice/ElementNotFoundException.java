package org.belgaia.permaculture.raisedbedplanerservice;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Thrown if an element of type @see Product or @see Certificate does not exist.
 *
 * Created by  Isabel Batista.
 */
public class ElementNotFoundException extends Exception implements GraphQLError {

    private String invalidField;

    public ElementNotFoundException(String message) {
        super(message);
        this.invalidField = invalidField;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("invalidField", invalidField);
    }
}
