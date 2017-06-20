package com.example.app;

import com.example.calculations.HeatIndex;
import com.example.calculations.Humidex;

public class Main {

    private static String humidexOutput(double centigrades, double relativeHumidityPercent) {
        Humidex humidex = new Humidex();
        double dewPointCelsius = humidex.calculateDewPointMagnusFormula(centigrades, relativeHumidityPercent);
        double humidexValue = humidex.calculateHumidexValue(centigrades, dewPointCelsius);

        return "Humidex: " + humidexValue + "\t - " + humidex.humidexToString(humidexValue);
    }

    private static String heatIndexOutput(double centigrades, double relativeHumidityPercent) {
        HeatIndex heatIndex = new HeatIndex();
        double heatIndexValue = heatIndex.calculatePerceivedCelsiusTemperatureFor(centigrades, relativeHumidityPercent);
        return "Heat Index " + heatIndexValue + " +/-" + HeatIndex.APPROXIMATION_ERROR + "°C";
    }

    /**
     * E.g.
     <pre>
     $ program 26 30
     Humidex: 26.055544783439473 - Little to no discomfort
     </pre>
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
