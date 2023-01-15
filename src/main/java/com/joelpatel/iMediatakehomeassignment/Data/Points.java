package com.joelpatel.iMediatakehomeassignment.Data;

import org.springframework.stereotype.Component;

@Component
public class Points {
    long total;
    long remaining;

    public Points() {
        this.total = 0;
        this.remaining = 1000;
    }

    public Points(long total, long remaining) {
        this.total = total;
        this.remaining = remaining;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getRemaining() {
        return remaining;
    }

    public void setRemaining(long remaining) {
        this.remaining = remaining;
    }

    public void consumePoints(long value) {
        this.setRemaining(this.getRemaining() - value);
        this.setTotal(this.getTotal() + value);
    }

    public void resetPoints(long quota) {
        this.setTotal(0L);
        this.setRemaining(quota);
    }
}
