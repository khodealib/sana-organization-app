package com.asenadev.sana.utils;

public enum CustomerState {
    PRESENT(1), EXITED(0);

    private int value;

    CustomerState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
