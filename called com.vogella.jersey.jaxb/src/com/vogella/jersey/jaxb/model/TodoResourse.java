package com.vogella.jersey.jaxb.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/add")
public class TodoResourse {
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("{nameT}/{name}/{parentId}")
        public String getHTML(@PathParam("nameT") String nT, @PathParam("name") String n, @PathParam("parentId") int par_id) throws ClassNotFoundException, SQLException {
        	connectH2 n_H2 = new connectH2();
        	Connection connection = null;
        	String result = null;
        	try {
				connection = n_H2.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка подключения к базе!";
			}
        	if(nT.equals("core")){
        	try {
				n_H2.addtoCORE(connection, n, par_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка! Проверьте корректность введенных данных!";
			}
        	}
        	else if(nT.equals("ecu")){
        		try {
    				n_H2.addtoECU(connection, n, par_id);
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return "Ошибка! Проверьте корректность введенных данных!";
    			}
        	}
        	ResultSet rs;
    	      try (Statement statement = connection.createStatement()) {
    	    	if(nT.equals("core")){  
    	        rs = statement.executeQuery("select top 1 * from core order by core_id desc");
    	            while (rs.next()) {
    	                System.out.println(rs.getInt("core_id") + " : " +rs.getString("namecore"));
    	                result="id = "+ rs.getInt("core_id") + " name = "+rs.getString("namecore");
    	            }
    	    	}
    	    	if(nT.equals("ecu")){
    	    		 rs = statement.executeQuery("select top 1 * from ecu order by ecu_id desc");
     	            while (rs.next()) {
     	                System.out.println(rs.getInt("ecu_id") + " : " +rs.getString("nameecu"));
     	                result="id = "+ rs.getInt("ecu_id") + " name = "+rs.getString("nameecu");
     	            }
    	    	}
    	      }
               
                return "Добавлен элемент: "+result;
        }
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("{nameT}/{name}/{lenorspeed}/{parentId}")
        public String getHTML(@PathParam("nameT") String nT, @PathParam("name") String n, @PathParam("lenorspeed") int ls, @PathParam("parentId") int par_id) throws ClassNotFoundException, SQLException {
        	connectH2 n_H2 = new connectH2();
        	Connection connection = null;
        	String result = null;
        	try {
				connection = n_H2.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка подключения к базе!";
			}
        	if(nT.equals("bus")){
        	try {
				n_H2.addtoBUS(connection, n, ls, par_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка! Проверьте корректность введенных данных!";
			}
        	}
        	else if(nT.equals("frame")){
        		try {
    				n_H2.addtoFRAME(connection, n,ls, par_id);
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return "Ошибка! Проверьте корректность введенных данных!";
    			}
        	}
        	ResultSet rs;
  	      try (Statement statement = connection.createStatement()) {
  	    	if(nT.equals("bus")){  
  	        rs = statement.executeQuery("select top 1 * from bus order by bus_id desc");
  	            while (rs.next()) {
  	                System.out.println(rs.getInt("bus_id") + " : "+rs.getString("namebus")+ " : " + rs.getInt("speedb"));
  	                result="id = "+ rs.getInt("bus_id") + " name = "+rs.getString("namebus") + " speed = "+ rs.getInt("speedb");
  	            }
  	    	}
  	    	if(nT.equals("frame")){
  	    		 rs = statement.executeQuery("select top 1 * from frame order by frame_id desc");
   	            while (rs.next()) {
   	                System.out.println(rs.getInt("frame_id") + " : "+rs.getString("nameframe")+ " : "  + rs.getInt("lengthf"));
   	                result="id = "+ rs.getInt("frame_id") + " name = "+rs.getString("nameframe") + "length = "+ rs.getInt("lengthf");
   	            }
  	    	}
  	      }
               
                return "Добавлен элемент: "+result;
        }
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("{name}")
        public String getHTML(@PathParam("name") String n) throws ClassNotFoundException, SQLException {
        	connectH2 n_H2 = new connectH2();
        	Connection connection = null;
        	String result = null;
        	try {
				connection = n_H2.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка подключения к базе!";
			}
        	
        	try {
				n_H2.addtoSYSTEM(connection, n);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка! Проверьте корректность введенных данных!";
			}  	     		
        	 ResultSet rs;
    	      try (Statement statement = connection.createStatement()) {
    	        rs = statement.executeQuery("select top 1 * from system order by sys_id desc");
    	            while (rs.next()) {
    	                System.out.println(rs.getInt("sys_id") + " : " + rs.getString("namesys"));
    	                result="id = "+ rs.getInt("sys_id") + " name = "+rs.getString("namesys");
    	            }
    	      }
               
                return "Добавлен элемент: "+result;
        }
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("{name}/{type}/{offset}/{lenght}/{period}/{parentId}")
        public String getHTML(@PathParam("name") String n, @PathParam("type") String t,@PathParam("offset") int of, @PathParam("lenght") int l,@PathParam("period") int p, @PathParam("parentId") int par_id) throws ClassNotFoundException, SQLException {
        	connectH2 n_H2 = new connectH2();
        	Connection connection = null;
        	String result = null;
        	try {
				connection = n_H2.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка подключения к базе!";
			}
        	
        	try {
				n_H2.addtoTASK(connection, n,t,of,l,p, par_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка! Проверьте корректность введенных данных!";
			}
        	
        	 ResultSet rs;
   	      try (Statement statement = connection.createStatement()) {
   	        rs = statement.executeQuery("select top 1 * from task order by task_id desc");
   	            while (rs.next()) {
   	                System.out.println(rs.getInt("task_id") + " : " + rs.getString("nametask")+": " + rs.getString("type")+":"+rs.getInt("offsett")
   	                		+":"+rs.getInt("lengtht")+":"+rs.getInt("period"));
   	                result="id = "+ rs.getInt("task_id") + " name = "+rs.getString("nametask")+ " type = "+rs.getString("type")+"offset ="+rs.getInt("offsett")+
               		" length = "+rs.getInt("lengtht")+" period = "+rs.getInt("period");
   	            }
   	      }
               
                return "Добавлен элемент: "+result;
        }


}