package com.aowin.Exceptions;

import javax.swing.JOptionPane;

public class FormatException extends RuntimeException {

	public FormatException() {
		super("输入格式不对");
		// TODO Auto-generated constructor stub
		JOptionPane.showMessageDialog(null, "格式不对！请重新输入");
	}
	

}
