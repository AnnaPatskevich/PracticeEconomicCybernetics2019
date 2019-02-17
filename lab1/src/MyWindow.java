import java.applet.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class MyWindow extends Applet implements Runnable{

    private volatile boolean fSuspend = false;
    public synchronized boolean getFSuspend() {
        return fSuspend;
    }
    public synchronized void setFSuspend( boolean value ) {
        fSuspend = value;
    }
    public void suspend() { setFSuspend( true ); }
    public void resume() { setFSuspend( false ); }
    public boolean isSuspended() { return getFSuspend(); }

    int xWindowSize = 700;
    int yWindowSize = 700;

    int shapeWidth = 300, shapeHight = 400, deg1 = 0, deg2 = 0;
    int xShape = xWindowSize/2 - shapeWidth/2;
    int yShape = yWindowSize/2 - shapeHight/2;
    int recBorderWidth = 5;
    int ovalBorderWidth = 5;
    Thread t = null;
    Color recColor, ovalColor, recBorderColor, ovalBorderColor;
    //boolean flagFirstPlay = true;

    AffineTransform transformsRec = AffineTransform.getRotateInstance(0);
    AffineTransform transformsOval = AffineTransform.getRotateInstance(0);

    public void start() {
        resume();
    }

    public void stop() {
        suspend();
    }

    public void destroy() {
        if ( t != null ) {
            t.interrupt();
            t = null;
        }
    }

    public Color getHtmlColor( String strRGB, Color def ) {
        if ( strRGB != null && strRGB.charAt(0)== '#' ) {
            try {
                return new Color(Integer.parseInt( strRGB.substring( 1 ), 16 ) );
            }
            catch ( NumberFormatException e ) {
                return def;
            }
        }
        return def;
    }

    public void init() {
        this.setSize(xWindowSize, yWindowSize);
        deg1 = deg2 = 0;
        setBackground(new Color( 255, 255, 255));
        recColor = getHtmlColor(getParameter( "RecColor" ), new Color(255, 222, 212));
        ovalColor = getHtmlColor(getParameter( "OvalColor" ),new Color( 251, 255, 157));
        recBorderColor = getHtmlColor(getParameter( "RecBorderColor" ), new Color(200, 200, 250));
        ovalBorderColor = getHtmlColor(getParameter( "OvalBorderColor" ),  new Color( 154, 255, 143));
        try {
            String str1 = getParameter("RecBorderWidth");
            if(str1 != null) {
                recBorderWidth = Integer.parseInt(str1);
            }
            String str2 = getParameter("OvalBorderWidth");
            if(str2 != null) {
                ovalBorderWidth = Integer.parseInt(str2);
            }
        }
        catch (Exception e) {
//	    		if(flagFirstPlay == true) {
//		    		JFrame frame = new JFrame("");
//		    		frame.setSize(480, 100);
//		    		frame.setLocation(100, 100);
//		    		frame.setVisible(true);
//		    		JLabel text = new JLabel("You insert incorrect data, and so the programm uses default data");
//		    		text.setFont(new Font ("TimesRoman", Font.BOLD, 14));
//		    		frame.add(text);
//		    		flagFirstPlay = false;
//	    		}
        }

        if ( t == null ) {
            setFSuspend( false );
            t = new Thread( this );
        }
        t.start();
    }

    void drawNext() {
        deg1+=5;
        if(deg1 == 360) {
            deg1 = 0;
        }
        transformsRec = AffineTransform.getRotateInstance(Math.PI*deg1/180, xWindowSize/2, yWindowSize/2);
        deg2-=10;
        if(deg2 == -360) {
            deg2 = 0;
        }
        transformsOval = AffineTransform.getRotateInstance(Math.PI*deg2/180, xWindowSize/2, yWindowSize/2);
        repaint();
    }

    public void paint(Graphics g){
        MyRectangle rect = new MyRectangle(xShape, yShape, shapeWidth, shapeHight, 20, recColor, recBorderColor);
        MyOval oval = new MyOval(xShape, yShape, shapeWidth, shapeHight, 10, ovalColor, ovalBorderColor);
        Image buffer1 = createImage(getWidth(), getHeight());
        Graphics2D gr1 = (Graphics2D)buffer1.getGraphics();
        gr1.transform(transformsRec);
        rect.paint(gr1);
        Graphics2D gr2 = (Graphics2D)buffer1.getGraphics();;
        gr2.transform(transformsOval);
        oval.paint(gr2);
        g.drawImage(buffer1, 0, 0, null);
    }

    public void run() {
        suspend();
        while ( true )
            try {
                Thread.sleep( 50 );
                if ( isSuspended()== false ) {
                    drawNext();
                }
            }
            catch ( Exception e ) {
                break;
            }
    }

}

