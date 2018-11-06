package com.porsche.control.module;
 
import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
 
import javax.swing.JPanel;
 
public class WelcomePanel extends JPanel implements Runnable{   //开始界面
	private static final long serialVersionUID = 1L;
	@Setter
	@Getter
	private int info = 0;
	@Setter
	@Getter
	private Boolean isLive=true;
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.red);
		g.setFont(new Font("微软雅黑", Font.BOLD, 30));
		if (info % 2 == 0) {
			g.drawString("多线程红绿灯模拟", 80, 150);
		}
	}
 
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			info++;
			this.repaint();
			if (!isLive) {
				break;
			}
		}
	}
	
}