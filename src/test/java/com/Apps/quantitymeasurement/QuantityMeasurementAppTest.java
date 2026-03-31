package com.Apps.quantitymeasurement;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import com.Apps.quantitymeasurement.dto.QuantityDTO;
import com.Apps.quantitymeasurement.dto.QuantityInputDTO;
import com.Apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.Apps.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.Apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuantityMeasurementAppTest {

    @Mock
    private QuantityMeasurementRepository repository;

    @InjectMocks
    private QuantityMeasurementServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ADD TEST
    @Test
    void testAddLength() {

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");

        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(q1);
        input.setThatQuantityDTO(q2);

        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        QuantityMeasurementEntity result = service.add(input);

        assertNotNull(result);
        assertEquals("ADD", result.getOperation());
        assertEquals("FEET", result.getResultUnit());
    }

    //  COMPARE TEST
    @Test
    void testCompareEqual() {

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");

        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(q1);
        input.setThatQuantityDTO(q2);

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        QuantityMeasurementEntity result = service.compare(input);

        assertEquals(1.0, result.getResultValue());
    }

    //  DIVIDE TEST
    @Test
    void testDivide() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(2, "FEET", "LENGTH");

        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(q1);
        input.setThatQuantityDTO(q2);

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        QuantityMeasurementEntity result = service.divide(input);

        assertEquals(5.0, result.getResultValue());
        assertEquals("DIVIDE", result.getOperation());
    }

    //  DIVIDE BY ZERO (ERROR TEST)
    @Test
    void testDivideByZero() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(0, "FEET", "LENGTH");

        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(q1);
        input.setThatQuantityDTO(q2);

        assertThrows(RuntimeException.class, () -> service.divide(input));
    }

    //  TEMPERATURE COMPARE
    @Test
    void testTemperatureCompare() {

        QuantityDTO q1 = new QuantityDTO(32, "FAHRENHEIT", "TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(0, "CELSIUS", "TEMPERATURE");

        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(q1);
        input.setThatQuantityDTO(q2);

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        QuantityMeasurementEntity result = service.compare(input);

        assertEquals(1.0, result.getResultValue());
    }
}