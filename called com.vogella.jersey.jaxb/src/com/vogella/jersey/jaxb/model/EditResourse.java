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

@Path("/edit")
public class EditResourse {
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nameT}/{name}/{itemId}")
    public String getHTML(@PathParam("nameT") String nT, @PathParam("name") String n, @PathParam("itemId") int id) throws ClassNotFoundException, SQLException {
    	connectH2 n_H2 = new connectH2();
    	Connection connection = null;
    	String result = "";
    	try {
			connection = n_H2.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка подключения к базе!";
		}
    	if(nT.equals("core")){
    	try {
    		result=n_H2.editCORE(connection, n, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка!";
		}
    	}
    	else if(nT.equals("ecu")){
    		try {
    			result=n_H2.editECU(connection, n, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка!";
			}
    	}
           
            return result;
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nameT}/{name}/{lenorspeed}/{itemId}")
    public String getHTML(@PathParam("nameT") String nT, @PathParam("name") String n, @PathParam("lenorspeed") int ls,  @PathParam("itemId") int id) throws ClassNotFoundException, SQLException {
    	connectH2 n_H2 = new connectH2();
    	Connection connection = null;
    	String result ="";
    	try {
			connection = n_H2.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка подключения к базе!";
		}
    	if(nT.equals("bus")){
    	try {
    		result=n_H2.editBUS(connection, n, ls, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка!";
		}
    	}
    	else if(nT.equals("frame")){
    		try {
    			result=	n_H2.editFRAME(connection, n,ls, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка!";
			}
    	}
           
	      return result;
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public String getHTML(@PathParam("name") String n) throws ClassNotFoundException, SQLException {
    	String id=null;
    	connectH2 n_H2 = new connectH2();
    	Connection connection = null;
    	String result = "";
    	try {
			connection = n_H2.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка подключения к базе!";
		}
    	
    	try {
			id = n_H2.getSYSTEMID(connection, n);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка!";
		}  	  
    	System.out.println("id = " + id);
    	
    	try {
    		result=n_H2.editSYSTEM(connection, n, Integer.parseInt(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка!";
		}  	     		
           
	      return result;
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}/{type}/{offset}/{lenght}/{period}/{itemId}")
    public String getHTML(@PathParam("name") String n, @PathParam("type") String t,@PathParam("offset") int of, @PathParam("lenght") int l,@PathParam("period") int p,  @PathParam("itemId") int id) throws ClassNotFoundException, SQLException {
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
    		result=n_H2.editTASK(connection, n,t,of,l,p, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка!";
		}
    	
    	return result;
    }

}
