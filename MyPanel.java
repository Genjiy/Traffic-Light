package com.porsche.control.module;
 
import com.porsche.control.type.Lamp;
import com.porsche.control.type.Light;
import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
 
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	@Setter
	@Getter
	private Light l1,l2,l3,l4,l5,l6,l7,l8,l9;      //十二个灯
	@Setter
	@Getter
	private Lamp lamp1,lamp2,lamp3;                      //3组信号灯
	public static int isLight = 0;                                //实现进程循环的辅助变量
	private static ArrayList<Light> lightList = new ArrayList<>();   //使用list方便遍历每个灯
	public MyPanel() {
		// TODO Auto-generated constructor stub
		this.setSize(1000, 1000);
		l4= addList(212, 316, false);
		l5= addList(242, 316, false);
		l6= addList(272, 316, true);
		l9= addList(316, 116, false);
		l8= addList(316, 146, false);
		l7= addList(316, 176, false);
		l1= addList(70, 212, false);
		l2= addList(70, 242, false);
		l3= addList(70, 272, true);
		
		lamp1=new Lamp(l1, l9, 2000, false, 0);
		lamp2=new Lamp(l7, l8, 2000, false, 1);
		lamp3=new Lamp(l5, l4, 2000, false, 2);
		
		Thread t1=new Thread(lamp1);            //创建并启动线程
		Thread t2=new Thread(lamp2);
		Thread t3=new Thread(lamp3);
		t1.start();
		t2.start();
		t3.start();
	}

	private Light addList(int x, int y, boolean sta) {
		Light a = new Light(x,y,sta);
		lightList.add(a);
		return a;
	}

	public void LampChange() {
		isLight =(isLight +1)%3;
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.darkGray);        //画路
		g.fillRect(0, 100, 600, 5);
		g.fillRect(0, 300, 200, 5);
		g.fillRect(400, 300, 200, 5);
		g.fillRect(200, 300, 5, 200);
		g.fillRect(400, 300, 5, 200);
		g.setColor(Color.gray);
		g.fillRect(0, 200, 600, 2);
		g.fillRect(300, 300, 2, 400);
		g.setColor(Color.blue);
		
		g.setColor(Color.black);         //画信号灯板
		g.fillRect(202,306, 100, 40);
		g.fillRect(306,106, 40, 100);
		g.fillRect(60,202,40, 100);
		Light temp;

		for (Light aLightList : lightList) {       //画灯
			temp = aLightList;
			if (temp.isStatus())
				g.setColor(Color.green);
			else
				g.setColor(Color.RED);
			g.fillOval(temp.getX(), temp.getY(), 20, 20);
		}
	}
}