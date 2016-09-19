package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Ball implements Runnable {
	
	private int x;
	private int y;
	private int r;
	private Color color;
	private double speed;
	private int angle;
	private boolean isRun;
	private JFrame frame;
	private final double PI = 3.1415926;
	
	public Ball(JFrame frame) {
		this.frame = frame;
		setX(frame.getWidth()/2);
		setY(40);
		setR(10);
		setAngle(0);
		setSpeed(10);
		color = Color.red;
		setRun(false);
	}
	
	
	public void drawBall(Graphics g) {
		x += speed*Math.sin(angle*PI/180);
		y += speed*Math.cos(angle*PI/180);
		System.out.println(x+" "+y+" "+ angle);
		g.setColor(color);
		g.fillArc(x, y, r, r, 0, 360);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}


	public int getAngle() {
		return angle;
	}


	public void setAngle(int angle) {
		this.angle = angle;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public boolean isRun() {
		return isRun;
	}


	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getR() {
		return r;
	}


	public void setR(int r) {
		this.r = r;
	}

}
