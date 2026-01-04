package com.ismaildurcan.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_FOUND("1001", "No record found!"),
    TOKEN_IS_EXPIRED("1002", "The token has expired."),
    USERNAME_NOT_FOUND("1003", "Username not found."),
    USERNAME_OR_PASSWORD_INVALID("1004", "Username or password is invalid."),
    REFRESH_TOKEN_NOT_FOUND("1005", "Refresh token not found."),
    REFRESH_TOKEN_IS_EXPIRED("1006", "The refresh token has expired."),
    CURRENCY_RATES_IS_OCCURRED("1007", "An error occurred while fetching currency rates from the TCMB."),
    CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1008", "Customer's amount is not enough to buy the car."),
    CAR_STATUS_IS_ALREADY_SALED("1009", "The car has already been sold."),
    GENERAL_EXCEPTION("9999", "An error occurred while processing your request.");

    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
