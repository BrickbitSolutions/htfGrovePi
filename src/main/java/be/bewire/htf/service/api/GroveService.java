package be.bewire.htf.service.api;

import com.dexterind.grovepi.sensors.base.SensorStatus;

import java.io.IOException;

public interface GroveService {
    SensorStatus toggleLed() throws IOException;
}
