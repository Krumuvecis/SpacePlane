package vehicleGraphics;

import vehicles.Vehicle;

import java.awt.*;

class VehiclePainter implements PainterInterface {
    private static final Color COLOR = Color.RED;
    private final Vehicle vehicle;

    VehiclePainter(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public final void paint(Graphics g) {
        g.setColor(COLOR);
        g.drawString("yo", 100, 100);
    }
}