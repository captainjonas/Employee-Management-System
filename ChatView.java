package com.aowin.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatView extends Thread{
	JTextField tSend;
	JFrame frameChat;
	JButton bSend;
	JTextArea are;
	Socket soc;
	BufferedReader br;
	String msg,name;
	

	public ChatView(String name){
		this.name=name;
	}
	

	public void run(){

	  
		try {
			soc=new Socket(InetAddress.getLocalHost(),9089);
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		System.out.println(soc);
		frameChat=new JFrame("Office Chat");
		Container con=frameChat.getContentPane();
		con.setLayout(new BorderLayout());
		are=new JTextArea(25,15);
		JScrollPane scroll=new JScrollPane(are);
		are.setLineWrap(true);
		con.add(are,BorderLayout.CENTER);
		
		JPanel panel=new JPanel();
		tSend=new JTextField("",15);
		bSend=new JButton("发送");
		panel.add(tSend);
		panel.add(bSend);
		con.add(panel, BorderLayout.SOUTH);
		System.out.println(con.getComponentCount());
		
		ActionListener al=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd=e.getActionCommand();
				if(cmd.equals("发送")){
					System.out.println("name"+name);
					String msg=name+":\n  "+tSend.getText();
					
					try {
						PrintWriter pw=new PrintWriter(soc.getOutputStream());
						pw.println(msg);
						pw.flush();
						tSend.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		};
		bSend.addActionListener(al);
		
		KeyListener kl=new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char ch=e.getKeyChar();
				if(ch=='\n'){
					String msg1=name+":\n  "+tSend.getText();
					try {
						PrintWriter pw1=new PrintWriter(soc.getOutputStream());
						pw1.println(msg1);
						pw1.flush();
						tSend.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		tSend.addKeyListener(kl);
		
		
	
		
		frameChat.setSize(300,600);
		frameChat.setLocation(600, 200);
		frameChat.setVisible(true);
		   try {
			br=new BufferedReader(new InputStreamReader(soc.getInputStream()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		   System.out.println(br);
		   are.setText(msg);
			while(true){
				String msg1;
				try {
					msg1 = br.readLine();
					System.out.println(msg1);
					are.setText(are.getText()+"\n"+msg1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}
		
	
	}
	
	public JFrame getFrameChat(){
		return frameChat;
	}
	public JButton getBSend(){
		return bSend;
	}
	public JTextField getTSend(){
		return tSend;
	}
	public JTextArea getAre(){
		return are;
	}
}
