package com.example.calculations;

/**
 * American version of the (Canadian) Humidex, based on Fahrenheit.
 * Confer https://en.m.wikipedia.org/wiki/Heat_index
 */
public class HeatIndex {

    public static double APPROXIMATION_ERROR = 1.3d;
    /**
     * @param temperatureCelsius "ambient dry-bulb temperature"
     * @return perceived temperature in centigrades
     */
    public double calculatePerceivedCelsiusTemperatureFor(double temperatureCelsius, double percentRelativeHumidity) {

        double t = toFahrenheit(temperatureCelsius);

        // TODO make them into constants
        double c_1 = -42.379d;
        double c_2 = 2.04901523d;
        double c_3 = 10.14333127d;
        double c_4 = -0.22475541d;
        double c_5 = -6.83783E-03d;
        double c_6 = -5.481717E-02d;
        double c_7 = 1.22874E-03d;
        double c_8 = 8.5282E-04d;
        double c_9 = -1.99E-06d;

        double heatIndexFahrenheit =
                c_1 +
                        c_2 * t +
                        c_3 * percentRelativeHumidity +
                        c_4 * t * percentRelativeHumidity +
                        c_5 * t * t +
                        c_6 * percentRelativeHumidity * percentRelativeHumidity +
                        c_7 * t * t * percentRelativeHumidity +
                        c_8 * t * percentRelativeHumidity * percentRelativeHumidity +
                        c_9 * t * t * percentRelativeHumidity * percentRelativeHumidity;

        return toCelsius(heatIndexFahrenheit);
    }


    private double toCelsius(double temperatureFahrenheit) {
        return (temperatureFahrenheit - 32.0d) / 1.8d;
    }

    private double toFahrenheit(double temperatureCelsius) {
        return temperatureCelsius * 1.8d + 32.0d;
    }
}
