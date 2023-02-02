package org.belgaia.permaculture.raisedbedplanerservice;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

/**
 * Thrown if a given @see RaisedBed or @see Planting or @see Plant already exists in the persistence layer.
 *
 * Created by Isabel Batista.
 */
public class AlreadyExistingElementException extends Exception implements GraphQLError {
    public AlreadyExistingElementException(String message) {
        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }
}
