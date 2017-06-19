package com.example.calculations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HumidexTest {

    private Humidex _sut = new Humidex();

    @Test
    public void test_approximateDewPoint() {
        assertEquals(17.00d, _sut.approximateDewPoint(30d, 35d), 0.1d);
    }

    @Test
    public void test_calculateDewPointMagnusFormula() {
        assertEquals(12.89d, _sut.calculateDewPointMagnusFormula(30d, 35d), 0.1d);
    }

    @Test
    public void test_calculateHumidexValue() {
        assertEquals(56.74d, _sut.calculateHumidexValue(30d, 35d), 0.1d);
    }

    @Test
    public void test_humidexToString() {
        assertEquals("Some discomfort", _sut.humidexToString(35d));
    }

}
