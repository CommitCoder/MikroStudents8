package com.sekcja3.students.exception;

public enum StudentError {

    STUDENT_NOT_FOUND("Student does not exists"),
    STUDENT_ALREADY_EXISTS("Student email already exists"),
    STUDENT_IS_NOT_ACTIVE("student is inactive");

    StudentError(String message) {
        this.message = message;
    }
    private String message;

    public String getMessage() {
        return message;
    }

}
