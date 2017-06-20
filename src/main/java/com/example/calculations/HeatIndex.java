package com.example.calculations;

/**
 * American version of the (Canadian) Humidex, based on Fahrenheit.
 * Confer https://en.m.wikipedia.org/wiki/Heat_index
 */
public class HeatIndex {

    private static final double C1 = -42.379d;
    private static final double C2 = 2.04901523d;
    private static final double C3 = 10.14333127d;
    private static final double C4 = -0.22475541d;
    private static final double C5 = -6.83783E-03d;
    private static final double C6 = -5.481717E-02d;
    private static final double C7 = 1.22874E-03d;
    private static final double C8 = 8.5282E-04d;
    private static final double C9 = -1.99E-06d;
    public static double APPROXIMATION_ERROR = 1.3d / 1.8d;

    /**
     * @param temperatureCelsius "ambient dry-bulb temperature"
     * @return perceived temperature in centigrades
     */
    public double calculatePerceivedCelsiusTemperatureFor(double temperatureCelsius, double percentRelativeHumidity) {

        double t = toFahrenheit(temperatureCelsius);

        double heatIndexFahrenheit =
                C1 +
                        C2 * t +
                        C3 * percentRelativeHumidity +
                        C4 * t * percentRelativeHumidity +
                        C5 * t * t +
                        C6 * percentRelativeHumidity * percentRelativeHumidity +
                        C7 * t * t * percentRelativeHumidity +
                        C8 * t * percentRelativeHumidity * percentRelativeHumidity +
                        C9 * t * t * percentRelativeHumidity * percentRelativeHumidity;

        return toCelsius(heatIndexFahrenheit);
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

    private double toFahrenheit(double temperatureCelsius) {
        return temperatureCelsius * 1.8d + 32.0d;
    }
}
