package com.sekcja3.students.exception;

public enum StudentError {

    STUDENT_NOT_FOUND("Student does not exists");

    StudentError(String message) {
        this.message = message;
    }

    private String message;


    public String getMessage() {
        return message;
    }

}
