package be.bewire.htf.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

import be.bewire.htf.integration.LEDApi;
import be.bewire.htf.service.api.GroveService;

@Service
public class GroveServiceImpl implements GroveService {
    private final LEDApi ledApi;

    public GroveServiceImpl() throws IOException, InterruptedException {
        this.ledApi = new LEDApi(4);
    }

    public void toggleLed() throws IOException {
        ledApi.toggle();
    }
}
