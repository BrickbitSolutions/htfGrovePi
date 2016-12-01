package be.bewire.htf.integration;

import com.dexterind.grovepi.sensors.base.AnalogSensor;
import com.dexterind.grovepi.sensors.base.SensorStatus;

import java.io.IOException;

public class LEDApi extends AnalogSensor{
    private SensorStatus status = SensorStatus.OFF;
    public final static int MAX_BRIGHTNESS = 255;

    public LEDApi(Integer pin) throws IOException, InterruptedException {
        super(pin, MAX_BRIGHTNESS + 1);
    }

    /**
     * Turns the LED on to the maximum brightness.
     * @throws IOException
     */
    public void turnOn() throws IOException{
        this.write(MAX_BRIGHTNESS);
    }

    /**
     * Turns the LED off.
     * @throws IOException
     */
    public void turnOff() throws IOException{
        this.write(0);
    }

    /**
     * Toggles the LED on/off.
     * @return Returns the new status of the LED
     * @throws IOException
     */
    public SensorStatus toggle() throws IOException{
        setStatus(SensorStatus.toggle(status));
        return status;
    }

    /**
     * Sets the LED brightness to the specified percentage of the maximum brightness,
     * note dimming only works if connected to a pin that supports PWM otherwise this
     * will just appear to turn the LED on/off.
     * @param percent Percentage of maximum brightness, expects a number from 0 to 100
     * @throws IOException
     */
    public void setBrightness(float percent) throws IOException{
        if (percent <= 0){
            turnOff();
        }else if(percent >= 100){
            turnOn();
        }else{
            this.write((int)(MAX_BRIGHTNESS * percent/100));
        }
    }

    /**
     * Set the analog value of the LED and sensor status.
     * @param value Expects a value from 0 to MAX_BRIGHTNESS
     * @return
     * @throws IOException
     */
    @Override
    public boolean write(int value) throws IOException {
        if (value <= 0){
            status = SensorStatus.OFF;
            super.write(0);
        }else{
            status = SensorStatus.ON;
            super.write(Math.min(value, MAX_BRIGHTNESS));
        }
        return true;
    }

    /**
     * Returns the current status of the LED.
     * @return Returns the status of the LED
     */
    public SensorStatus getStatus(){
        return status;
    }

    /**
     * Set the status of the LED to the passed status.
     * @param status
     * @throws IOException
     */
    private void setStatus(SensorStatus status) throws IOException{
        if (status == SensorStatus.OFF){
            turnOff();
        }else{
            turnOn();
        }
    }
}
