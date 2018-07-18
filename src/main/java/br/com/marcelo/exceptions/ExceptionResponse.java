package br.com.marcelo.exceptions;

import br.com.marcelo.constants.ErrorCodes;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ExceptionResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String code;
    private String message;
    private List<String> details;


    public ExceptionResponse(ErrorCodes errorCode, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = Collections.singletonList(details);
    }

    public ExceptionResponse(ErrorCodes errorCode, List<String> details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = details;
    }

    public ExceptionResponse(String code) {
        this.code = code;
    }

    public ExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

