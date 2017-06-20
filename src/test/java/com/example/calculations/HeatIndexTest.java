package com.example.calculations;

import org.junit.jupiter.api.Test;

import static com.example.calculations.HeatIndex.APPROXIMATION_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeatIndexTest {

    private static final double DELTA = 1.0d + APPROXIMATION_ERROR;
    private HeatIndex _sut = new HeatIndex();

    // I cannot seem to get this to work in IDEA, nor with maven >:[
//    @ParameterizedTest
//    @MethodSource(names = "heatIndexProvider")
//    public void test_calculatePerceivedCelsiusTemperatureFor(double temperatureCelsius, double percentRelativeHumidity, double perceivedTemperatureCentigrades) throws Exception {
//        assertEquals(perceivedTemperatureCentigrades, _sut.calculatePerceivedCelsiusTemperatureFor(temperatureCelsius, percentRelativeHumidity), 0.1d);
//    }
//
//    static Stream<Arguments> heatIndexProvider() {
//        return Stream.of(
//                ObjectArrayArguments.create(27.0d, 40.0d, 27.0d),
//                ObjectArrayArguments.create(30.0d, 50.0d, 31.0d),
//                ObjectArrayArguments.create(32.0d, 60.0d, 38.0d)
//        );
//    }

    @Test
    public void test_calculatePerceivedCelsiusTemperatureFor() {
        double temperatureCelsius;
        double percentRelativeHumidity;
        double perceivedTemperatureCentigrades;

        temperatureCelsius = 27.0d;
        percentRelativeHumidity = 40.0d;
        perceivedTemperatureCentigrades = 27.0d;
        double actual = _sut.calculatePerceivedCelsiusTemperatureFor(temperatureCelsius, percentRelativeHumidity);
        assertEquals(perceivedTemperatureCentigrades, actual, DELTA);

        temperatureCelsius = 30.0d;
        percentRelativeHumidity = 50.0d;
        perceivedTemperatureCentigrades = 31.0d;
        actual = _sut.calculatePerceivedCelsiusTemperatureFor(temperatureCelsius, percentRelativeHumidity);
        assertEquals(perceivedTemperatureCentigrades, actual, DELTA);

        temperatureCelsius = 32.0d;
        percentRelativeHumidity = 60.0d;
        perceivedTemperatureCentigrades = 38.0d;
        actual = _sut.calculatePerceivedCelsiusTemperatureFor(temperatureCelsius, percentRelativeHumidity);
        assertEquals(perceivedTemperatureCentigrades, actual, DELTA);
    }
}
