package stopWatch;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;


public class StopWatch extends JFrame implements Runnable, ActionListener {
	
	private boolean isRun;
	private boolean isPause;
	private long startTime;
	private long pauseTime;
	private long endTime;
	
	private JPanel labelPanel;
	private JPanel buttonPanel;
	private JLabel timeLabel;
	private JButton startButton;
	private JButton pauseButton;
	private JButton continueButton;
	private JButton stopButton;
	
	private Thread thread;
	
	public final static int initX = 100;
	public final static int initY = 100;
	public final static int initWidth = 500;
	public final static int initHeight = 400;
	
	public StopWatch() {
		isRun = false;
		isPause = false;
		
		this.setTitle("秒表");
		this.setBounds(initX, initY, initWidth, initHeight);
		this.setLayout(new GridLayout(2,1));
		
		labelPanel = new JPanel();
		timeLabel = new JLabel("00:00:00:00");
		timeLabel.setFont(new Font("bold",1,45));
		labelPanel.add(timeLabel);
		this.add(labelPanel);
		
		buttonPanel = new JPanel();
		startButton = new JButton("开始");
		startButton.addActionListener(this);
		buttonPanel.add(startButton);
		pauseButton = new JButton("暂停");
		pauseButton.addActionListener(this);
		buttonPanel.add(pauseButton);
		continueButton = new JButton("继续");
		continueButton.addActionListener(this);
		buttonPanel.add(continueButton);
		stopButton = new JButton("停止");
		stopButton.addActionListener(this);
		buttonPanel.add(stopButton);
		this.add(buttonPanel);
		
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private String format(long t) {
		int h, m, s, ss;
		ss = (int) (t % 1000 - t % 10)/10;
		t/= 1000;
		s = (int) (t % 60);
		t/= 60;
		m = (int) (t % 60);
		t/= 60;
		h = (int) (t % 60);
		return String.format("%02d:%02d:%02d:%02d", h, m, s, ss);
	}
	
	@Override
	public void run() {
		while(isRun) {
			endTime = System.currentTimeMillis();
			if(!isPause) {
				String timeStr = format(endTime - startTime);
				timeLabel.setText(timeStr);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton && !isRun) {
			startTime = System.currentTimeMillis();
			isRun = true;
			thread = new Thread(this);
			thread.start();
		} else if(e.getSource() == pauseButton && !isPause) {
			pauseTime = System.currentTimeMillis();
			isPause = true;
		} else if(e.getSource() == continueButton && isPause) {
			isPause = false;
		} else if(e.getSource() == stopButton && isRun) {
			isRun = false;
		}
		
	}
}
