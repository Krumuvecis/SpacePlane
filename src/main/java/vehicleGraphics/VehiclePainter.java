package vehicleGraphics;

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

    private final ImageObserver imageObserver;
    private final VehiclePanelData panelData;
    private final double angle; // in radians

    //
    protected VehiclePainter(ImageObserver imageObserver, VehiclePanelData panelData,
                             double angle) {
        this.imageObserver = imageObserver;
        this.panelData = panelData;
        this.angle = Math.toRadians(angle);
    }

    //
    @Override
    public final void paint(Graphics g, int[] drawLocation) {
        Dimension panelSize = panelData.getPanelSize();
        double[] drawCenter = new double[] {
                (double) panelSize.width / 2,
                (double) panelSize.height / 2};

        //vehicle & scale
        Vehicle vehicle = panelData.getVehicle();
        int maxDrawLength = Math.min(
                (int) (panelSize.width / Math.sin(angle)),
                (int) (panelSize.height / Math.cos(angle)));
        double scaleTestCoefficient = 1;
        //actual * scale = draw
        double scale = vehicle.getLength() / maxDrawLength * scaleTestCoefficient;

        //paint parts
        for (VehiclePart part : vehicle.getParts()) {
            VehiclePartLocationInfo partInfo = vehicle.getPartInfo(part);
            paintPart(
                    g, part,
                    getDrawLoc(partInfo.getLocation(), drawCenter, scale),
                    scale);
        }
    }

    private double[] getDrawLoc(double[] actualLoc, double[] drawCenter, double scale) {
        double[] relativeOrthogonalLoc = new double[] { //actual loc scaled to drawable for g2
                -1 * actualLoc[1] / scale,  //draw x = actual -y
                -1 * actualLoc[0] / scale}; //draw y = actual -x

        return new double[] { //use trigonometry here
                drawCenter[0]
                        + relativeOrthogonalLoc[0] * Math.cos(angle)
                        + relativeOrthogonalLoc[1] * Math.sin(angle),
                drawCenter[1]
                        + relativeOrthogonalLoc[1] * Math.cos(angle)
                        + relativeOrthogonalLoc[0] * Math.sin(angle)};
    }

    private void paintPart(Graphics g, VehiclePart part,
                           double[] partDrawLoc, double scale) {
        paintPartImage(g, part, partDrawLoc, scale);
        paintPartInfo(g, part, partDrawLoc);
    }

    private void paintPartImage(Graphics g, VehiclePart part,
                                double[] partDrawLoc, double scale) {
        double[] scaledPartSize = new double[] {
                part.getDiameter() / scale,
                part.getLength() / scale};
        int bufferedImageMaxSize = (int) Math.hypot(
                scaledPartSize[0],
                scaledPartSize[1]);

        //buffered image stuff
        int[]
                bufferedImageSize = new int[] {
                        bufferedImageMaxSize,
                        bufferedImageMaxSize},
                bufferedImageCenter = new int[] {
                        bufferedImageSize[0] / 2,
                        bufferedImageSize[1] / 2};

        //create image and apply transform
        BufferedImage image = new BufferedImage(
                bufferedImageSize[0], bufferedImageSize[1],
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.transform(getTransform(angle, bufferedImageCenter));

        //paint on image
        paintPartOnImage(g2, bufferedImageCenter, scale, part);

        //paint image
        int[] bufferedImageLocation = new int[] {
                (int) partDrawLoc[0] - bufferedImageSize[0] / 2,
                (int) partDrawLoc[1] - bufferedImageSize[1] / 2};
        g.drawImage(image, bufferedImageLocation[0], bufferedImageLocation[1], imageObserver);

        //test square
        g.setColor(Color.red);
        g.drawRect(
                bufferedImageLocation[0], bufferedImageLocation[1],
                bufferedImageSize[0], bufferedImageSize[1]);
    }

    private void paintPartOnImage(Graphics2D g2, int[] imageCenter, double scale, VehiclePart part) {
        double[] scaledPartSize = new double[] {
                part.getDiameter() / scale,
                part.getLength() / scale};
        g2.setColor(PART_COLOR);
        g2.fillRect(
                (int) (imageCenter[0] - scaledPartSize[0] / 2),
                (int) (imageCenter[1] - scaledPartSize[1] / 2),
                (int) scaledPartSize[0], (int) scaledPartSize[1]);
        g2.setColor(PART_BORDER_COLOR);
        g2.drawRect(
                (int) (imageCenter[0] - scaledPartSize[0] / 2),
                (int) (imageCenter[1] - scaledPartSize[1] / 2),
                (int) scaledPartSize[0], (int) scaledPartSize[1]);
    }

    private void paintPartInfo(Graphics g, VehiclePart part, double[] partDrawLoc) {
        int[] textOffset = new int[] {-35, 5};
        g.setColor(PART_TEXT_COLOR);

        //part title
        g.setFont(PART_TITLE_FONT);
        g.drawString(
                part.getName(),
                (int) partDrawLoc[0] + textOffset[0],
                (int) partDrawLoc[1] + textOffset[1]);

        //part details
        g.setFont(PART_DETAILS_FONT);
        String massString = "M: " + ValueConvertor.convertMassToTonnes(part.getMass()) + " t";
        g.drawString(massString,
                (int) partDrawLoc[0] + textOffset[0],
                (int) partDrawLoc[1] + textOffset[1] + 15);
        String sizeString = "L: " + ValueConvertor.roundLength(part.getLength()) + " m";
        g.drawString(sizeString,
                (int) partDrawLoc[0] + textOffset[0],
                (int) partDrawLoc[1] + textOffset[1] + 30);
    }

    //for rotation
    private AffineTransform getTransform(double angle, int[] center) {
        return new AffineTransform() {{
            translate(center[0], center[1]);
            rotate(angle); //already in radians
            translate(-center[0], -center[1]);
        }};
    }

    //
    static class ValueConvertor {
        private static final int
                PART_MASS_DECIMAL_DIGITS = 2,
                PART_LENGTH_DECIMAL_DIGITS = 1;

        //
        static double convertMassToTonnes(double mass) {
            return roundToDecimalDigits(mass / 1000, PART_MASS_DECIMAL_DIGITS);
        }

        //
        static double roundLength(double length) {
            return roundToDecimalDigits(length, PART_LENGTH_DECIMAL_DIGITS);
        }

        //
        static double roundToDecimalDigits(double value, int decimalDigits) {
            return ((int) (value * Math.pow(10, decimalDigits))) / Math.pow(10.0, decimalDigits);
        }
    }
}