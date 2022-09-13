package com.praveen.crud.student.Exception;

/*import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

*//*    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request)
                                                                                    {
        StudentErrorResponse exceptionResponse = new StudentErrorResponse(new Date(),
                "Validation Failed",
                ex.getBindingResult().toString());
            return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }*//*
}*/
