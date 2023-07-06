package vehicleGraphics;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

//
class BorderPainter implements PainterInterface {
    private final Color color;
    private final int offset;

    //
    protected BorderPainter(Color color, int offset) {
        this.color = color;
        this.offset = offset;
    }

    //
    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        Dimension size = VehiclePanelData.getOptimalSize();
        int
                x = size.width,
                y = size.height;
        g.drawRect(offset, offset, Math.max(0, x - 2 * offset), Math.max(0, y - 2 * offset));
    }
}