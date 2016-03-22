package SnakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener{

	/**
	 * 
	 */
	//Че это такое я не знаю.
	private static final long serialVersionUID = 1L;
		
	static final int Size = 32;
	static final int Width = 640;
	static final int Heigth = 480;
	//Скорость обновления таймера
	static final int Speed = 200;
	//Создаем класс SnakeBody
	Snake s = new Snake(10, 5);
	//Таймер
	Timer t = new Timer(Speed, this);

	//Конструктор класса Main
	public Game(){
		t.start();
		addKeyListener(new Keyboard());
		setFocusable(true);
	}
	
	//Главная форма на которой создается фрейм(Форма)
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setSize(Width, Heigth);
		f.setTitle("Моя ЗЗЗЗЗЗЗЗМЕЙКА");
		f.setLocationRelativeTo(null);
		f.add(new Game());
		f.setVisible(true);
	}

	//Процедура отрисовки чего либо (я так понимаю она фактически стандартная)
	public void paint(Graphics g){
		g.setColor(c(0, 100 ,0));
		g.fillRect(0, 0, Width, Heigth);
		
		if(s.EndGame)
	     GameOver(g);
		
		g.setColor(c(255, 0, 0));
		g.drawOval(s.a.AppleX*Size,s.a.AppleY*Size,Size,Size);
		for(int i = 0; i < s.SnakeLength; i ++)
		{
			g.setColor(c(0, 255, 0));
			g.drawRoundRect(s.SnakeP[0][i]*Size, s.SnakeP[1][i]*Size, Size, Size, 5, 5);
		}
	}
	
	//Функция которая возврощает цвет по заданым RGB
	public Color c(int Red, int Green, int Blue){
		return new Color(Red, Green, Blue);
	}

	//Какая то конструкция от таймера 
	@Override
	public void actionPerformed(ActionEvent e) {
		s.Move();
		
		repaint();
	}
	
	//Работа с клавиатурой
	private class Keyboard extends KeyAdapter{
		public void keyPressed(KeyEvent k){
			int Key = k.getKeyCode();
			if (Key != 0 && s.EndGame){
				s.StartNewGame();
				s.a.SetNewPosition();
	            t.start();
	            }
			if((Key == KeyEvent.VK_RIGHT) && s.moveto != 2) s.moveto = 0;
			if((Key == KeyEvent.VK_DOWN) && s.moveto != 3) s.moveto = 1;
	    	if((Key == KeyEvent.VK_LEFT) && s.moveto != 0) s.moveto = 2;
	    	if((Key == KeyEvent.VK_UP) && s.moveto != 1) s.moveto = 3;
	    	}
		}
	
	private void GameOver(Graphics g){
		t.stop();
		
		s.a.AppleX = -1;
		s.a.AppleY = -1;
		
		for(int i = 0; i < s.SnakeLength; i ++){
			s.SnakeP[0][i] = -1;
			s.SnakeP[1][i] = -1;
		}
		
		g.setColor(c(255, 0, 0));
		Font font = new Font("Tahoma", Font.BOLD|Font.ITALIC, 40);
		g.setFont(font);
		g.drawString("Game Over", 200, 220);
	}
	}
