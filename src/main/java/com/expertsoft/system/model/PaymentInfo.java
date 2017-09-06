package com.expertsoft.system.model;

public class PaymentInfo {

    public enum Provider {
        VISA,
        MASTERCARD
    }

    public PaymentInfo() {}

    private Provider provider;
    private String cardNumber;
    private String securityCode;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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
