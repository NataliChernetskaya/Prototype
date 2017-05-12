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
	 public void addtoSYSTEM(Connection connection, String nname, int uId) throws SQLException {//user_id
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("insert into system (namesys, user_id) values('"+nname+"',"+uId+")");
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
	        	switch (nameT){
	        	case "system": statement.execute("delete from system where  sys_id = "+itemId);
	        	break;
	        	case "bus": statement.execute("delete from bus where  bus_id = "+itemId);
	        	break;
	        	case "ecu": statement.execute("delete from ecu where  ecu_id = "+itemId);
	        	break;
	        	case "core": statement.execute("delete from core where  core_id = "+itemId);
	        	break;
	        	case "task": statement.execute("delete from frame where  task_id = "+itemId);
	        			statement.execute("delete from task where  task_id = "+itemId);       			
	        	break;
	        	case "frame": statement.execute("delete from frame where  frame_id = "+itemId);
	        	break;
	        	}
	        }
	    }
	 
	 //----------------------------------------------------------------------------
	 //-------------Edit Item---------------------------------------------------
	 public void editBUS(Connection connection, String nname, int speedb, int busId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update BUS set namebus = '"+nname+"', speedb = "+speedb+" where bus_id = "+busId);
	        }
	    }
	 public void editCORE(Connection connection, String nname, int coreId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update core set namecore ='"+nname+"'  where core_id = "+coreId);
	        }
	    }
	 public void editECU(Connection connection, String nname,  int ecuId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update ecu set nameecu = '"+nname+"'  where ecu_id = "+ecuId);
	        }
	    }
	 public void editFRAME(Connection connection, String nname, int lengthf,  int frameId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update frame set nameframe = '"+nname+"', lengthf = "+lengthf+""
	        			+ "   where frame_id = "+frameId);
	        }
	    }
	 public void editSYSTEM(Connection connection, String nname, int sysId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update system set namesys ='"+nname+"' where sys_id = "+sysId);
	        }
	    }
	 public void editTASK(Connection connection, String nname, String type, int offsett, int lengtht, int period,  int taskId) throws SQLException {
	        try (Statement statement = connection.createStatement()) {
	        	
	        	statement.execute("update task set nametask = '"+nname+"', type = '"+type+"', offsett ="+offsett+" , lengtht = "+lengtht+", "
	        			+ "period = "+period+" where task_id = "+taskId);
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
	 public String getCOREID(Connection connection,  int ecuId) throws SQLException {
	 		String result ="0";
     	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select core_id, namecore from core where ecu_id = "+ecuId+";");
	            while (rs.next()) {
	                System.out.println(rs.getInt("core_id") + " : " + rs.getString("namecore"));
	                result=result+"-"+ rs.getInt("core_id");

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
	 public String getBUSID(Connection connection,  int sysId) throws SQLException {
	 		String result = "0";
  	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select bus_id, namebus, speedb from bus where sys_id = "+sysId+";");
	            while (rs.next()) {
	                System.out.println(rs.getInt("bus_id") + " : " + rs.getString("namebus")+" : " + rs.getInt("speedb"));
	                result=result+"-"+ rs.getInt("bus_id");

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
	 public String getECUID(Connection connection,  int sysId) throws SQLException {
	 		String result = "0";
	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select ecu_id, nameecu from ecu where sys_id = "+sysId+";");
	            while (rs.next()) {
	                System.out.println(rs.getInt("ecu_id") + " : " + rs.getString("nameecu"));
	                result=result+"-"+rs.getInt("ecu_id");

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
	 		String result = "0";
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
	 		String result = "0";
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
	 public String getTASKID(Connection connection,  int coreId) throws SQLException {
	 		String result = "0";
	ResultSet rs;
	        try (Statement statement = connection.createStatement()) {
	            rs = statement.executeQuery("select task_id from task where core_id = "+coreId+";");
	            while (rs.next()) {
	            	//System.out.println(rs.getInt("task_id"));
         result=result+"-"+ rs.getInt("task_id");
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
