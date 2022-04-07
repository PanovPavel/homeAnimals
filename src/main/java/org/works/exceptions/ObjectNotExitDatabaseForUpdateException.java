package org.works.exceptions;

public class ObjectNotExitDatabaseForUpdateException extends RuntimeException{
    public ObjectNotExitDatabaseForUpdateException(String message) {
        super(message);
    }
}
