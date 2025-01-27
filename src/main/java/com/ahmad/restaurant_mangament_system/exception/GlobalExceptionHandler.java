package com.ahmad.restaurant_mangament_system.exception;

import com.ahmad.restaurant_mangament_system.security.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ErrorResponse handleServerErrorException(Exception e) {
//        log.error(e.getMessage());
//        return new ErrorResponse(500, e.getMessage());
//    }


    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiErrorResponse handleNotFoundException(NotFoundException e) {
   //     log.error(e.getMessage());
        return new ApiErrorResponse(404, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HandlerException.class)
    public ApiErrorResponse handlerException(HandlerException e) {
      //  log.error(e.getMessage());
        return new ApiErrorResponse(400, e.getMessage());
    }

}
