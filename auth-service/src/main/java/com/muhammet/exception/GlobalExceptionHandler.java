package com.muhammet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 1- Method a tamamen keyfidir. İstediğiniz gibi isimlendirebilirsiniz.
     * 2- Geridönüş tipi de keyfidir. İstenilen bir değer döndürülebilir.
     * **** Ancak, buraya kontrol edilebilen ve sınıflandırılmış mesajlar koymak doğru yaklaşım olacaktır. ***
     * 3- Method içi parametreleri yakalamak istediğiniz istisnaını türünde olmalı.
     * 4- Spring' in otomatik olarak yakalayacağı istisnanın türünü bildirmeniz gerekir.
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.ok("Beklenmeyen bir hata oluştu: " + ex.getMessage());
    }

    @ExceptionHandler(AuthManagerException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleMonolitikManagerException(AuthManagerException ex){
        ErrorType errorType = ex.getErrorType();
        HttpStatus httpStatus = errorType.getHttpStatus();
        /**
         * ResponseEntity.ok().build(); // 200
         */
        return new ResponseEntity<>(createError(errorType,ex),httpStatus);
    }


    private ErrorMessage createError(ErrorType errorType, Exception exception){
        System.out.println("Hata oluştu: " + exception.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }
}
