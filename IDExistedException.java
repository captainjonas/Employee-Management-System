package com.aowin.Exceptions;

import javax.swing.JOptionPane;

public class IDExistedException extends Exception {
	public IDExistedException(){
		super("编号已经存在！");
		JOptionPane.showMessageDialog(null, "编号已经存在！");
	}

}
