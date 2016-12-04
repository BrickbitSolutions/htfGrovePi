package be.bewire.htf.utils;

public class TemperatureUtils {
    /**
     * Converts the temperature from Celsius To Fahrenheit
     * @param temp The measured temperature in Celsius
     * @return The temperature in Fahrenheit
     */
    public static Double convertCelsiusToFahrenheit(Double temp) {
        return temp * 9 / 5 + 32;
    }

    /**
     * Converts the temperature from Fahrenheit To Celsius
     * @param temp The measured temperature in Fahrenheit
     * @return The temperature in Celsius
     */
    public static Double convertFahrenheitToCelsius(Double temp) {
        return ((temp - 32) * 5 / 9);
    }

    /**
     * Calculates the Heat Index of given temperature and humidity
     * @param temp The measured temperature
     * @param hum The measured humidity
     * @param scale The temperature scale of the temperature
     * @return The Heat Index Value in the given temperature scale
     */
    public static Double getHeatIndex(Double temp, Double hum, TemperatureScale scale) {
        boolean needsConversion = scale == TemperatureScale.CELSIUS;
        temp = needsConversion ? convertCelsiusToFahrenheit(temp) : temp;

        double hi = -42.379 +
                2.04901523 * temp +
                10.14333127 * hum +
                -0.22475541 * temp * hum +
                -0.00683783 * Math.pow(temp, 2) +
                -0.05481717 * Math.pow(hum, 2) +
                0.00122874 * Math.pow(temp, 2) * hum +
                0.00085282 * temp * Math.pow(hum, 2) +
                -0.00000199 * Math.pow(temp, 2) * Math.pow(hum, 2);

        return needsConversion ? convertFahrenheitToCelsius(hi) : hi;
    }
}
