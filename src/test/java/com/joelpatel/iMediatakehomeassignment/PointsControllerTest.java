package com.joelpatel.iMediatakehomeassignment;

import com.joelpatel.iMediatakehomeassignment.Data.Points;
import com.joelpatel.iMediatakehomeassignment.Data.ResetRequestBody;
import com.joelpatel.iMediatakehomeassignment.Data.ValueRequestBody;
import com.joelpatel.iMediatakehomeassignment.controller.PointsController;
import com.joelpatel.iMediatakehomeassignment.exceptions.ExcessException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ContextConfiguration(classes = { PointsController.class, ValidationAutoConfiguration.class })
public class PointsControllerTest {
    @Autowired
    private PointsController pointsController;

    @BeforeEach
    @Test
    public void testReset() {
        ResetRequestBody body = new ResetRequestBody(1000L);
        ResponseEntity<Points> pointsResponseEntity = pointsController.resetPoints(body);
        assertEquals(HttpStatus.OK, pointsResponseEntity.getStatusCode());

        Points points = pointsResponseEntity.getBody();

        assertNotNull(pointsResponseEntity);
        assertNotNull(points);
        assertEquals(points.getRemaining(), body.getQuota());
        assertEquals(points.getTotal(), 0L);
    }

    @Test
    public void testConsumePointsEndpoint() {
        ValueRequestBody body = new ValueRequestBody(100L);

        ResponseEntity<Points> pointsResponseEntity = pointsController.consumePoints(body);
        assertEquals(HttpStatus.OK, pointsResponseEntity.getStatusCode());

        Points returnedPoints = pointsResponseEntity.getBody();

        assertNotNull(pointsResponseEntity);
        assertNotNull(returnedPoints);
        assertEquals(returnedPoints.getTotal(), body.getValue());
        assertEquals(returnedPoints.getRemaining() + body.getValue(), 1000L);
    }

    @Test
    public void testConsumePointsEndpointExcessException() {
        ValueRequestBody body = new ValueRequestBody(1001L);

        ExcessException exception = assertThrows(ExcessException.class, () -> pointsController.consumePoints(body));
        assertEquals(exception.getExcess(), 1L);
        assertEquals(exception.getStatusCode(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void testConsumePointsConstraintViolationException() {
        ValueRequestBody body = new ValueRequestBody(-1L);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> pointsController.consumePoints(body));
        assertTrue(exception.getMessage().contains("please enter a positive value to be consumed"));

        testReset();

        ValueRequestBody body2 = new ValueRequestBody(0L);
        ConstraintViolationException exception2 = assertThrows(ConstraintViolationException.class, () -> pointsController.consumePoints(body2));
        assertTrue(exception2.getMessage().contains("please enter a positive value to be consumed"));
    }

    @Test
    public void testResetPointsConstraintViolationException() {
        ResetRequestBody body = new ResetRequestBody(-1L);
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> pointsController.resetPoints(body));
        assertTrue(exception.getMessage().contains("please enter a positive quota"));

        testReset();

        ResetRequestBody body2 = new ResetRequestBody(0L);
        ConstraintViolationException exception2 = assertThrows(ConstraintViolationException.class, () -> pointsController.resetPoints(body2));
        assertTrue(exception2.getMessage().contains("please enter a positive quota"));
    }
}
