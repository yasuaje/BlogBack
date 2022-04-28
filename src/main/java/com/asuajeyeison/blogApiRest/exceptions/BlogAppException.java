package com.asuajeyeison.blogApiRest.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BlogAppException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public BlogAppException(HttpStatus status,String message){
        this.status = status;
        this.message = message;
    }
    public BlogAppException(HttpStatus status,String message,String message1){
        this.status = status;
        this.message = message;
        this.message = message1;
    }
}
