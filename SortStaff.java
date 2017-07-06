package com.aowin.MSutil;

import java.util.Comparator;

import com.aowin.stuff.Stuff;


public class SortStaff implements Comparator<Stuff>{
   String field;
   boolean ascend;
   public int compare(Stuff staff1, Stuff staff2){
	   int flag=-1;
	   int result=-1;
	   if(ascend){
		   flag=1;
	   }else{
		   flag=-1;
	   }
		  
	   switch(field){
	   case "编号": result=flag*(staff1.getId()-staff2.getId());break;
	   case "名字": result=flag*((staff1.getName()).compareTo(staff2.getName()));break;
	   case "性别": result=flag*(staff1.getSex()-staff2.getSex());break;
	   case "部门": result=flag*(staff1.getDepartment().compareTo(staff2.getDepartment()));break;
	   case "工资": result=flag*(staff1.getSalary()-staff2.getSalary());break;
	   default: result=-1;
	   }
	   
	   return result;
   }
public SortStaff(String field, boolean ascend) {
	super();
	this.field = field;
	this.ascend = ascend;
}
   

}
