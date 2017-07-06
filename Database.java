package com.aowin.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.aowin.stuff.Stuff;


public class Database {
	static Statement st;
	static Connection conn;
	public static ArrayList<Integer> getAllID(){
		ArrayList<Integer> IDs=new ArrayList<Integer>();
		conn=DButil.getConn();
		String sql1="select ID from stuff";
		try {
		st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql1);
		while(rs.next()){
			int id=rs.getInt("ID");
			IDs.add(id);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return IDs;
	}
	
	public static void add(Stuff stuff) throws SQLException{
		conn=DButil.getConn();
		st=conn.createStatement();
		String sql="insert into stuff values('"+stuff.getId()+"','"+stuff.getName()+"','"+stuff.getSex()+"','"+stuff.getDepartment()+"',"+stuff.getSalary()+")";
			st.executeUpdate(sql);
	    DButil.close(null, st, conn);
	}
	
	public static void addList(ArrayList<Stuff> al){
		ArrayList<Integer> ids=getAllID();
		try {
		String sql="insert into stuff values(?, ?, ?, ?, ?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		for(Stuff s:al){
			Integer id1=s.getId();
			if(!ids.contains(id1)){
				ps.setInt(1, id1);
				ps.setString(2, s.getName());
				ps.setInt(3, s.getSex());
				ps.setString(4, s.getDepartment());
				ps.setInt(5, s.getSalary());
				ps.addBatch();
				System.out.println(ps);
			}
		}
		ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DButil.close(null, st, conn);
	}
	public static void update(Stuff stuff,Object id) throws SQLException{
		conn=DButil.getConn();
		st=conn.createStatement();
		String sql="update stuff set ID="+stuff.getId()+",name= '"+stuff.getName()+"',sex= '"+stuff.getSex()+"',department= '"+stuff.getDepartment()+"',salary= "+stuff.getSalary()+" where ID="+id+"";
		st.executeUpdate(sql);
		DButil.close(null, st, conn);
	}
	public static void delete(String sql){
		conn=DButil.getConn();
		try {
			st=conn.createStatement();
			st.executeUpdate(sql);
			DButil.close(null, st, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Stuff> query(String sql){
		ArrayList<Stuff> al=new ArrayList<Stuff> ();
		conn=DButil.getConn();
		st=null;
		ResultSet rs=null;
		try {
			st=conn.createStatement();
			rs = st.executeQuery(sql);
	
		while(rs.next()){
			int id=rs.getInt("ID");
			String name=rs.getString("name");
			int sex = rs.getInt("sex");
			String department=rs.getString("department");
			int salary=rs.getInt("salary");
			Stuff stuff=new Stuff(id,name,sex,department,salary);
			al.add(stuff);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 DButil.close(rs, st, conn);
		return al;
		
		
	}
	
	public static String queryPassword(String accountName){
		String pass=null;
		conn=DButil.getConn();
		try {
			Statement st=conn.createStatement();
			String sql="select password from account where Name='"+accountName+"'";
			ResultSet result=st.executeQuery(sql);
			if(result.next()){
				pass=result.getString("password"); 
			}
		    DButil.close(result, st, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pass;
		
	}
	
	
	public static boolean queryName(String name){
		boolean flag=false;
		conn=DButil.getConn();
		try {
			Statement st=conn.createStatement();
			String sql="select Name from account where Name='"+name+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()){
				flag=true;
			}
				DButil.close(rs, st, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
