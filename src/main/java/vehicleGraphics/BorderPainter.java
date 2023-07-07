package vehicleGraphics;

import java.awt.Dimension;
import java.awt.Graphics;

//
class BorderPainter implements PainterInterface {
    private final VehiclePanelData panelData;

    //
    protected BorderPainter(VehiclePanelData panelData) {
        this.panelData = panelData;
    }

    //
    @Override
    public void paint(Graphics g, int[] drawLocation) {
        Dimension size = panelData.getPanelSize();
        int
                offset = panelData.getBorderOffset(),
                x = size.width,
                y = size.height;
        g.setColor(panelData.getBorderColor());
        g.drawRect(offset, offset, Math.max(0, x - 2 * offset), Math.max(0, y - 2 * offset));
    }
}