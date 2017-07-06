package com.aowin.frame;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.aowin.Database.Database;

public class LogView extends Thread{
	MainView mv;
	JPanel panel13,panel2,panel1;
	Container con;
	JFrame frame;
	JTextField tUser;
	JPasswordField password;
	JMenu menu1;


	
	public void run(){
		frame=new JFrame("登录");
		con=frame.getContentPane();
		con.setLayout(new FlowLayout());
		JLabel lUser=new JLabel("用户名：");
		tUser=new JTextField(12);
		JPanel panel11=new JPanel();
		panel11.add(lUser);
		panel11.add(tUser);
		con.add(panel11);
		JPanel panel12=new JPanel();
		JLabel lPassword=new JLabel("密   码：");
		password=new JPasswordField(12);
		panel12.add(lPassword);
		panel12.add(password);
		con.add(panel12);
		panel13=new JPanel();
		JButton bAdmin=new JButton("登录");
		JButton bTravel=new JButton("游客登录");
		panel2=new JPanel();
		panel2.add(bAdmin);
		panel2.add(bTravel);
		con.add(panel2);
		
		
		ActionListener lal=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd=e.getActionCommand();
				mv=getMV();
				
				switch(cmd){
				case "登录":{
			
					String userName=tUser.getText();
					String truePass=Database.queryPassword(userName);
					String password1=new String(password.getPassword());
					boolean hasName=Database.queryName(userName);
					
					if(hasName==false){
						tUser.setText("");
						JOptionPane.showMessageDialog(null, "用户名错误！");
					}else{
						if(!truePass.equals(password1)){
							password.setText("");
							JOptionPane.showMessageDialog(null, "密码错误！");
						}else{
							    frame.setVisible(false);	
								mv=new MainView("管理员",userName);
								mv.getFrame().setVisible(true);
								menu1=mv.getMenu1();
								if(menu1.getItemCount()==3){
									menu1.add(mv.getMAdd());
									menu1.add(mv.getMDelete());
									menu1.add(mv.getMUpdate());
									menu1.add(mv.getMImport());
									menu1.add(mv.getMExport());
								}
								mv.getCon().remove(mv.getPanel1());
								mv.getModel().setRowCount(0);
						}
						}
				
					break;
				}
				case "游客登录":{
					frame.setVisible(false);
					mv=new MainView("用户","");
					menu1=mv.getMenu1();
					if(menu1.getItemCount()==8){
						menu1.remove(mv.getMAdd());
						menu1.remove(mv.getMDelete());
						menu1.remove(mv.getMUpdate());
						menu1.remove(mv.getMImport());
						menu1.remove(mv.getMExport());
					}
						
					mv.getFrame().setVisible(true);
					mv.getCon().remove(mv.getPanel3());
					break;
				}
				}
			}
			
		};
	
		bAdmin.addActionListener(lal);
		bTravel.addActionListener(lal);
		
		frame.setLocation(200, 300);
		frame.setSize(280, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public Container getCon(){
		return con;
	}
	public JPanel getPanel13(){
		return panel13;
	}
	public MainView getMV(){
		return mv;
	}
	public JFrame getFrame(){
		return frame;
	}
	public JTextField getTUser(){
		return tUser;
	}
	public JPasswordField getPasswordField(){
		return password;
	}
	public JPanel getPanel2(){
		return panel2;
	}
	public JPanel getPanel1(){
		return panel1;
	}


}
