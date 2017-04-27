package com.vogella.jersey.jaxb.model;

import java.sql.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class connectH2 {
	 public connectH2() throws ClassNotFoundException {
	        Class.forName("org.h2.Driver");
	    }
	 public Connection getConnection() throws SQLException {
	        return DriverManager.getConnection("jdbc:h2:~/testdb","sa","");
	    }
	 //-------------Add to Table----------------------------------------------------- 
	 public void addtoBUS(Connection connection, String nname, int speedb, int sysId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("insert into BUS(namebus, speedb, sys_id) values('"+nname+"',"+speedb+", "+sysId+")");
	            //statement.execute("insert into user(name) values(nname)");
	        }
	    }
	 public void addtoCORE(Connection connection, String nname, int ecuId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("insert into core(namecore, ecu_id) values('"+nname+"', "+ecuId+") ");
	        }
	    }
	 public void addtoECU(Connection connection, String nname, int sysId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("insert into ecu (nameecu, sys_id) values('"+nname+"',"+sysId+")");
	        }
	    }
	 public void addtoFRAME(Connection connection, String nname, int lengthf, int taskId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("insert into frame (nameframe, lengthf, task_Id) values('"+nname+"',"+lengthf+","+taskId+")");
	        }
	    }
	 public void addtoSYSTEM(Connection connection, String nname) throws SQLException {//user_id
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("insert into system (namesys) values('"+nname+"')");
	        }
	    }
	 public void addtoTASK(Connection connection, String nname, String type, int offsett, int lengtht, int period, int coreId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("insert into task (nametask, type, offsett, lengtht, period, core_Id"
	        			+ ") values('"+nname+"', '"+type+"',"+offsett+", "+lengtht+", "+period+","+coreId+")");
	        }
	    }
	 //-----------------------------------------------------------------------------------------
	 //---------------------Delete From Table--------------------------------------------------
	 public void deleteFromTable(Connection connection,String nameT, int itemId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("delete from "+nameT+" where  bus_id = "+itemId);
	        }
	    }
	 
	 //----------------------------------------------------------------------------
	 //-------------Edit Item---------------------------------------------------
	 public void editBUS(Connection connection, String nname, int speedb, int busId, int sysId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update BUS set namebus = '"+nname+"', speedb = "+speedb+", sys_id="+sysId+" where id = "+busId+")");
	        }
	    }
	 public void editCORE(Connection connection, String nname, int ecuId, int coreId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update core set namecore ='"+nname+"' , ecu_id ="+ecuId+" where core_id = "+coreId+") ");
	        }
	    }
	 public void editECU(Connection connection, String nname, int sysId, int ecuId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update ecu set nameecu = '"+nname+"', sys_id ="+sysId+"  where ecu_id = "+ecuId+")");
	        }
	    }
	 public void editFRAME(Connection connection, String nname, int lengthf, int taskId, int frameId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update frame set nameframe = '"+nname+"', lengthf = "+lengthf+""
	        			+ " , task_Id = "+taskId+" where frame_id = "+frameId+")");
	        }
	    }
	 public void editSYSTEM(Connection connection, String nname, int sysId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update system set namesys ='"+nname+"' where sys_id = "+sysId+")");
	        }
	    }
	 public void editTASK(Connection connection, String nname, String type, int offsett, int lengtht, int period, int coreId, int taskId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update task set nametask = '"+nname+"', type = '"+type+"', offsett ="+offsett+" , lengtht = "+lengtht+", "
	        			+ "period = "+period+", core_Id ="+coreId+"where task_id = "+taskId+ ")");
	        }
	    }
	 //--------------------------------------------------------------------------
	 
	 //------------------Get Information----------------------------------------
	 public String getCORE(Connection connection,  int ecuId) throws SQLException {
		 		String result ="0";
	        	ResultSet rs;
		        try (Statement statement = connection.createStatement()) {
		            rs = statement.executeQuery("select core_id, namecore from core where ecu_id = "+ecuId+";");
		            while (rs.next()) {
		                System.out.println(rs.getInt("core_id") + " : " + rs.getString("namecore"));
		                result=result+"-"+ rs.getInt("core_id") + ":"+rs.getString("namecore");

		            }
		            System.out.println("----------------");
		        }
		        return result;
	        }
	 public String getBUS(Connection connection,  int sysId) throws SQLException {
	 		String result = "0";
     	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select bus_id, namebus, speedb from bus where sys_id = "+sysId+";");
	            while (rs.next()) {
	                System.out.println(rs.getInt("bus_id") + " : " + rs.getString("namebus")+" : " + rs.getInt("speedb"));
	                result=result+"-"+ rs.getInt("bus_id") + ":"+rs.getString("namebus") + ":" + rs.getInt("speedb");

	            }
	            System.out.println("----------------");
	        }
	        return result;
     }
	 public String getECU(Connection connection,  int sysId) throws SQLException {
	 		String result = "0";
  	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select ecu_id, nameecu from ecu where sys_id = "+sysId+";");
	            while (rs.next()) {
	                System.out.println(rs.getInt("ecu_id") + " : " + rs.getString("nameecu"));
	                result=result+"-"+rs.getInt("ecu_id") + ":"+rs.getString("nameecu");

	            }
	            System.out.println("----------------");
	        }
	        return result;
  }
	 public String getFRAME(Connection connection,  int taskId) throws SQLException {
	 		String result = "0";
  	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select frame_id, nameframe, lengthf from frame where task_id = "+taskId+";");
	            while (rs.next()) {
	                System.out.println(rs.getInt("frame_id") + " : " + rs.getString("nameframe")+" : " + rs.getInt("lengthf"));
	                result=result+"-"+rs.getInt("frame_id") + ":"+rs.getString("nameframe") + ":" + rs.getInt("lengthf");

	            }
	            System.out.println("----------------");
	        }
	        return result;
  }
	 public String getSYSTEMNAME(Connection connection) throws SQLException {
	 		String result = "0";
	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select * from system;");
	            while (rs.next()) {
	                System.out.println(rs.getInt("sys_id") + " : " + rs.getString("namesys"));
	                result=result+"-"+rs.getString("namesys");

	            }
	            System.out.println("----------------");
	        }
	        return result;
}
	 public String getSYSTEMID(Connection connection, String nameS) throws SQLException {
	 		String result = null;
	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select sys_id from system where namesys = '"+nameS+"'");
	            while (rs.next()) {
	                System.out.println(rs.getInt("sys_id") );
	                result=Integer.toString(rs.getInt("sys_id"));

	            }
	            System.out.println("----------------");
	        }
	        return result;
}
	 public String getTASK(Connection connection,  int coreId) throws SQLException {
	 		String result = "0";
	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select task_id, nametask, type, offsett, lengtht, period from task where core_id = "+coreId+";");
	            while (rs.next()) {
	            	System.out.println(rs.getInt("task_id") + " : " + rs.getString("nametask")+": " + rs.getString("type")+":"+rs.getInt("offsett")
               		+":"+rs.getInt("lengtht")+":"+rs.getInt("period"));
               result=result+"-"+ rs.getInt("task_id") + ":"+rs.getString("nametask")+ ":"+rs.getString("type")+":"+rs.getInt("offsett")+
       		":"+rs.getInt("lengtht")+":"+rs.getInt("period");
	            }
	            System.out.println("----------------");
	        }
	        return result;
}
	 public String getTASKNAME(Connection connection,  int coreId) throws SQLException {
	 		String result = null;
	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select task_id, nametask, type, offsett, lengtht, period from task where core_id = "+coreId+";");
	            while (rs.next()) {
	            	System.out.println(rs.getInt("task_id") + " : " + rs.getString("nametask")+": " + rs.getString("type")+":"+rs.getInt("offsett")
            		+":"+rs.getInt("lengtht")+":"+rs.getInt("period"));
            result=result+"-"+ rs.getInt("task_id") + ":"+rs.getString("nametask");
	            }
	            System.out.println("----------------");
	        }
	        return result;
}
	 public String resultSet(Connection connection, String nameTable) throws SQLException {
		 ResultSet rs;
		 String result = null;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select top 1 * from "+nameTable+" order by id desc");
	            while (rs.next()) {
	                System.out.println(rs.getInt("id") + " : " + rs.getString("name"));
	                result="id = "+ rs.getInt("id") + " name = "+rs.getString("name");
	            }
	            System.out.println("----------------");
	        }
	        return result;
	 }
}
