package vehicleGraphics;

import java.awt.*;

import vehicles.Vehicle;
import vehicles.VehicleAdapter;

//
public class VehiclePanelData {
    private static final Color
            BACKGROUND = Color.BLACK,
            BORDER_COLOR = Color.RED;
    private static final int BORDER_OFFSET = 0;

    private final VehicleAdapter vehicleAdapter;
    private final Dimension panelSize;

    //
    public VehiclePanelData(VehicleAdapter vehicleAdapter, Dimension panelSize) {
        this.vehicleAdapter = vehicleAdapter;
        this.panelSize = panelSize;
    }

    //
    public void setPanelSize(int width, int height) {
        panelSize.width = width;
        panelSize.height = height;
    }

    //
    public Dimension getPanelSize() {
        return panelSize;
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