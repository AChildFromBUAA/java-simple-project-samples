package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Paddle implements Runnable {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private JFrame frame;
	
	public Paddle(JFrame frame) {
		this.frame = frame;
		setY(20);
		setWidth(40);
		setHeight(10);
		x = (frame.getWidth() - width)/2;
		color = Color.green;
	}

	public void drawPaddle(Graphics g) {
		//System.out.print(x);
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
