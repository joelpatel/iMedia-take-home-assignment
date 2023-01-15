package com.joelpatel.iMediatakehomeassignment.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ValueRequestBody {
    @NotNull(message = "please provide value to be consumed")
    @Positive(message = "please enter a positive value to be consumed")
    long value;

    public ValueRequestBody() {
    }

    public ValueRequestBody(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
