package draw;

import javax.swing.*;
import java.awt.*;

//класс описывает панель для рисования линий

public class DrawPanel extends JPanel
{
    public Color currentColor = Color.BLACK;
   
    @Override
    protected void paintComponent(Graphics g) //вызывается при вызове repaint()
    {
	super.paintComponent(g);     
    }
}