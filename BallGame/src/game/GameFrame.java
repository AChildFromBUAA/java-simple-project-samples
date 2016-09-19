package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements Runnable, MouseListener, MouseMotionListener {
	
	private Ball ball;
	private Paddle paddle;
	private Thread thread;
	private int score;
	private boolean isClicked;
	public final static double speedRate = 1.02;
	public final static int initX = 100;
	public final static int initY = 100;
	public final static int initWidth = 500;
	public final static int initHeight = 400;
	
	public GameFrame() throws HeadlessException {
		score = 0;
		isClicked = false;
		this.setBounds(initX, initY, initWidth, initHeight);
		this.setBackground(Color.white);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		ball = new Ball(this);
		paddle = new Paddle(this);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public GameFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public GameFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public GameFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	
	
	/*public void paintComponents(Graphics g) {
		System.out.print("paint");
		super.paintComponents(g);
		ball.drawBall(g);
		paddle.drawPaddle(g);
		check();
	}*/
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paddle.drawPaddle(g);
		ball.drawBall(g);
		check();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		paddle.setX(e.getX());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(isClicked)  {
			isClicked = false;
			thread.stop();
		} else {
			Random rand = new Random();
			ball.setAngle(rand.nextInt(180));
			thread = new Thread(this);
			ball.setRun(true);
			thread.start();
			isClicked = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while(ball.isRun()) {
			this.repaint();
			try {
				Thread.currentThread().sleep(120);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	private void check() {
		int width = this.getWidth();
		int height = this.getHeight();
		int ballx = ball.getX();
		int bally = ball.getY();
		int ballr = ball.getR();
		int paddlex = paddle.getX();
		int paddley = paddle.getY();
		int paddleWidth = paddle.getWidth();
		int paddleHeight = paddle.getHeight();
		int angle = ball.getAngle();
		if(ballx >= paddlex && (ballx <= paddlex+paddleWidth) && (bally - ballr <= paddleHeight + paddley)) {
			ball.setAngle(180-angle);
			double speed = ball.getSpeed();
			score += 10*speed;
			ball.setSpeed(speed*speedRate);
		} else if(ballx > 0 && ballx < width && bally + ballr >= height) {
			ball.setAngle(180-angle);
		} else if(bally > 0 && bally < height && ballx >= width) {
			ball.setAngle(360 - angle);
		} else if(bally > 0 && bally < height && ballx <= 0) {
			ball.setAngle(angle -270);
		} else if(ballx > 0 && ballx < width && bally - ballr <= 0) {
			ball.setRun(false);
		}
	}

}
