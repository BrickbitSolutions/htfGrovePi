package be.bewire.htf.service.api;

import java.io.IOException;

import be.bewire.htf.grove.sensor.SensorStatus;

public interface GroveService {
    SensorStatus toggleLed() throws IOException;
}
