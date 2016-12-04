package be.bewire.htf.integration;

import java.io.IOException;

import be.bewire.htf.grove.sensor.GenericDigitalSensor;
import be.bewire.htf.grove.sensor.SensorStatus;

public class LEDApi extends GenericDigitalSensor {
    public final static int MAX_BRIGHTNESS = 255;
    private SensorStatus status = SensorStatus.OFF;

    public LEDApi(Integer pin) throws IOException, InterruptedException {
        super(pin);
    }

    /**
     * Turns the LED on to the maximum brightness.
     */
    public void turnOn() throws IOException {
        this.write(MAX_BRIGHTNESS);
    }

    /**
     * Turns the LED off.
     */
    public void turnOff() throws IOException {
        this.write(0);
    }

    /**
     * Toggles the LED on/off.
     */
    public void toggle() throws IOException {
        if(status == SensorStatus.ON){
            turnOff();
        }else{
            turnOn();
        }
    }

    /**
     * Sets the LED brightness to the specified percentage of the maximum brightness,
     * note dimming only works if connected to a pin that supports PWM otherwise this
     * will just appear to turn the LED on/off.
     *
     * @param percent Percentage of maximum brightness, expects a number from 0 to 100
     */
    public void setBrightness(Double percent) throws IOException {
        if (percent <= 0) {
            turnOff();
        } else if (percent >= 100) {
            turnOn();
        } else {
            this.write((int) (MAX_BRIGHTNESS * percent / 100));
            status = SensorStatus.ON;
        }
    }

    /**
     * Returns the current status of the LED.
     *
     * @return Returns the status of the LED
     */
    public SensorStatus getStatus() {
        return status;
    }
}
