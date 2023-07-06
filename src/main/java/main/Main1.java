package main;

import vehicles.SimpleVehicle;
import vehicles.Vehicle;

public class Main1 extends AbstractMain {
    private static final Vehicle VEHICLE = new SimpleVehicle();

    public static void main(String[] args) {
        new Main1();
    }

    @Override
    protected Vehicle getVehicle() {
        return VEHICLE;
    }
}