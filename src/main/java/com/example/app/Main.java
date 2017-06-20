package com.example.app;

import com.example.calculations.HeatIndex;
import com.example.calculations.Humidex;

import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance(Locale.UK);

    static {
        NUMBER_FORMATTER.setMaximumFractionDigits(2);
    }

    private static String humidexOutput(double centigrades, double relativeHumidityPercent) {
        Humidex humidex = new Humidex();
        double dewPointCelsius = humidex.calculateDewPointMagnusFormula(centigrades, relativeHumidityPercent);
        double humidexValue = humidex.calculateHumidexValue(centigrades, dewPointCelsius);
        return "Humidex: \t" + NUMBER_FORMATTER.format(humidexValue) + "\t - " + humidex.humidexToString(humidexValue);
    }

    private static String heatIndexOutput(double centigrades, double relativeHumidityPercent) {
        HeatIndex heatIndex = new HeatIndex();
        double heatIndexValueCelsius = heatIndex.calculatePerceivedCelsiusTemperatureFor(centigrades, relativeHumidityPercent);
        return "Heat Index: \t" + NUMBER_FORMATTER.format(heatIndexValueCelsius) +
                " +/-" + NUMBER_FORMATTER.format(HeatIndex.APPROXIMATION_ERROR) + "°C" +
                "\t - " + heatIndex.heatIndexToString(heatIndexValueCelsius);
    }

    /**
     * E.g.
     * <pre>
     * $ program 26 30
     * Humidex: 26.055544783439473 - Little to no discomfort
     * </pre>
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: [program] centigrades relativeHumidityPercent (centigrades relativeHumidityPercent)*");
        } else {
            for (int i = 0; i < args.length - 1; i += 2) {
                double centigrades = Double.parseDouble(args[i]);
                double relativeHumidityPercent = Double.parseDouble(args[i + 1]);
                System.out.println(humidexOutput(centigrades, relativeHumidityPercent));
                System.out.println(heatIndexOutput(centigrades, relativeHumidityPercent));
            }
        }
    }

}
