package main;

import vehicles.Vehicle;
import vehicles.SimpleVehicle;

//
public class Main1 extends AbstractMain {
    private static final Vehicle VEHICLE = new SimpleVehicle();

    //
    public static void main(String[] args) {
        new Main1();
    }

    //
    @Override
    protected Vehicle getVehicle() {
        return VEHICLE;
    }
}