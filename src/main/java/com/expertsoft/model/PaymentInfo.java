package com.expertsoft.model;

public class PaymentInfo {

    public enum Type {
        VISA,
        MASTERCARD
    }

    public PaymentInfo() {}

    private Type type;
    private String cardNumber;
    private String securityCode;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
