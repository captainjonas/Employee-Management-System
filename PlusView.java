package com.aowin.frame;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.aowin.Exceptions.FormatException;
import com.aowin.stuff.Stuff;

public class PlusView {	
	JFrame frame;
	Container con;
	JTextField text1,text2,text5;
	JComboBox box,box1,box3;
	JButton button1,button2;
	JPanel panel5,panel6,panel7;
	

	public PlusView(){
		
		frame=new JFrame();
		con=frame.getContentPane();
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("编号：");
		JLabel label2=new JLabel("姓名：");
		JLabel label3=new JLabel("性别：");
		JLabel label4=new JLabel("部门:");
		JLabel label5=new JLabel("工资:");
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		panel5=new JPanel();
		panel1.add(label1);
		panel2.add(label2);
		panel3.add(label3);
		panel4.add(label4);
		panel5.add(label5);
		text1=new JTextField("",12);
		text2=new JTextField("",12);
		panel1.add(text1);
		panel2.add(text2);
		Object[] sexName={"男","女",""};
		box=new JComboBox(sexName);
		panel3.add(box);
		Object[] departmentName={"","Secretary","Engineer","Lead","Logistics","Market","Finance"};
		box1=new JComboBox(departmentName);
		panel4.add(box1);
		text5=new JTextField("",12);
		panel5.add(text5);
		panel6=new JPanel();
		button1=new JButton("确认");
		button2=new JButton("取消");
		panel6.add(button1);
		panel6.add(button2);
		con.add(panel1);
		con.add(panel2);
		con.add(panel3);
		con.add(panel4);
		con.add(panel5);
		con.add(panel6);
		
		panel7=new JPanel();
		JLabel label7=new JLabel("请选择查询条件");
		Object[] items={"and","or"};
		box3=new JComboBox(items);
		panel7.add(label7);
		panel7.add(box3);
		//con.add(panel7);

		
		frame.setSize(250,400);
		frame.setLocation(600, 200);
		
	}
	
	public JFrame getFrame(){
		return frame;
	}
	public Container getCon(){
		return con;
	}
	public JTextField getText1(){
		return text1;
	}
	public JTextField getText2(){
		return text2;
	}
	public JTextField getText5(){
		return text5;
	}
	public JComboBox getBox(){
		return box;
	}
	public JComboBox getBox1(){
		return box1;
	}
	public JButton getButton1(){
		return button1;
	}
	public JButton getButton2(){
		return button2;
	}
	public JPanel getPanel6(){
		return panel6;
	}
	public JPanel getPanel7(){
		return panel7;
	}
	public JComboBox getBox3(){
		return box3;
	}
	public JPanel getPanel5(){
		return panel5;
	}
	
	public Stuff getStuff(){
		Stuff stuff=new Stuff();
		int idSet=Integer.parseInt(text1.getText());
		System.out.println(idSet);
		stuff.setId(idSet);
		stuff.setName(text2.getText());
		stuff.setSalary(Integer.valueOf(text5.getText()));
		Object sexO=box.getSelectedItem();
		int sexSet=(sexO.equals("男"))?1:0;
		stuff.setSex(sexSet);
		stuff.setDepartment((String)(box1.getSelectedItem()));
		return stuff;
	}
	public void clear(){
		text1.setText("");
		text2.setText("");
		text5.setText("");
		box.setSelectedItem("男");
		box1.setSelectedItem("");
	}
	public Stuff getStaff2(){
		Stuff stuff=new Stuff();
		int idSet=Integer.parseInt(text1.getText());
		System.out.println(idSet);
		stuff.setId(idSet);
		stuff.setName(text2.getText());
		stuff.setSalary(0);
		Object sexO=box.getSelectedItem();
		int sexSet=(sexO.equals("男"))?1:0;
		stuff.setSex(sexSet);
		stuff.setDepartment((String)(box1.getSelectedItem()));
		return stuff;
	}
	
	public boolean throwFormatException(){
		String regID="\\d{2,8}";
		String regName="[a-z||A-Z||\u4e00-\u9fa5]{1,30}";
		String regSalary="\\d{3,9}";
		boolean flag=false;
		if((!text1.getText().equals("")&&!text1.getText().matches(regID)||(!text2.getText().equals("")&&!text2.getText().matches(regName))||(!text5.getText().equals("")&&!text5.getText().matches(regSalary)))){
			flag=true;
			try {
				throw new FormatException();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return flag;
	}


}
