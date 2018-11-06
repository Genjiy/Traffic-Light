package com.porsche.control;
 
import com.porsche.control.module.MyPanel;
import com.porsche.control.module.Settings;
import com.porsche.control.module.WelcomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CoreFunction extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static MyPanel panel = new MyPanel();;

	private JMenuItem jmi1, jmi2,jmi4;
	private WelcomePanel sp;
	
	private CoreFunction(){
		this.setTitle("traffic lamp");
		this.setSize(420,450);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JMenuBar jmb = new JMenuBar();

		JMenu jm1 = new JMenu("控制");
		JMenu jm2 = new JMenu("设置");
		
		jmi1=new JMenuItem("开始模拟");
		jmi1.addActionListener(this);
		jmi2=new JMenuItem("退出模拟");
		jmi2.addActionListener(this);

		jmi4=new JMenuItem("更改绿灯时间");
		jmi4.addActionListener(this);
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm2.add(jmi4);
		jmb.add(jm1);
		jmb.add(jm2);
		
		this.setJMenuBar(jmb);
		
		sp=new WelcomePanel();
		Thread t=new Thread(sp);
		t.start();
		this.setContentPane(sp);
		
		this.setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jmi1) {
			sp.setIsLive(false);
			this.remove(sp);
			this.setContentPane(panel);
			this.setSize(1000,1000);
			this.setVisible(true);
		}else if(e.getSource()==jmi2) {
			System.exit(0);
		}else if (e.getSource() == jmi4) {
			if(CoreFunction.panel==null)
    			JOptionPane.showMessageDialog(this, "请开始模拟再进行设置", "错误", JOptionPane.INFORMATION_MESSAGE);
			else new Settings();
			}
		}
	public static void main(String[] args) {
		new CoreFunction();
	}
}