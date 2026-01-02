package com.ismaildurcan.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_FOUND("1001", "No record found!"),
    GENERAL_EXCEPTION("9999", "An error occurred while processing your request.");

    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
