package vehicleGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

class RotationTestPainter implements PainterInterface {
    private final ImageObserver imageObserver;

    RotationTestPainter(ImageObserver imageObserver) {
        this.imageObserver = imageObserver;
    }

    @Override
    public void paint(Graphics g) {
        int[]
                location = new int[] {200, 200},
                size = new int[] {200, 200};
        //drawRectangles(g, location, size, new int [] {200, 100});
        drawRotatedCircles(g, location, size);
    }

    private void drawRectangles(Graphics g, int[] location, int[] size, int[] rectSize) {
        int[] rectLoc = new int[] {100, 100};
        String text = "A rotated text";
        BufferedImage image = new BufferedImage(size[0], size[1], BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        g2.setColor(Color.cyan);
        g2.fillRect(rectLoc[0], rectLoc[1], rectSize[0], rectSize[1]);
        g2.setColor(Color.magenta);
        g2.drawString(text, rectLoc[0] + rectSize[0] / 2, rectLoc[1] + 20);

        g2.rotate(Math.toRadians(20));

        g2.setColor(Color.magenta);
        g2.fillRect(rectLoc[0], rectLoc[1], rectSize[0], rectSize[1]);
        g2.setColor(Color.cyan);
        g2.drawString("text", rectLoc[0] + rectSize[0] / 2, rectLoc[1] + 20);

        g.drawImage(image, location[0], location[1], imageObserver);
    }

    private void drawRotatedCircles(Graphics g, int[] location, int[] size) {
        int maxSize = Math.max(size[0], size[1]);
        BufferedImage image = new BufferedImage(maxSize * 2, maxSize * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        int radius = 100;
        int[]
                circleLoc = new int[] {0, 0},
                center = new int[] {
                        circleLoc[0] + radius,
                        circleLoc[1] + radius};

        //circle 1
        drawSingleCircle(g2, center, radius, Color.cyan);

        //circle 2
        double angle = 30;

        g2.translate(center[0], center[1]);
        g2.rotate(Math.toRadians(angle));
        g2.translate(-center[0], -center[1]);

        drawSingleCircle(g2, center, radius, Color.magenta);

        //resets angle transform
        g2.translate(center[0], center[1]);
        g2.rotate(Math.toRadians(-angle));
        g2.translate(-center[0], -center[1]);

        //circle 3
        angle = 70;

        g2.translate(center[0], center[1]);
        g2.rotate(Math.toRadians(angle));
        g2.translate(-center[0], -center[1]);

        drawSingleCircle(g2, center, radius, Color.yellow);

        g.drawImage(image, location[0], location[1], imageObserver);
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