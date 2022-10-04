package com.muhammet.exception;

import lombok.Getter;

@Getter
public class MainManagerException extends RuntimeException{
    /**
     * Uygujlama içinde fırlatılacak olan özelleştirilmiş hatalar için kullanılacaktır.
     */
    private final ErrorType errorType;

    public MainManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public MainManagerException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }

}
