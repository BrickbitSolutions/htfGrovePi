package be.bewire.htf.grove.sensor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import be.bewire.htf.grove.Commands;
import be.bewire.htf.grove.GroveBoard;
import be.bewire.htf.utils.TemperatureScale;
import be.bewire.htf.utils.TemperatureUtils;

public class DHTDigitalSensor {
    private DHTModuleType moduleType;
    private TemperatureScale scale;
    private GroveBoard groveBoard;
    private Integer pin;

    public DHTDigitalSensor(Integer pin, DHTModuleType moduleType, TemperatureScale scale) throws
            IOException,
            InterruptedException {
        this.pin = pin;
        this.moduleType = moduleType;
        this.scale = scale;
        this.groveBoard = GroveBoard.INSTANCE;
    }

    public DHTDigitalSensor(int pin) throws IOException, InterruptedException {
        this(pin, DHTModuleType.DHT11, TemperatureScale.CELSIUS);
    }

    public Double[] read() throws IOException {
        groveBoard.write(Commands.DHT_TEMP, this.pin, this.moduleType.getValue(), Commands.UNUSED);
        groveBoard.sleep(500);
        groveBoard.read(1);
        groveBoard.sleep(500);

        byte[] result = groveBoard.read(9);

        Double temp = (double) ByteBuffer.wrap(result).order(ByteOrder.LITTLE_ENDIAN).getFloat(1);
        Double humidity = (double) ByteBuffer.wrap(result).order(ByteOrder.LITTLE_ENDIAN).getFloat(5);
        Double heatIndex = TemperatureUtils.getHeatIndex(temp, humidity, scale);

        return new Double[]{
                temp,
                humidity,
                heatIndex
        };
    }

    public enum DHTModuleType {
        DHT11(0),
        DHT21(2),
        DHT22(1),
        AM2301(3);

        private Integer value;

        DHTModuleType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}