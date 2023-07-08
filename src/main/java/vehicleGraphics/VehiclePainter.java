package vehicleGraphics;

import java.util.List;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import vehicles.Vehicle;
import vehicles.VehiclePart;
import vehicles.VehiclePartLocationInfo;

//
class VehiclePainter implements PainterInterface {
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
    private final double angle;

    //
    protected VehiclePainter(ImageObserver imageObserver, VehiclePanelData panelData,
                             double angle) {
        this.imageObserver = imageObserver;
        this.panelData = panelData;
        this.angle = angle;
    }

    //
    @Override
    public final void paint(Graphics g, int[] drawLocation) {
        Dimension panelSize = panelData.getPanelSize();
        int drawDiameter = Math.min(panelSize.width, panelSize.height);

        //draw y = actual -x
        //draw x = actual -y

        //vehicle & scale
        Vehicle vehicle = panelData.getVehicle();
        double scale = vehicle.getLength() / drawDiameter;

        double[] drawCenter = new double[] {
                panelSize.width / 2.0,
                panelSize.height / 2.0
        };

        for (VehiclePart part : vehicle.getParts()) {
            paintPart(g, part, vehicle.getPartInfo(part), drawCenter, scale);
        }

        //buffered image stuff
    }

    private void paintPart(Graphics g,
                           VehiclePart part, VehiclePartLocationInfo partInfo,
                           double[] drawCenter, double scale) {
        double[] partDrawLoc = new double[] {
                drawCenter[0] - partInfo.getLocation()[1] / scale,
                drawCenter[1] - partInfo.getLocation()[0] / scale};
        paintPartImage(g, part, partDrawLoc, scale);
        paintPartInfo(g, part, partDrawLoc);
    }

    private void paintPartImage(Graphics g, VehiclePart part,
                                double[] partDrawLoc, double scale) {
        int[]
                bufferedImageSize = new int[] {
                        (int) (part.getDiameter() / scale),
                        (int) (part.getLength() / scale)},
                bufferedImageCenter = new int[] {
                        bufferedImageSize[0] / 2,
                        bufferedImageSize[1] / 2},
                bufferedImageLocation = new int[] {
                        (int) partDrawLoc[0] - bufferedImageSize[0] / 2,
                        (int) partDrawLoc[1] - bufferedImageSize[1] / 2};

        BufferedImage image = new BufferedImage(
                bufferedImageSize[0], bufferedImageSize[1],
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.transform(getTransform(angle, bufferedImageCenter));

        g2.setColor(PART_COLOR);
        g2.fillRect(
                0, 0,
                bufferedImageSize[0], bufferedImageSize[1]);
        g2.setColor(PART_BORDER_COLOR);
        g2.drawRect(
                0, 0,
                bufferedImageSize[0], bufferedImageSize[1]);

        g.drawImage(image, bufferedImageLocation[0], bufferedImageLocation[1], imageObserver);
    }

    private void paintPartInfo(Graphics g, VehiclePart part, double[] partDrawLoc) {
        //part title
        g.setColor(PART_TEXT_COLOR);
        g.setFont(PART_TITLE_FONT);
        g.drawString(
                part.getName(),
                (int) partDrawLoc[0], (int) partDrawLoc[1] - 5);

        //part details
        g.setFont(PART_DETAILS_FONT);
        g.drawString(
                "Mass: " + String.format("%." + PART_MASS_DECIMAL_DIGITS + "f", part.getMass()) + " kg",
                (int) partDrawLoc[0], (int) partDrawLoc[1] + 15);
        g.drawString(
                "Length: " + String.format("%." + PART_LENGTH_DECIMAL_DIGITS + "f", part.getLength()) + " m",
                (int) partDrawLoc[0], (int) partDrawLoc[1] + 30);
    }

    //for rotation
    private AffineTransform getTransform(double angle, int[] center) {
        return new AffineTransform() {{
            translate(center[0], center[1]);
            rotate(Math.toRadians(angle));
            translate(-center[0], -center[1]);
        }};
    }

    private void paintVehicle(Graphics2D g2, int[] drawSize, Vehicle vehicle, double scale) {
        int[] drawCenter = new int[] {drawSize[0] / 2, drawSize[1] / 2};
        List<VehiclePart> parts = vehicle.getParts();
        for (int i = 0; i < parts.size(); i++) {
            VehiclePart part = parts.get(i);
            double[]
                    scaledPartSize = new double[] {
                            part.getDiameter() / scale,
                            part.getLength() / scale},
                    partDrawLoc = new double[] {
                            drawCenter[0] - scaledPartSize[0] / 2 - vehicle.getPartInfo(part).getLocation()[1] / scale,
                            drawCenter[1] - scaledPartSize[1] / 2 - vehicle.getPartInfo(part).getLocation()[0] / scale};
            paintVehiclePart(g2, part, partDrawLoc, scaledPartSize);
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
        int[] textOffset = new int[]{-35, 5};

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