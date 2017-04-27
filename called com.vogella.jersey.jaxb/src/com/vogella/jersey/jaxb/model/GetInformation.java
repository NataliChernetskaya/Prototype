package com.vogella.jersey.jaxb.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/getinfo")
public class GetInformation {
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nameT}/{parentId}")
    public String getHTML(@PathParam("nameT") String nT, @PathParam("parentId") int par_id) throws SQLException, ClassNotFoundException{
		connectH2 n_H2 = new connectH2();
    	Connection connection = null;
    	String result = null, error="Ошибка подключения к базе!";
    	try {
			connection = n_H2.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return error;
		}
    	switch (nT){
    		case "bus": result = n_H2.getBUS(connection, par_id);
    		break;
    		case "core": result = n_H2.getCORE(connection, par_id);
    		break;
    		case "ecu": result = n_H2.getECU(connection, par_id);
    		break;
    		case "frame": result = n_H2.getFRAME(connection, par_id);
    		break;
    		case "task": result = n_H2.getTASK(connection, par_id);
    		break;

    	}
	        return result;
		
	}
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nameT}/{opcion}/{sysN}")
    public String getHTML(@PathParam("nameT") String nT,@PathParam("opcion") int op,@PathParam("sysN") String nS) throws SQLException, ClassNotFoundException{
		connectH2 n_H2 = new connectH2();
    	Connection connection = null;
    	String result = null, error="Ошибка подключения к базе!";
    	try {
			connection = n_H2.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return error;
		}
    	switch (nT){
		case "system": if(op ==1) result = n_H2.getSYSTEMNAME(connection);
		else result = n_H2.getSYSTEMID(connection, nS);
		break;
		case "task":result = n_H2.getTASKNAME(connection, Integer.parseInt(nS));
    	break;
    	
    	}
	        return result;
    	
	}
    
}
