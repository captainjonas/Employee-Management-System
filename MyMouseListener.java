package com.aowin.Listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTable;

import com.aowin.MSutil.SortStaff;
import com.aowin.frame.MainView;
import com.aowin.stuff.Stuff;

public class MyMouseListener implements MouseListener {
	int MouseCount;
	MainView mv;
	ArrayList<Stuff> al;
	

	public MyMouseListener(MainView mv) {
		super();
		this.mv = mv;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table=mv.getTable();
		Point point=e.getPoint();
		int column=table.columnAtPoint(point);
		String colName=table.getColumnName(column);
		MouseCount=e.getClickCount();
		boolean f=(MouseCount%2==1)?true:false;
	
			al=mv.getAllData();
			SortStaff ss=new SortStaff(colName,f);
			Collections.sort(al,ss);
			mv.getModel().setRowCount(0);
			mv.insertListToTable(al);
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
