import java.awt.*;

public class FCircle extends Canvas{
   
    protected int diam;
    protected Color color;
    
    public FCircle(int diam, Color color)
    {
        this.diam = diam;
        this.color = color;
    }
    
    public FCircle( )
    {
        diam = 100;
        color = Color.getHSBColor(70,130,180);
    }
    
    public void setDiam(int diam)
    {
        this.diam = diam;
        repaint(); 
    }
    
    public void setColor(Color color)
    {
        //super.setForeground(color);
        this.color = color;
        repaint(); 
    }
    
    @Override
    public int getWidth( )
    {
        return diam;
    }
    
    public Color getColor( )
    {
        return color;
    }

    @Override
    public Dimension getPreferredSize() 
    {
        return new Dimension(diam,diam);
    }
    
    @Override
    public Dimension getMinimumSize() 
    {
        return getPreferredSize(); 
    }
    public Dimension getMaximumSize() 
    {
        return new Dimension(1500, 1500); 
    }
    @Override
    public void paint( Graphics g )
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillOval(0, 0, diam, diam);
    }
}