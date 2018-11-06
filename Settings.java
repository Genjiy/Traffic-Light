package com.porsche.control.module;

import com.porsche.control.CoreFunction;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
 
public class Settings extends JFrame implements ActionListener {     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jText1, jText2, jText3;
    private JButton jb1,jb2;

	public Settings(){
		JLabel lab1 = new JLabel("主路直行");
		JLabel lab2 = new JLabel("主路左转");
		JLabel lab3 = new JLabel("支路左转：");

		int time1 = CoreFunction.panel.getLamp1().getGreenTime();
		int time2 = CoreFunction.panel.getLamp2().getGreenTime();
		int time3 = CoreFunction.panel.getLamp3().getGreenTime();

    	jText1 =new JTextField(String.valueOf(time1));
    	jText2 =new JTextField(String.valueOf(time2));
    	jText3 =new JTextField(String.valueOf(time3));
    	
    	jb1=new JButton("确定");
    	jb1.addActionListener(this);
    	jb2=new JButton("取消");
    	jb2.addActionListener(this);
    	
    	this.setLayout(new GridLayout(5,2,10,5));
    	this.add(lab1);
    	this.add(jText1);
    	this.add(lab2);
    	this.add(jText2);
    	this.add(lab3);
    	this.add(jText3);
    	this.add(jb1);
    	this.add(jb2);
    	
    	this.setLocationRelativeTo(null); 
    	this.setSize(400, 200);  
    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	this.setResizable(false);
        this.setVisible(true);  
    }
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==jb1) {
    		
    		if(jText1.getText().length()==0|| jText2.getText().length()==0|| jText3.getText().length()==0)
    			JOptionPane.showMessageDialog(this, "请输入完整数据！", "错误", JOptionPane.INFORMATION_MESSAGE);
    		else {
    			CoreFunction.panel.getLamp1().setGreenTime(Integer.parseInt(jText1.getText()));
    			CoreFunction.panel.getLamp2().setGreenTime(Integer.parseInt(jText2.getText()));
    			CoreFunction.panel.getLamp3().setGreenTime(Integer.parseInt(jText3.getText()));
    			this.dispose();
    		}
    	}else if(e.getSource()==jb2){
    		this.dispose();
    	}
    }
}  