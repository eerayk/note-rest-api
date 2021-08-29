package com.example.noterestapi.dto;

public class ResponseDto {
    private Boolean error;
    private String message;

    public ResponseDto(){};

    public ResponseDto(Boolean error){
        this.error = error;
        this.message = "";
    }

    public ResponseDto(Boolean error, String message){
        this.error = error;
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
