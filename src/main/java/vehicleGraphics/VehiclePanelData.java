package vehicleGraphics;

import java.awt.Dimension;

import vehicles.Vehicle;

//
public class VehiclePanelData {
    private static final Dimension
            OPTIMAL_SIZE = new Dimension(500, 500);

    //
    public static Dimension getOptimalSize() {
        return OPTIMAL_SIZE;
    }

    private final Vehicle vehicle;

    //
    public VehiclePanelData(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    //
    public Vehicle getVehicle() {
        return vehicle;
    }
}