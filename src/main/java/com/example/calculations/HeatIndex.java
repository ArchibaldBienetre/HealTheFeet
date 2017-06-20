package com.example.calculations;

import java.math.BigDecimal;

/**
 * American version of the (Canadian) Humidex, based on Fahrenheit.
 * Confer https://en.m.wikipedia.org/wiki/Heat_index
 */
public class HeatIndex {

    public static double APPROXIMATION_ERROR = 1.3d / 1.8d;

    private static final BigDecimal C1 = bd(-42.379d);
    private static final BigDecimal C2 = bd(2.04901523d);
    private static final BigDecimal C3 = bd(10.14333127d);
    private static final BigDecimal C4 = bd(-0.22475541d);
    private static final BigDecimal C5 = bd(-6.83783E-03d);
    private static final BigDecimal C6 = bd(-5.481717E-02d);
    private static final BigDecimal C7 = bd(1.22874E-03d);
    private static final BigDecimal C8 = bd(8.5282E-04d);
    private static final BigDecimal C9 = bd(-1.99E-06d);

    /**
     * @param temperatureCelsius "ambient dry-bulb temperature"
     * @return perceived temperature in centigrades
     */
    public double calculatePerceivedCelsiusTemperatureFor(double temperatureCelsius, double percentRelativeHumidity) {

        BigDecimal t = toFahrenheit(bd(temperatureCelsius));
        BigDecimal rh = bd(percentRelativeHumidity);

        BigDecimal heatIndexFahrenheit =
                C1.add(
                        C2.multiply(t)
                ).add(
                        C3.multiply(rh)
                ).add(
                        C4.multiply(t).multiply(rh)
                ).add(
                        C5.multiply(t).multiply(t)
                ).add(
                        C6.multiply(rh).multiply(rh)
                ).add(
                        C7.multiply(t).multiply(t).multiply(rh)
                ).add(
                        C8.multiply(t).multiply(rh).multiply(rh)
                ).add(
                        C9.multiply(t).multiply(t).multiply(rh).multiply(rh)
                );

        return toCelsius(d(heatIndexFahrenheit));
    }

    public String heatIndexToString(double heatIndexCelsius) {
        if (heatIndexCelsius < 27.0d) {
            return "No effect.";
        } else if (heatIndexCelsius < 32.0d) {
            return "Caution: fatigue is possible with prolonged exposure and activity. Continuing activity could result in heat cramps.";
        } else if (heatIndexCelsius < 41.0d) {
            return "Extreme caution: heat cramps and heat exhaustion are possible. Continuing activity could result in heat stroke.";
        } else if (heatIndexCelsius < 54.0d) {
            return "Danger: heat cramps and heat exhaustion are likely; heat stroke is probable with continued activity.";
        } else {
            return "Extreme danger: heat stroke is imminent.";
        }
    }


    private double toCelsius(double temperatureFahrenheit) {
        return (temperatureFahrenheit - 32.0d) / 1.8d;
    }

    // divide throws ArithmeticExceptions often - will use double for converting result back
//    private BigDecimal toCelsius(BigDecimal temperatureFahrenheit) {
//        return (temperatureFahrenheit.subtract(bd(32.0d))).divide(bd(1.8d));
//    }

    private BigDecimal toFahrenheit(BigDecimal temperatureCelsius) {
        return temperatureCelsius.multiply(bd(1.8d)).add(bd(32.0d));
    }

    private static double d(BigDecimal bd) {
        return bd.doubleValue();
    }

    private static BigDecimal bd(double aDouble) {
        return new BigDecimal(aDouble);
    }
}
