package com.muhammet.exception;

import lombok.Getter;

@Getter
public class ElasticManagerException extends RuntimeException{
    /**
     * Uygujlama içinde fırlatılacak olan özelleştirilmiş hatalar için kullanılacaktır.
     */
    private final ErrorType errorType;

    public ElasticManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ElasticManagerException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }

}
