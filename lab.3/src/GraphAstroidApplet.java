import java.awt.*;
import java.awt.geom.Line2D;
import java.applet.*;

public class GraphAstroidApplet extends Applet {

    private static final long serialVersionUID = 1L;
    private int centerX;
    private int centerY;
    private final int paramA = 120;

    private Shape astroid;

    public GraphAstroidApplet() { }

    protected void update() {
        paint(getGraphics());
    }

    @Override
    public void init() {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        astroid = new Astroid(paramA, centerX, centerY);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(86, 24, 51));
        g2d.draw(new Line2D.Double(0, centerY, getWidth(), centerY));
        g2d.draw(new Line2D.Double(centerX, 0, centerX, getHeight()));

        g2d.setColor(new Color(0, 0 , 0));
        g2d.setStroke(new SloppyStroke(1));
        g2d.draw(astroid);
    }

}