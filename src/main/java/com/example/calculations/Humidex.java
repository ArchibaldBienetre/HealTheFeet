package com.example.calculations;

/**
 * Humidex: index number to hint at the perceived temparature. The humidex is based on relative humidty and temperature
 * (and, technically, atmospheric pressure):
 * <p>
 * Calculation of the (Canadian) Humidex: https://en.m.wikipedia.org/wiki/Humidex
 * <p>
 */
public class Humidex {

    /* Humidex */

    public double calculateHumidexValue(double temperatureCelsius, double dewPointCelsius) {
        return temperatureCelsius + 0.5555d * (6.11d * StrictMath.exp(humidexExponent(dewPointCelsius + 273.15d)) - 10d);
    }

    private double humidexExponent(double dewPointCelsius) {
        return 5417.7530d * (1.0d / 273.15d - 1.0d / dewPointCelsius);
    }

    /* Dew point */

    /**
     * There is also a very simple approximation that allows conversion between the dew point, temperature, and
     * relative humidity. This approach is accurate to within about ±1 °C as long as the relative humidity is above 50%:
     */
    @SuppressWarnings("unused")
    public double approximateDewPoint(double temperatureCelsius, double percentRelativeHumidity) {
        return temperatureCelsius - (100.0d - percentRelativeHumidity) / 5.0d;
    }

    /* Magnus formula */
    @SuppressWarnings("unused")
    private final double MAGNUS_A_IN_MILLIBARS = 6.112d;         // or 6.112d,   6.105d,     6.1121d,    6.1121d
    private final double MAGNUS_B = 17.67d;         // or 17.62d,   17.27d,     17.368d,    17.966d
    private final double MAGNUS_C_IN_CENTIGRADES = 243.5d;         // or 243.12d,  237.7d,     238.88d,    247.15d

    public double calculateDewPointMagnusFormula(double temperatureCelsius, double percentRelativeHumidity) {
        return MAGNUS_C_IN_CENTIGRADES * gammaM(temperatureCelsius, percentRelativeHumidity)
                / (MAGNUS_B - gammaM(temperatureCelsius, percentRelativeHumidity));
    }

    private double gammaM(double temperatureCelsius, double percentRelativeHumidity) {
        return StrictMath.log(percentRelativeHumidity / 100) + MAGNUS_B * temperatureCelsius
                / (MAGNUS_C_IN_CENTIGRADES + temperatureCelsius);
    }

    public String humidexToString(double humidex) {
        if (humidex < 20) {
            return "No discomfort";
        } else if (humidex <= 29) {
            return "Little to no discomfort";
        } else if (humidex <= 39) {
            return "Some discomfort";
        } else if (humidex <= 45) {
            return "Great discomfort, avoid exertion";
        } else {
            return "Dangerous; heat stroke quite possible";
        }
    }


}