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
    @Path("{nameT}/{name}/{parentId}/{itemId}")
    public String getHTML(@PathParam("nameT") String nT, @PathParam("name") String n, @PathParam("parentId") int par_id, @PathParam("itemId") int id) throws ClassNotFoundException, SQLException {
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
			n_H2.editCORE(connection, n,par_id, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка! Проверьте корректность введенных данных!";
		}
    	}
    	else if(nT.equals("ecu")){
    		try {
				n_H2.editECU(connection, n, par_id, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка! Проверьте корректность введенных данных!";
			}
    	}
           
            return "Элемент изменен!";
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nameT}/{name}/{lenorspeed}/{parentId}/{itemId}")
    public String getHTML(@PathParam("nameT") String nT, @PathParam("name") String n, @PathParam("lenorspeed") int ls, @PathParam("parentId") int par_id, @PathParam("itemId") int id) throws ClassNotFoundException, SQLException {
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
			n_H2.editBUS(connection, n, ls, par_id, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка! Проверьте корректность введенных данных!";
		}
    	}
    	else if(nT.equals("frame")){
    		try {
				n_H2.editFRAME(connection, n,ls, par_id, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка! Проверьте корректность введенных данных!";
			}
    	}
           
	      return "Элемент изменен!";
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}/{itemId}")
    public String getHTML(@PathParam("name") String n, @PathParam("itemId") int id) throws ClassNotFoundException, SQLException {
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
			n_H2.editSYSTEM(connection, n, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка! Проверьте корректность введенных данных!";
		}  	     		
           
	      return "Элемент изменен!";
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}/{type}/{offset}/{lenght}/{period}/{parentId}/{itemId}")
    public String getHTML(@PathParam("name") String n, @PathParam("type") String t,@PathParam("offset") int of, @PathParam("lenght") int l,@PathParam("period") int p, @PathParam("parentId") int par_id, @PathParam("itemId") int id) throws ClassNotFoundException, SQLException {
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
			n_H2.editTASK(connection, n,t,of,l,p, par_id, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка! Проверьте корректность введенных данных!";
		}
    	
    	return "Элемент изменен!";
    }

}
