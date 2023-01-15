package com.joelpatel.iMediatakehomeassignment.controller;

import com.joelpatel.iMediatakehomeassignment.Data.Points;
import com.joelpatel.iMediatakehomeassignment.Data.ResetRequestBody;
import com.joelpatel.iMediatakehomeassignment.Data.ValueRequestBody;
import com.joelpatel.iMediatakehomeassignment.exceptions.ExcessException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class PointsController {
    @Autowired
    Points points;

    @PostMapping("/consume")
    public ResponseEntity<Points> consumePoints(@RequestBody @Valid ValueRequestBody body) {
        if (points.getRemaining() < body.getValue()) {

            long excess = body.getValue() - points.getRemaining();
            throw new ExcessException(excess, HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            points.consumePoints(body.getValue());
            return new ResponseEntity<>(points, HttpStatus.OK);
        }
    }

    @GetMapping("/consume")
    public ResponseEntity<Points> getPoints() {
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @PostMapping("/reset")
    public ResponseEntity<Points> resetPoints(@RequestBody @Valid ResetRequestBody body) {
        points.resetPoints(body.getQuota());
        return new ResponseEntity<>(points, HttpStatus.OK);
    }
}
