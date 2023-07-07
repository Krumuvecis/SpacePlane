package vehicleGraphics;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import vehicles.Vehicle;

//
class VehiclePainter implements PainterInterface {
    private static final Color COLOR = Color.RED;
    private final ImageObserver imageObserver;
    private final VehiclePanelData panelData;

    //
    protected VehiclePainter(ImageObserver imageObserver, VehiclePanelData panelData) {
        this.imageObserver = imageObserver;
        this.panelData = panelData;
    }

    //
    @Override
    public final void paint(Graphics g, int[] drawLocation) {
        Dimension size = VehiclePanelData.getOptimalSize();
        int[] drawCenter = new int[] {
                drawLocation[0] + size.width / 2,
                drawLocation[1] + size.height / 2};

        g.setColor(COLOR);
        g.drawString("Sample text", drawLocation[0], drawLocation[1] + 20);
    }

    //for rotation
    private AffineTransform getTransform(double angle, int[] center) {
        return new AffineTransform() {{
            translate(center[0], center[1]);
            rotate(Math.toRadians(angle));
            translate(-center[0], -center[1]);
        }};
    }
}