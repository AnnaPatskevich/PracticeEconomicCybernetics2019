import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
public class Square implements Shape {
    private double X_0;
    private double Y_0;
    private double side;
    Square (double X, double Y, double s) {
        this.X_0 = X;
        this.Y_0 = Y;
        this.side = s;
    }
    @Override
    public Rectangle getBounds() { return new Rectangle((int) (X_0), (int) (Y_0), (int) (X_0 +  side), (int) (X_0 + side)); }
    @Override
    public Rectangle2D getBounds2D() {
        return new Rectangle2D.Double(X_0, Y_0, X_0 + side, X_0 + side);
    }
    @Override
    public boolean contains(Point2D p) {
        return contains(p.getX(), p.getY());
    }
    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return getBounds().intersects(x, y, w, h);
    }
    @Override
    public boolean intersects(Rectangle2D r) {
        return getBounds().intersects(r);
    }
    @Override
    public boolean contains(double x, double y, double w, double h) { return contains(x, y) && contains(x + w, y) && contains(x, y + h) && contains(x + w, y + h); }
    @Override
    public boolean contains(Rectangle2D r) {
        return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }
    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return new TriangleIterator(at);
    }
    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return getPathIterator(at);
    }
    @Override
    public boolean contains(double x, double y) {
        if (x > 0 || y > 0) return true;
        else return false;
    }
    class TriangleIterator implements PathIterator {
        int index = 0;
        boolean done = false;
        private AffineTransform at;
        public TriangleIterator(AffineTransform at) {
            this.at = at;
        }
        @Override
        public int getWindingRule() {
            return WIND_NON_ZERO;
        }
        @Override
        public boolean isDone() {
            return done;
        }
        @Override
        public void next() { index++; }
        @Override
        public int currentSegment(float[] coords) {
            if (index == 0) {
                coords[0] = (float) X_0;
                coords[1] = (float) (Y_0);
                if (at != null) at.transform(coords, 0, coords, 0, 1);
                return SEG_MOVETO;
            }
            if (index == 1) {
                coords[0] = (float) (X_0 + side);
                coords[1] = (float) (Y_0 );
            } else if (index == 2) {
                coords[0] = (float) (X_0 + side );
                coords[1] = (float) (Y_0 + side );
            } else  if (index == 3){
                coords[0] = (float) (X_0);
                coords[1] = (float) (Y_0 +side);
            } else {
                coords[0] = (float) X_0;
                coords[1] = (float) (Y_0);
                done = true;
            }
            if (at != null) at.transform(coords, 0, coords, 0, 1);
            return SEG_LINETO;
        }
        @Override
        public int currentSegment(double[] coords) {
            if (index == 0) {
                coords[0] = X_0;
                coords[1] = Y_0;
                if (at != null) at.transform(coords, 0, coords, 0, 1);
                return SEG_MOVETO;
            }
            if (index == 1) {
                coords[0] = (X_0 + side);
                coords[1] = (Y_0);
            } else if (index == 2) {
                coords[0] = (X_0 + side );
                coords[1] = (Y_0 + side );
            } else if (index == 3){
                coords[0] = (X_0);
                coords[1] = (Y_0 + side);
            } else {
                coords[0] = X_0;
                coords[1] = Y_0;
                done = true;
            }
            if (at != null) at.transform(coords, 0, coords, 0, 1);
            return SEG_LINETO;
        }
    }
}