package be.bewire.htf.grove.sensor;

import java.io.IOException;

import be.bewire.htf.grove.Commands;
import be.bewire.htf.grove.GroveBoard;

public class GenericDigitalSensor {
    private int pin = 0;
    private GroveBoard board = GroveBoard.INSTANCE;

    public GenericDigitalSensor(int pin) throws IOException, InterruptedException {
        super();
        this.pin = pin;
    }

    public byte[] readBytes() throws IOException {
        this.board.write(Commands.DREAD, this.pin, Commands.UNUSED, Commands.UNUSED);
        this.board.sleep(100);
        return this.board.read(1);
    }

    public boolean write(int value) throws IOException {
        this.board.write(Commands.DWRITE, this.pin, value, Commands.UNUSED);
        this.board.sleep(100);
        return true;
    }
}