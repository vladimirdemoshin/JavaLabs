package secondlab;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class SecondLab{

    private static int currentFigure = 0; // 0 - изменяем размер овала, 1 - прямоугольника
    private Panel pane = new Panel(); //панель,на которой будем рисовать
    private JButton save = new JButton("Сохранить графический ресурс");
    private JButton minus = new JButton("-");
    private JButton plus = new JButton("+");
    private JMenuBar menu = new JMenuBar(); //меню наверху формы с двумя кнопками
    private JMenu rectangle = new JMenu();
    private JMenu ellipse = new JMenu();
  
    public SecondLab()
    {
        JFrame mf = new JFrame(); //создаем форму, на которой будут расположены все элементы
	mf.setLayout(new BorderLayout());
	mf.setSize(1000, 500);
       
                
        pane.setBorder(BorderFactory.createLineBorder(Color.RED));
        pane.setBackground(Color.WHITE);
                
	mf.add(pane, BorderLayout.CENTER); //добавляем на форму элементы
        mf.add(minus, BorderLayout.WEST);
        mf.add(plus, BorderLayout.EAST);
        mf.add(save, BorderLayout.SOUTH);
                
        rectangle.setText("Выбрать прямоугольник");
        menu.add(rectangle);
        ellipse.setText("Выбрать овал (выбрано)");
        menu.add(ellipse);
        mf.add(menu, BorderLayout.NORTH);
                
        pane.setCoordinates(10, 50, 100, 50, 550, 50, 100, 50); //сразу отрисовываем две фигуры
	pane.repaint();
                
        //обработчики кликов на кнопки
        
	plus.addActionListener(new ActionListener() //увеличиваем текущую выбранную фигуру до определенных размеров
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(SecondLab.currentFigure == 0) 
                {
                    if(Panel.ellipseWidth < 300)
                    {
                        Panel.ellipseWidth += 10;
                        Panel.ellipseHeight += 10;
                    }
                }
                else
                {
                    if(Panel.rectangleWidth < 300)
                    {
                        Panel.rectangleWidth += 10;
                        Panel.rectangleHeight += 10;
                    }
                }
                pane.repaint();                            
	    }
	});
        
        minus.addActionListener(new ActionListener() //уменьшаем текущую фигуру до определенных размеров
        {
	    public void actionPerformed(ActionEvent ae)
            {
                if(SecondLab.currentFigure==0) 
                {
                    if(Panel.ellipseWidth>100)
                    {
                        Panel.ellipseWidth -= 10;
                        Panel.ellipseHeight -= 10;
                    }
                }
                else
                {
                    if(Panel.rectangleWidth>100)
                    {
                        Panel.rectangleWidth -= 10;
                        Panel.rectangleHeight -= 10;
                    }
                }
                pane.repaint();   		
            }
        });
                
        save.addActionListener(new ActionListener() //сохраняем текущий рисунок на панели в файл
        {
	    public void actionPerformed(ActionEvent ae)
            {
                saveImage(pane);
            }
        });
                
        ellipse.addMouseListener(new MouseAdapter() //меняем текущую выбранную фигуру на овал
        {
            public void mouseClicked(MouseEvent evt)
            {
                currentFigure = 0;
                rectangle.setText("Выбрать прямоугольник");
                ellipse.setText("Выбрать овал (выбрано)");                    
            }
        });
                
        rectangle.addMouseListener(new MouseAdapter() //меняем текущую выбранную фигуру на прямоугольник
        {
            public void mouseClicked(MouseEvent evt)
            {
                currentFigure = 1;
                rectangle.setText("Выбрать прямоугольник (выбрано)");
                ellipse.setText("Выбрать овал");
            }
        });
        
        mf.addWindowListener(new WindowAdapter()
        {
             public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
       
        mf.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){ 
			public void run(){
				new SecondLab();
			}
		});
    }
    
    public void saveImage(Panel panel) //функция сохранения в файл рисунок с панели
    {
        BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        panel.paint(img.getGraphics());
        try 
        {
           ImageIO.write(img, "png", new File("Screen.png"));
           System.out.println("Графический ресурс сохранен в файле Screen.png");
        } 
        catch (Exception e) 
        {
            System.out.println("Ресурс не сохранен" + e.getMessage());
        }
    }
}

class Panel extends JPanel{ //класс панели

    //координаты отрисованных фигур 
    
	public static int ellipseX;
	public static int ellipseY;
	public static int ellipseWidth;
	public static int ellipseHeight;
        
        public static int rectangleX;
	public static int rectangleY;
	public static int rectangleWidth;
	public static int rectangleHeight;
	
	public static void setCoordinates(int eX, int eY, int eW, int eH, int rX, int rY, int rW, int rH)
        {
		ellipseX = eX;
	        ellipseY = eY;
	        ellipseWidth = eW;
                ellipseHeight = eH;
                
                rectangleX = rX;
	        rectangleY = rY;
	        rectangleWidth = rW;
                rectangleHeight = rH;
	}
	
	public Panel()
        {
           
	}
        
        @Override
	protected void paintComponent(Graphics g) // при вызове метода repaint() вызывается эта функция
        {
		super.paintComponent(g); 
                g.fillOval(ellipseX, ellipseY, ellipseWidth, ellipseHeight);
                g.fillRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
	}
}