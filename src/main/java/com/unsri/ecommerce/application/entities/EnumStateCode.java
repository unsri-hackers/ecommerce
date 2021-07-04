package com.unsri.ecommerce.application.entities;

public enum EnumStateCode {
    SUCCESS("SUCCESS"),
    ERROR("ERROR");

    public final String label;

    private EnumStateCode(String label) {
        this.label = label;
    }
}
