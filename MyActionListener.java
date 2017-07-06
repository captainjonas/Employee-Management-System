package com.aowin.Listener;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.aowin.Database.Database;
import com.aowin.Exceptions.IDExistedException;
import com.aowin.Exceptions.SelectRowException;
import com.aowin.frame.ChatView;
import com.aowin.frame.MainView;
import com.aowin.frame.PlusView;
import com.aowin.stuff.Stuff;


public class MyActionListener implements ActionListener {

	MainView view;
	Stuff stuff=new Stuff();
    Socket soc;
    ChatView chat;


	
	public MyActionListener(MainView mv){
		this.view=mv;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();

		PlusView pv=view.getPV();
		JFrame framePV=pv.getFrame();
		JTextField text1=pv.getText1();
		JTextField text2=pv.getText2();
		JTextField text5=pv.getText5();
		JPanel panel1=view.getPanel1();
		JPanel panel5=pv.getPanel5();
		JPanel panel6=pv.getPanel6();
		JPanel panel7=pv.getPanel7();
		Container con=pv.getCon();
		JComboBox box=pv.getBox();
		JComboBox box1=pv.getBox1();
		Container conMV=view.getCon();
		DefaultTableModel model=view.getModel();
		int count=pv.getCon().getComponentCount();
		TableColumnModel columnModel=view.getTable().getColumnModel();
		TableColumn co=columnModel.getColumn(4);
		
		if(e.getSource().equals(view.getMenu2())){
	    	JOptionPane.showMessageDialog(null, "如果任何问题，请联系和盈工程师！\n电话：400-800-7176");
	    }
	    
	    
	
		switch(cmd){
		case "增加":{
			if(co.getWidth()==70){
				if(count==7){
					con.remove(panel6);
					con.remove(panel7);
					con.add(panel6);
				}
				if(count==5){
					con.remove(panel7);
					con.remove(panel6);
					con.add(panel5);
					con.add(panel6);
				}
			}else if(co.getWidth()==0){
				if(count==6||count==7){
					con.remove(panel5);
					con.remove(panel6);
					con.remove(panel7);
					con.add(panel6);
				}
			}
			
			framePV.setVisible(true);
			framePV.setTitle("增加");
			break;
		}
		case "删除":{
			int row=view.getTable().getSelectedRow();
			int[] rows=view.getTable().getSelectedRows();
			if(row!=-1){
			Object[] ids=new Object[rows.length];
			for(int j=0;j<ids.length;j++){
				ids[j]=model.getValueAt(rows[j], 0);
			}
			String sql1="delete from stuff where ";
				for(int i=0;i<ids.length-1;i++){
					sql1+="id="+ids[i]+" or ";
				}
				sql1+="id="+ids[ids.length-1];
			Database.delete(sql1);
			for(int i=0;i<rows.length;i++){
				model.removeRow(rows[i]);
				for(int j=i+1;j<rows.length;j++)
					rows[j]--;
			}
			}else{
				try {
					throw new SelectRowException();
				} catch (SelectRowException e1) {
					// TODO Auto-generated catch block
					System.err.println(e1.getMessage());
				}
			}
			break;
		}
		case "修改":{	
			int row=view.getTable().getSelectedRow();
			if(row!=-1){
				if(co.getWidth()==70){
					if(count==7){
						con.remove(panel6);
						con.remove(panel7);
						con.add(panel6);
					}
					if (count==5){
						con.remove(panel7);
						con.remove(panel6);
						con.add(panel5);
						con.add(panel6);
					}
				}else if(co.getWidth()==0){
					if(count==6||count==7){
						con.remove(panel6);
						con.remove(panel5);
						con.remove(panel7);
						con.add(panel6);
					}
				}
				
					view.displayStuff();
				
				
				framePV.setTitle("修改");
				framePV.setVisible(true);
			}else{
				try {
					throw new SelectRowException();
				} catch (SelectRowException e1) {
					System.err.println(e1.getMessage());
				}
			}
			break;
		}
		
		case "查询":{
			if(co.getWidth()==70){
				if(count==5){
					con.remove(panel6);
					con.add(panel5);
					con.add(panel7);
					con.add(panel6);
				}
				if(count==6){
					con.remove(panel6);
					con.add(panel7);
					con.add(panel6);
				}
			}else if(co.getWidth()==0){
				if(count==7){
					con.remove(panel5);
					con.remove(panel6);
					con.remove(panel7);
					con.add(panel6);
					con.add(panel7);	
				}
			}
			
			
			framePV.setTitle("查询");
			framePV.setVisible(true);
			break;
		
		}
		
		case "取消":{
			text1.setText("");
			text2.setText("");
			text5.setText("");
			box.setSelectedItem("男");
			box1.setSelectedItem("");
			break;
		}
		case "导出":{
			JFileChooser chooser=new JFileChooser();
			chooser.showSaveDialog(null);
			File file=chooser.getSelectedFile();
			try {
					view.writeXML(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			break;
		}
		case "导入":{
			JFileChooser choose=new JFileChooser();
			choose.showOpenDialog(null);
			File f=choose.getSelectedFile();
			 ArrayList<Stuff> al1=view.readXML(f);	
			 System.out.println(al1);
				int option=JOptionPane.showConfirmDialog(null, "是否将导入的数据添加到数据库？"); 
				System.out.println(option);
				if(option==0){
			     Database.addList(al1);
				}
		
			break;
		}
			
		case "更新":{
			view.recover();
			break;
		}
		
		case "员工管理":{
			view.recover();
			co.setMinWidth(70);
			co.setMaxWidth(70);
			co.setWidth(70);
			co.setPreferredWidth(70);
			panel1.add(view.getBAdd());
			panel1.add(view.getBDelete());
			panel1.add(view.getBUpdate());
			conMV.add(panel1,BorderLayout.SOUTH);
			break;
		}
		case "部门管理":{
			view.recover();
			co.setMinWidth(0);
			co.setMaxWidth(0);
			co.setWidth(0);
			co.setPreferredWidth(0);
			panel1.add(view.getBAdd());
			panel1.add(view.getBDelete());
			panel1.add(view.getBUpdate());
			conMV.add(panel1,BorderLayout.SOUTH);
			break;
		}
		case "问题讨论":{
			
		     new ChatView(view.getUsrName()).start();
			 break;
		}
		
		case "确认":{
			if(!pv.throwFormatException()){
			if(framePV.getTitle().equals("增加")){
				ArrayList<Integer> ID=Database.getAllID();
				if(!ID.contains(Integer.valueOf(text1.getText()))){

						try {
							Database.add(pv.getStuff());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						view.addStuff(pv.getStuff());
						pv.clear();
				}else{
					try {
						throw new IDExistedException();
					} catch (IDExistedException e1) {
						System.err.println(e1.getMessage());
					}
				}
			}else{
				if(framePV.getTitle().equals("查询")){
					Object condition=pv.getBox3().getSelectedItem();
						boolean[] flag={true,true,true,true,true};
						if(text1.getText().equals(""))
							flag[0]=false;
						if(text2.getText().equals(""))
							flag[1]=false;
						if(text5.getText().equals(""))
							flag[4]=false;
						if(box.getSelectedItem().equals(""))
							flag[2]=false;
						if(box1.getSelectedItem().equals(""))
							flag[3]=false;
						int sexQuery=(box.getSelectedItem().equals("男"))?1:0;
						String[] attribute={"id like '%"+text1.getText(), "name like '%"+text2.getText(), "sex='"+sexQuery,"department='"+box1.getSelectedItem(),"salary like '%"+text5.getText()};
						String sql="select * from stuff where ";
						if(!(flag[0]||flag[1]||flag[2]||flag[3]||flag[4])){
							sql="select * from stuff";
						}else{
							int lastIndex=4;
							for(int j=4;j>=0;j--){
								if(!flag[j]){
									lastIndex--;
								}else{
									break;
								}
							}
							for(int i=0;i<lastIndex;i++){
								if(flag[i])
								sql+=attribute[i]+"%' "+condition+" ";
							}
							  sql+=attribute[lastIndex]+"'";	
						}
						System.out.println(sql);
						ArrayList<Stuff> al=Database.query(sql);
						if(al.size()==0){
							JOptionPane.showMessageDialog(null, "您查询的对象不存在！");
						}
							
						model.setRowCount(0);
							for(Stuff stuff:al)
								view.addStuff(stuff);
					   pv.clear();
						
				}else{
					if(framePV.getTitle().equals("修改")){
						int row1=view.getTable().getSelectedRow();
						Object id=model.getValueAt(row1, 0);
							try {
								Database.update(pv.getStuff(), id);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							model.removeRow(row1);
							view.addStuff(pv.getStuff());
						    framePV.setVisible(false);
					}
					
					
					}
			
			}	
					}
		}	
		}
	}
	
}
