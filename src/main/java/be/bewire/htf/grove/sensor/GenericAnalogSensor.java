package be.bewire.htf.grove.sensor;

import java.io.IOException;

import be.bewire.htf.grove.Commands;
import be.bewire.htf.grove.GroveBoard;

public class GenericAnalogSensor {
    private int pin = 0;
    private int nrResponseBytes = 4;

    private GroveBoard board = GroveBoard.INSTANCE;

    public GenericAnalogSensor(int pin, int nrResponseBytes) throws IOException, InterruptedException {
        this.pin = pin;
        this.nrResponseBytes = nrResponseBytes;
    }

    public byte[] read() throws IOException {
        this.board.write(Commands.AREAD, this.pin, Commands.UNUSED, Commands.UNUSED);
        this.board.sleep(100);
        this.board.read(1);
        return this.board.read(this.nrResponseBytes);
    }

    public boolean write(int value) throws IOException {
        this.board.write(Commands.AWRITE, this.pin, value, Commands.UNUSED);
        this.board.sleep(100);
        return true;
    }
}