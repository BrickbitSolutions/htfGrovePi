package be.bewire.htf.grove;

import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.system.SystemInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.pi4j.io.i2c.I2CBus.BUS_0;
import static com.pi4j.io.i2c.I2CBus.BUS_1;

public class GroveBoard {
    public final static GroveBoard INSTANCE = new GroveBoard();
    private static final byte ADDRESS = 0x04;
    private final I2CDevice groveBoard;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GroveBoard() {
        I2CDevice groveBoard;

        try {
            Integer busId = SystemInfo.getBoardType().name().contains("ModelA") ? BUS_0 : BUS_1;
            groveBoard = I2CFactory.getInstance(busId).getDevice(ADDRESS);
        } catch (IOException | InterruptedException e) {
            logger.error("Could not get the board data", e);
            groveBoard = null;
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            logger.error("Could not instantiate I2C groveBoard", e);
            groveBoard = null;
        }

        this.groveBoard = groveBoard;
    }

    public void write(Integer... bytes) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        Arrays.stream(bytes).forEach(b -> byteBuffer.put(b.byteValue()));
        sleep(100);
    }

    public byte[] read(int nrBytes) throws IOException {
        byte[] buffer = new byte[nrBytes];
        groveBoard.read(1, buffer, 0, buffer.length);
        return buffer;
    }

    public void setPinMode(Integer pin, Integer pinMode) throws IOException {
        write(Commands.PMODE, pin, pinMode, Commands.UNUSED);
    }

    public void sleep(int msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public String version() throws IOException {
        write(Commands.VERSION, Commands.UNUSED, Commands.UNUSED, Commands.UNUSED);
        sleep(100);
        byte[] b = read(4);
        read(1);

        return String.format("%s.%s.%s", (int)b[1], (int)b[2], (int)b[3]);
    }
}
