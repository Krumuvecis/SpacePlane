package vehicleGraphics;

import java.util.List;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import vehicles.Vehicle;
import vehicles.VehiclePart;

//
class VehiclePainter implements PainterInterface {
    private static final int[] VEHICLE_DRAW_SIZE = new int[] {980, 880};
    private static final Color TEST_COLOR = Color.RED;
    private final ImageObserver imageObserver;
    private final VehiclePanelData panelData;
    private final int[]
            panelDrawSize,
            vehicleDrawSize;

    //
    protected VehiclePainter(ImageObserver imageObserver, VehiclePanelData panelData) {
        this.imageObserver = imageObserver;
        this.panelData = panelData;

        Dimension size = VehiclePanelData.getOptimalSize();
        panelDrawSize = new int[] {size.width, size.height};
        vehicleDrawSize = VEHICLE_DRAW_SIZE;
    }

    //
    @Override
    public final void paint(Graphics g, int[] drawLocation) {
        BufferedImage image = new BufferedImage(
                panelDrawSize[0], panelDrawSize[1],
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        double angle = -20;
        g2.transform(getTransform(
                angle, new int[] {
                        vehicleDrawSize[0] / 2,
                        vehicleDrawSize[1] / 2}));
        paintVehicle(g2, vehicleDrawSize);
        int[] vehicleDrawLocation = new int[] {
                drawLocation[0] + (panelDrawSize[0] - vehicleDrawSize[0]) / 2,
                drawLocation[1] + (panelDrawSize[1] - vehicleDrawSize[1]) / 2};
        g.drawImage(image, vehicleDrawLocation[0], vehicleDrawLocation[1], imageObserver);
    }

    private void paintVehicle(Graphics2D g2, int[] drawSize) {
        int[] drawCenter = new int[] {drawSize[0] / 2, drawSize[1] / 2};

        //vehicle
        Vehicle vehicle = panelData.getVehicle();
        double scale = vehicle.getLength() / drawSize[0];

        List<VehiclePart> parts = vehicle.getParts();
        int xPart = 0;
        for (int i = 0; i < parts.size(); i++) {
            VehiclePart part = parts.get(i);
            double[] scaledPartSize = new double[] {
                    part.getLength() / scale,
                    part.getDiameter() / scale};
            int[] partDrawLoc = new int[] {
                    (int)(drawCenter[0] + drawSize[0] / 2 - scaledPartSize[0] - xPart / scale),
                    (int)(drawCenter[1] - scaledPartSize[1] / 2)};

            g2.setColor(TEST_COLOR);
            g2.drawRect(
                    partDrawLoc[0], partDrawLoc[1],
                    (int) scaledPartSize[0], (int) scaledPartSize[1]);
            g2.drawString(
                    part.getName(),
                    (int)(partDrawLoc[0] + scaledPartSize[0] / 2),
                    (int)(partDrawLoc[1] + scaledPartSize[1] / 2));

            xPart += part.getLength();
        }
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