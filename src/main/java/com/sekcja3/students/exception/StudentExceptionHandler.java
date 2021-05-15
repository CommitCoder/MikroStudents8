package com.sekcja3.students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;




@RestControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(value = StudentException.class) // wyjątek który ma nam złapać
    public ResponseEntity<ErrorInfo> handleException(StudentException e){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if(StudentError.STUDENT_NOT_FOUND.equals(e.getStudentError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }else if(StudentError.STUDENT_ALREADY_EXISTS.equals(e.getStudentError())){
            httpStatus = HttpStatus.CONFLICT;
        }else if(StudentError.STUDENT_IS_NOT_ACTIVE.equals(e.getStudentError())){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getStudentError().getMessage()));
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }


}
