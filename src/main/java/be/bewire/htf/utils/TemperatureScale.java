package be.bewire.htf.utils;

public enum TemperatureScale {
    CELSIUS(0),
    FAHRENHEIT(1);

    private Integer value;

    TemperatureScale(Integer intValue) {
        this.value = intValue;
    }

    public Integer getValue() {
        return value;
    }
}
