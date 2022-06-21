package com.masters.uj.otp.model;


public enum DeliveryType {
    E("E"), S("S");

    private final String value;

    DeliveryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public DeliveryType fromValue(String value) {
        if (E.value.equals(value)) {
            return E;
        }
        return S;
    }

}
