package draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class MainClass {
    
    //создаем элементы меню
    
    private JMenuBar mainMenu = new JMenuBar(); 
    
    private JMenu saveResourse = new JMenu("Сохранить графический ресурс");
    private JMenu chooseBlack = new JMenu("Чёрный (текущий цвет)");
    private JMenu chooseRed = new JMenu("Красный");
    private JMenu chooseGreen = new JMenu("Зелёный");
    private JMenu chooseBlue = new JMenu("Синий");
    private JMenu clearPanel = new JMenu("Очистить панель");
    
    public int x1, y1, x2, y2; //координаты для рисования прямой
    public Graphics g; //объект для рисования примитивов
    public boolean shiftIsPressed = false, startDrawLine = true; 
    
    private DrawPanel drawPanel = new DrawPanel(); //панель, на которой будет происходить рисование
    
    public MainClass()
    {
        JFrame mainFrame = new JFrame("Draw line"); //создаем форму, на которой будут находиться панель и меню
        
        //устанавливаем настройки формы
        
        mainFrame.setLayout(new BorderLayout()); 
	mainFrame.setSize(800, 600);
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //устанавливаем настройки панели для рисования
        
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        drawPanel.setBackground(Color.WHITE);
        
        //настраиваем элементы и добавляем их на форму
        
        mainFrame.add(drawPanel, BorderLayout.CENTER);
        
        chooseBlack.setForeground(Color.BLACK);
        chooseRed.setForeground(Color.RED);
        chooseGreen.setForeground(Color.GREEN);
        chooseBlue.setForeground(Color.BLUE);
        
        mainMenu.add(saveResourse);
        mainMenu.add(chooseBlack);
        mainMenu.add(chooseRed);
        mainMenu.add(chooseGreen);
        mainMenu.add(chooseBlue);
        mainMenu.add(clearPanel);
        
        mainFrame.add(mainMenu,BorderLayout.NORTH);
        
        //описываем обработчики событий нажатий на все кнопки меню
        
        saveResourse.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                saveGraphicalResource(drawPanel);
            }
        });
        
        clearPanel.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {    
                Graphics g = drawPanel.getGraphics();
                g.setColor(drawPanel.getBackground());
                g.fillRect(0,0,drawPanel.getWidth(),drawPanel.getHeight());
            }
        });
        
        chooseBlack.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                chooseBlack.setText("Чёрный (текущий цвет)");
                chooseRed.setText("Красный");
                chooseGreen.setText("Зелёный");
                chooseBlue.setText("Синий");
                
                drawPanel.currentColor = Color.BLACK;
            }
        });
        
        chooseRed.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                chooseBlack.setText("Чёрный");
                chooseRed.setText("Красный (текущий цвет)");
                chooseGreen.setText("Зелёный");
                chooseBlue.setText("Синий");
                drawPanel.currentColor = Color.RED;
            }
        });
        
        chooseGreen.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                chooseBlack.setText("Чёрный");
                chooseRed.setText("Красный");
                chooseGreen.setText("Зелёный (текущий цвет)");
                chooseBlue.setText("Синий");
                drawPanel.currentColor = Color.GREEN;
            }
        });
        
        chooseBlue.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                chooseBlack.setText("Чёрный");
                chooseRed.setText("Красный");
                chooseGreen.setText("Зелёный");
                chooseBlue.setText("Синий (текущий цвет)");
                drawPanel.currentColor = Color.BLUE;
            }
        });
        
        //создаём обработчики нажатия кнопки и её ведения по панели 
        
        drawPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent evt) //движение мыши
            {
                if(shiftIsPressed) // если зажата клавиша Shift, не меняем координату y
                {
                    x1 = evt.getX();
                    g.setColor(drawPanel.currentColor); 
                    g.drawLine(x1, y1, x2, y1);
                    x2 = x1;
                }
                else
                {
                    x1 = evt.getX();
                    y1 = evt.getY();
                    g.setColor(drawPanel.currentColor); 
                    g.drawLine(x1, y1, x2, y2);
                    x2 = x1;
                    y2 = y1;
                }
            }
        });
        
        drawPanel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent evt)
            {  
                if(shiftIsPressed) // если зажата клавиша Shift
                {
                    x1 = evt.getX();
                    if(startDrawLine == true) //если мы только начали рисовать, фиксируем y, а потом не меняем
                    {
                        y1 = evt.getY();
                        startDrawLine = false;
                    }
                }
                else
                {
                   x1 = evt.getX();
                   y1 = evt.getY();
                }     
                 g = drawPanel.getGraphics();
                 x2 = x1;
                 y2 = y1;
            }
        });
        
        //создаём обработчики нажатия на клавишу Shift
        mainFrame.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent evt) 
            {
                if(evt.getKeyCode() == KeyEvent.VK_SHIFT)
                {
                    shiftIsPressed = true;
                      
                }
            }
            
            @Override
            public void keyReleased(KeyEvent evt) 
            {
                if(evt.getKeyCode() == KeyEvent.VK_SHIFT)
                {
                    shiftIsPressed = false; 
                    startDrawLine = true;  
                }
            }
        });
        
        mainFrame.setVisible(true); //делаем форму видимой для пользователя
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable(){ 
			public void run(){
				new MainClass();
			}
		});
    }
    
    public void saveGraphicalResource(DrawPanel panel) //функция сохранения графического ресурса с панели (не работает)
    {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        panel.paint(image.getGraphics());
        try 
        {
           ImageIO.write(image, "jpg", new File("Resource.png"));
           System.out.println("Графический ресурс с панели сохранен в файл Resource.png");
        } 
        catch (Exception e) 
        {
            System.out.println("Ресурс не сохранен" + e.getMessage());
        }
    }
}
