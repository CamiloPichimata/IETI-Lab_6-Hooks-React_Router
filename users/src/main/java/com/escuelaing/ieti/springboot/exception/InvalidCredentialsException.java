package com.escuelaing.ieti.springboot.exception;

import com.escuelaing.ieti.springboot.dto.ServerErrorResponseDto;
import com.escuelaing.ieti.springboot.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

import javax.ws.rs.InternalServerErrorException;

public class InvalidCredentialsException extends InternalServerErrorException {

    public InvalidCredentialsException() {
        super(String.valueOf(HttpStatus.NOT_FOUND));
        ServerErrorResponseDto serverErrorResponseDto = new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
