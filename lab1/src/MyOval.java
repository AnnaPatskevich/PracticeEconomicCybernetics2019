import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MyOval implements Shape{

    @Override
    public boolean contains(Point2D p) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(Rectangle2D r) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(double x, double y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Rectangle2D getBounds2D() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        // TODO Auto-generated method stub
        return false;
    }

    int borderWidth;
    Color borderColor;
    Color shapeColor;
    int x;
    int y;
    int width;
    int height;

    public MyOval(int X, int Y, int WIDTH, int HEIGHT, int BORDERWIDTH, Color BORDERCOLOR, Color SHAPECOLOR) {
        x = X;
        y = Y;
        width = WIDTH;
        height = HEIGHT;
        borderWidth = BORDERWIDTH;
        borderColor = BORDERCOLOR;
        shapeColor = SHAPECOLOR;
    }

    public void paint(Graphics2D g){
        g.setColor(shapeColor);
        g.fillOval(x, y, width, height);
        g.setStroke(new BasicStroke(borderWidth));
        g.setColor( borderColor);
        g.drawOval(x, y, width, height);
    }

}

