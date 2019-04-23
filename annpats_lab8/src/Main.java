import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;


public class Main extends Applet{
	
	public void paint(Graphics g)
	{
		FCircle fl = new FillCircle(50, Color.blue);
		fl.paint(g);
	}


}