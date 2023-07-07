package vehicleGraphics;

import java.awt.*;

import vehicles.Vehicle;
import vehicles.VehicleAdapter;

//
public class VehiclePanelData {
    private static final Dimension
            OPTIMAL_SIZE = new Dimension(1000, 900);
    private static final Color
            BACKGROUND = Color.BLACK,
            BORDER_COLOR = Color.RED;
    private static final int BORDER_OFFSET = 0;

    //
    public static Dimension getOptimalSize() {
        return OPTIMAL_SIZE;
    }

    private final VehicleAdapter vehicleAdapter;

    //
    public VehiclePanelData(VehicleAdapter vehicleAdapter) {
        this.vehicleAdapter = vehicleAdapter;
    }

    //
    public Color getBackgroundColor() {
        return BACKGROUND;
    }

    //
    public Color getBorderColor() {
        return BORDER_COLOR;
    }

    //
    public int getBorderOffset() {
        return BORDER_OFFSET;
    }

    //
    public Vehicle getVehicle() {
        return vehicleAdapter.getVehicle();
    }
}