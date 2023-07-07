package vehicleGraphics;

import java.awt.Dimension;
import java.awt.Graphics;

//
class BorderPainter implements PainterInterface {
    private final VehiclePanelData panelData;
    private final Dimension size;

    //
    protected BorderPainter(VehiclePanelData panelData, Dimension size) {
        this.panelData = panelData;
        this.size = size;
    }

    //
    @Override
    public void paint(Graphics g, int[] drawLocation) {
        g.setColor(panelData.getBorderColor());
        int
                offset = panelData.getBorderOffset(),
                x = size.width,
                y = size.height;
        g.drawRect(offset, offset, Math.max(0, x - 2 * offset), Math.max(0, y - 2 * offset));
    }
}