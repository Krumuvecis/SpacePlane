package main;

import main.graphics.Window;
import vehicles.Vehicle;

abstract class AbstractMain {
    protected AbstractMain() {
        startGraphics(getVehicle());
    }

    protected abstract Vehicle getVehicle();

    private void startGraphics(Vehicle vehicle) {
        new Window(vehicle);
    }
}