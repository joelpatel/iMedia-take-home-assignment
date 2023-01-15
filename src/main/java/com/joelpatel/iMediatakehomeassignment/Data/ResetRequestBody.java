package com.joelpatel.iMediatakehomeassignment.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ResetRequestBody {
    @NotNull(message = "please provide quota for resetting points")
    @Positive(message = "please enter a positive quota")
    long quota;

    public ResetRequestBody() {
    }

    public ResetRequestBody(long quota) {
        this.quota = quota;
    }

    public long getQuota() {
        return quota;
    }

    public void setQuota(long quota) {
        this.quota = quota;
    }
}
