package com.porsche.control.type;
import com.porsche.control.CoreFunction;
import com.porsche.control.module.MyPanel;
import lombok.Data;

@Data
public class Lamp implements Runnable {
	private Light now, opposite;            //同时亮起或熄灭的灯
	private int greenTime;                  //绿灯亮的时间
	private int name;                       //编组
	private boolean status;                 //状态
	private static final Object lock = new Object();
 
	public Lamp(Light l1, Light l2, int gt, boolean st, int name) {
		now = l1;
		opposite = l2;
		status = st;
		greenTime = gt;
		this.name = name;
		lightStatues();
	}
 
	private void change() {
		this.status = !(this.status);
		lightStatues();
	}

	private void lightStatues() {
		now.setStatus(status);
		opposite.setStatus(status);
	}
 
	public void run() {
		while (true) {
			synchronized (lock) {                //使用synchronized实现进程间的互斥
				if (name == MyPanel.isLight) {           //使用辅助变量实现进程按顺序循环
					change();
					CoreFunction.panel.repaint();
					try {
						Thread.sleep(greenTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					lock.notifyAll();                //唤醒其他进程
					change();
					CoreFunction.panel.LampChange();
					CoreFunction.panel.repaint();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					try {
						lock.wait();           //挂起进程
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}