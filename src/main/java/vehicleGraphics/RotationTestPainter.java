package vehicleGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

//
class RotationTestPainter implements PainterInterface {
    private final ImageObserver imageObserver;

    //
    protected RotationTestPainter(ImageObserver imageObserver) {
        this.imageObserver = imageObserver;
    }

    //
    @Override
    public void paint(Graphics g) {
        int[] location = new int[] {200, 200};
        int radius = 100;
        drawRotatedCircles(g, location, radius);
    }

    private void drawRotatedCircles(Graphics g, int[] location, int radius) {
        BufferedImage image = new BufferedImage(
                radius * 2,
                radius * 2,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        int[] center = new int[] {radius, radius};

        drawRotatedCircle(g2, center, radius, 0, Color.cyan);
        drawRotatedCircle(g2, center, radius, 30, Color.magenta);
        drawRotatedCircle(g2, center, radius, 70, Color.yellow);

        g.drawImage(image, location[0], location[1], imageObserver);
    }

    private void drawRotatedCircle(Graphics2D g2, int[] center, int radius, double angle, Color color) {
        g2.transform(getTransform(angle, center));
        drawSingleCircle(g2, center, radius, color);
        g2.transform(getTransform(-angle, center)); //resets transform
    }

    private AffineTransform getTransform(double angle, int[] center) {
        return new AffineTransform() {{
            translate(center[0], center[1]);
            rotate(Math.toRadians(angle));
            translate(-center[0], -center[1]);
        }};
    }

    private void drawSingleCircle(Graphics2D g2, int[] center, int radius, Color color) {
        g2.setColor(color);
        g2.drawOval(
                center[0] - radius,
                center[1] - radius,
                radius * 2,
                radius * 2);
        g2.drawLine(
                center[0],
                center[1],
                center[0] + radius,
                center[1]);
    }
}