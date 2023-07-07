package vehicleGraphics;

import java.awt.Color;
import java.awt.Graphics;

import vehicles.Vehicle;

//
class VehiclePainter implements PainterInterface {
    private static final Color COLOR = Color.RED;
    private final Vehicle vehicle;

    //
    protected VehiclePainter(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    //
    @Override
    public final void paint(Graphics g, int[] drawLocation) {
        g.setColor(COLOR);
        g.drawString("Sample text", drawLocation[0], drawLocation[1] + 20);
    }
}