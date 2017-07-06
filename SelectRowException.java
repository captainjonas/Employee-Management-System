package com.aowin.Exceptions;

import javax.swing.JOptionPane;

public class SelectRowException extends RuntimeException {
	public SelectRowException(){
		super("请选择表格中相应的行");
		JOptionPane.showMessageDialog(null, "请选择表格中相应的行！");
	}

}
