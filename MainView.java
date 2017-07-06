package com.aowin.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.aowin.Database.Database;
import com.aowin.Listener.MyActionListener;
import com.aowin.Listener.MyMouseListener;
import com.aowin.stuff.Stuff;


public class MainView {
	PlusView pv;
	JTable table;
	DefaultTableModel model;
	ArrayList<Stuff> al;
	JMenuItem menu2;
	JPanel panel0,panel1,panel3,pChat;
	JTextArea are;
	String accountName,usrName;
	JFrame frame;
	Container con,conChat;
	JButton bAdd,bDelete,bUpdate,bSend;
	JTextField fMsg;
	JMenu menu1;
	JMenuItem mAdd,mDelete, mImport, mExport,mUpdate;


	

	public MainView(String name,String usrName){	
		this.usrName=usrName;
		this.accountName=name;
		
		pv=new PlusView();
		
		frame=new JFrame("人事管理系统——"+accountName);
		con=frame.getContentPane();
		con.setLayout(new BorderLayout());
		panel0=new JPanel();
		panel0.setLayout(new FlowLayout(FlowLayout.CENTER));
		con.add(panel0, BorderLayout.CENTER);
		menu1=new JMenu("File");
		menu2=new JMenuItem("Help");
		JMenuBar bar=new JMenuBar();
		bar.add(menu1);
		bar.add(menu2);
		frame.setJMenuBar(bar);
		mAdd=new JMenuItem("增加");
		mDelete=new JMenuItem("删除");
		mUpdate=new JMenuItem("修改");
		JMenuItem mRecover=new JMenuItem("更新");
		JMenuItem mQuery=new JMenuItem("查询");
		mImport=new JMenuItem("导入");
		mExport=new JMenuItem("导出");
		menu1.add(mAdd);
		menu1.add(mDelete);
		menu1.add(mUpdate);
		menu1.add(mRecover);
		menu1.add(mQuery);
		menu1.add(mImport);
		menu1.add(mExport);
		menu1.addSeparator();
		JLabel label0=new JLabel("人事管理系统        ");
		Font f=new Font("黑体",Font.BOLD,14);
		label0.setFont(f);
		panel0.add(label0);
		Object[] colNames={"编号","名字","性别","部门","工资"};
		model=new DefaultTableModel(colNames,0);
		table=new JTable(model);
		JScrollPane scroll=new JScrollPane(table);
		panel0.add(scroll);
		JTableHeader head=table.getTableHeader();
		panel0.add(head);
		panel0.add(table);
		
		panel1=new JPanel();
		con.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bAdd=new JButton("增加");
		bDelete=new JButton("删除");
		bUpdate=new JButton("修改");
		JButton bRecover=new JButton("更新");
		JButton bQuery=new JButton("查询");
		panel1.add(bRecover);
		panel1.add(bQuery);
		
		panel3=new JPanel();
		panel3.setLayout(new GridLayout(15,1,5,5));
		JButton bStaff=new JButton("员工管理");
		JButton bDepart=new JButton("部门管理");
		JButton bDiscuss=new JButton("问题讨论");
		panel3.add(bStaff);
		panel3.add(bDepart);
		panel3.add(bDiscuss);
		con.add(panel3, BorderLayout.WEST);
		
		
		MyActionListener l=new MyActionListener(this);
		MyMouseListener ml=new MyMouseListener(this);
		menu2.addActionListener(l);
		mAdd.addActionListener(l);
		mDelete.addActionListener(l);
		mUpdate.addActionListener(l);
		mRecover.addActionListener(l);
		mQuery.addActionListener(l);
		mImport.addActionListener(l);
		mExport.addActionListener(l);
		bAdd.addActionListener(l);
		bDelete.addActionListener(l);
		bUpdate.addActionListener(l);
		bRecover.addActionListener(l);
		bQuery.addActionListener(l);
		bStaff.addActionListener(l);
		bDepart.addActionListener(l);
		bDiscuss.addActionListener(l);
		pv.getButton1().addActionListener(l);
		pv.getButton2().addActionListener(l);
		head.addMouseListener(ml);
		
		recover();
		frame.setLocation(200, 100);
		frame.setSize(500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
		
	}
	public PlusView getPV(){
		return pv;
	}
	public void setPV(PlusView pv1){
		this.pv=pv1;
	}
	public DefaultTableModel getModel(){
		return model;
	}
	public JTable getTable(){
		return table;
	}
	public ArrayList<Stuff> getAl(){
		return al;
	}
	public JMenuItem getMenu2(){
		return menu2;
	}
	public JFrame getFrame(){
		return frame;
	}
	public Container getCon(){
		return con;
	}
	public Container getConChat(){
		return conChat;
	}
	public JPanel getPanel0(){
		return panel0;
	}
	public JPanel getPanel1(){
		return panel1;
	}
	public JPanel getPanel3(){
		return panel3;
	}
	public JButton getBAdd(){
		return bAdd;
	}
	public JButton getBDelete(){
		return bDelete;
	}
	public JButton getBUpdate(){
		return bUpdate;
	}
	public String getUsrName(){
		return usrName;
	}
	public JTextField getFMsg(){
		return fMsg;
	}

	public JTextArea getAre(){
		return are;
	}
	public JPanel getPChat(){
		return pChat;
	}
	public JMenu getMenu1(){
		return menu1;
	}
	public JMenuItem getMAdd(){
		return mAdd;
	}
	public JMenuItem getMDelete(){
		return mDelete;
	}
	public JMenuItem getMUpdate(){
		return mUpdate;
	}
	public JMenuItem getMExport(){
		return mExport;
	}
	public JMenuItem getMImport(){
		return mImport;
	}


	
	public void addStuff(Stuff stuff){//在主界面表格中增加一行stuff数据
		String sexR=(stuff.getSex()==1)?"Male":"Female";
		Object[] rowData={stuff.getId(),stuff.getName(),sexR,stuff.getDepartment(),stuff.getSalary()};
		model.addRow(rowData);
	}
	
	
	public void recover(){//更新表格
		model.setRowCount(0);
		ArrayList<Stuff>al=Database.query("select * from stuff");
	
		   for(Stuff s:al){
		   addStuff(s);
		}   
	}
	
	public Stuff getRowStuff(int row){//将一行中的数据提出到stuff对象中
		Stuff stuff=new Stuff();
		Integer id=(Integer)model.getValueAt(row, 0);
	    String name=(String)model.getValueAt(row, 1);
	    String sex=(String)model.getValueAt(row, 2);
	    int sex1=(sex.equals("Male"))?1:0;
	    String department=(String)model.getValueAt(row, 3);
	    Integer salary=(Integer)model.getValueAt(row, 4);
	    stuff=new Stuff(id,name,sex1,department,salary);
	    return stuff;
	}

	
	public void displayStuff(){  //将表格中鼠标点击的数据，显示到副界面上
		int row=table.getSelectedRow();
			   Stuff stuff=getRowStuff(row);
			    pv.getText1().setText(String.valueOf(stuff.getId()));
			    pv.getText2().setText(stuff.getName());
			    pv.getText5().setText(String.valueOf(stuff.getSalary()));
			    Object sexDisplay=(stuff.getSex()==1)?"男":"女";
			    pv.getBox().setSelectedItem(sexDisplay);
			    pv.getBox1().setSelectedItem(stuff.getDepartment());
	}
	
	public ArrayList<Stuff> getAllData(){//将表格中所有的数据放进List中
		ArrayList<Stuff> list=new ArrayList<Stuff> ();
		int rowCount=model.getRowCount();
		for(int i=0;i<rowCount;i++){
			Stuff stuff=getRowStuff(i);
			list.add(stuff);
		}
		return list;
	}

	
	public void insertListToTable(ArrayList<Stuff> al){//将List中所有数据加入表格
		for(Stuff i:al)
			addStuff(i);
	}
	


	public void writeXML(File file) throws FileNotFoundException{
		int rowCount=table.getRowCount();
		Document doc=DocumentHelper.createDocument();
		Element root=doc.addElement("Company");
		for(int i=0;i<rowCount;i++){
			Stuff s=getRowStuff(i);
			Element staff=root.addElement("staff");
			Element id=staff.addElement("id");
			id.setText(String.valueOf(s.getId()));
			Element name=staff.addElement("name");
			name.setText(s.getName());
			Element sex=staff.addElement("sex");
			String sexXML=(s.getSex()==1)?"Male":"Female";
			sex.setText(sexXML);
			Element department=staff.addElement("department");
			department.setText(s.getDepartment());
			Element salary=staff.addElement("salary");
			salary.setText(String.valueOf(s.getSalary()));
		}
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		try {
			doc.write(bw);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


		public ArrayList<Stuff> readXML(File file){
			model.setRowCount(0);
			Document doc=null;
			al=new ArrayList<Stuff>();
			SAXReader reader=new SAXReader();
			try {
				doc=reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			Element root=doc.getRootElement();
			List<Element> staffList=root.elements();
			for(int i=0;i<staffList.size();i++){
				Stuff stuff=new Stuff();
				Element staff=staffList.get(i);
			    List<Element> childList=staff.elements();  
			    for(int j=0;j<childList.size();j++){
			    	Element child=childList.get(j);
			    	String content=child.getText();
			    	switch(j){
			    	case 0: stuff.setId(Integer.valueOf(content));break;
			    	case 1: stuff.setName(content);break;
			    	case 2: {
			    		int sex=(content.equals("Male"))?1:0;
			    		stuff.setSex(sex);
			    		break;
			    	}
			    	case 3: stuff.setDepartment(content);break;
			    	case 4: stuff.setSalary(Integer.valueOf(content));break;
			    	}
			    }
				al.add(stuff);	
			}
			insertListToTable(al);
			return al;
		}
		
		
	
}
