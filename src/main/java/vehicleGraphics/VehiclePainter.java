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
    private static final Color
            PART_COLOR = new Color(100, 100, 100),
            PART_BORDER_COLOR = new Color(150, 150, 150),
            PART_TEXT_COLOR = new Color(200, 200, 200);
    private static final Font
            PART_TITLE_FONT = new Font("Verdana", Font.BOLD, 18),
            PART_DETAILS_FONT = new Font(null, Font.BOLD, 15);
    private static final int
            PART_MASS_DECIMAL_DIGITS = 2,
            PART_LENGTH_DECIMAL_DIGITS = 1;
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

    //for rotation
    private AffineTransform getTransform(double angle, int[] center) {
        return new AffineTransform() {{
            translate(center[0], center[1]);
            rotate(Math.toRadians(angle));
            translate(-center[0], -center[1]);
        }};
    }

    private void paintVehicle(Graphics2D g2, int[] drawSize) {
        int[] drawCenter = new int[] {drawSize[0] / 2, drawSize[1] / 2};

        //vehicle
        Vehicle vehicle = panelData.getVehicle();
        double scale = vehicle.getLength() / drawSize[0];

        //parts
        List<VehiclePart> parts = vehicle.getParts();
        int xPart = 0;
        for (int i = 0; i < parts.size(); i++) {
            VehiclePart part = parts.get(i);
            double[]
                    scaledPartSize = new double[] {
                            part.getLength() / scale,
                            part.getDiameter() / scale},
                    partDrawLoc = new double[] {
                            drawCenter[0] + (double) drawSize[0] / 2 - scaledPartSize[0] - xPart / scale,
                            drawCenter[1] - scaledPartSize[1] / 2};
            paintVehiclePart(g2, part, partDrawLoc, scaledPartSize);
            xPart += part.getLength();
        }
    }

    private void paintVehiclePart(Graphics2D g2, VehiclePart part,
                                  double[] partDrawLoc, double[] scaledPartSize) {
        g2.setColor(PART_COLOR);
        g2.fillRect(
                (int) partDrawLoc[0], (int) partDrawLoc[1],
                (int) scaledPartSize[0], (int) scaledPartSize[1]);
        g2.setColor(PART_BORDER_COLOR);
        g2.drawRect(
                (int) partDrawLoc[0], (int) partDrawLoc[1],
                (int) scaledPartSize[0], (int) scaledPartSize[1]);
        paintPartInfo(g2, part, partDrawLoc, scaledPartSize);
    }

    private void paintPartInfo(Graphics2D g2, VehiclePart part,
                               double[] partDrawLoc, double[] scaledPartSize) {
        g2.setColor(PART_TEXT_COLOR);
        int[] textOffset = new int[] {-35, 5};

        //title
        g2.setFont(PART_TITLE_FONT);
        g2.drawString(
                part.getName(),
                (int) (partDrawLoc[0] + scaledPartSize[0] / 2 + textOffset[0]),
                (int) (partDrawLoc[1] + scaledPartSize[1] / 2 + textOffset[1]));

        //details
        g2.setFont(PART_DETAILS_FONT);
        String massString = "M: " + convertMassToTonnes(part.getMass()) + " t";
        g2.drawString(
                massString,
                (int) (partDrawLoc[0] + scaledPartSize[0] / 2 + textOffset[0]),
                (int) (partDrawLoc[1] + scaledPartSize[1] / 2 + textOffset[1]) + 20);

        String sizeString = "L: " + roundLength(part.getLength()) + " m";
        g2.drawString(
                sizeString,
                (int) (partDrawLoc[0] + scaledPartSize[0] / 2 + textOffset[0]),
                (int) (partDrawLoc[1] + scaledPartSize[1] / 2 + textOffset[1]) + 40);
    }

    private double convertMassToTonnes(double mass) {
        return roundToDecimalDigits(mass / 1000, PART_MASS_DECIMAL_DIGITS);
    }

    private double roundLength(double length) {
        return roundToDecimalDigits(length, PART_LENGTH_DECIMAL_DIGITS);
    }

    private double roundToDecimalDigits(double value, int decimalDigits) {
        return ((int) (value * Math.pow(10, decimalDigits))) / Math.pow(10.0, decimalDigits);
    }
}