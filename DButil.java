package com.aowin.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DButil {
	public static Connection conn=null;
	public static Statement st=null;
	public static String url="jdbc:mysql://localhost:3306/stuff";
	public static String user="root";
	public static String password="daxian";
	
	
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConn(){
		try {
			conn=DriverManager.getConnection(url,user,password);
			//System.out.println("成功连接");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(ResultSet rs,Statement st,Connection conn){
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(st!=null){
				st.close();
				st=null;
			}
			if(conn!=null){
				conn.close();
				conn=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
