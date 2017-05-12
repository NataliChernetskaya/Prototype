package com.vogella.jersey.jaxb.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/delitem")
public class DeleteItem {
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nameT}/{itemId}")
    public String getHTML(@PathParam("nameT") String nT, @PathParam("itemId") int item_id) throws SQLException, ClassNotFoundException{
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
    	case "system": String [] ecu_id =  n_H2.getECUID(connection, item_id).split("-"); 	
						for(int i=1; i<ecu_id.length; i++){
							String [] core_id =  n_H2.getCOREID(connection, Integer.parseInt(ecu_id[i])).split("-"); 	
							for(int j=1; j<core_id.length; j++){
								String [] task_id =  n_H2.getTASKID(connection, Integer.parseInt(core_id[j])).split("-"); 	
			    				for(int k=1; k<task_id.length; k++){
			    					n_H2.deleteFromTable(connection,"task", Integer.parseInt(task_id[k]));
			    				}
			    				n_H2.deleteFromTable(connection,"core", Integer.parseInt(core_id[j]));
							}
							n_H2.deleteFromTable(connection,"ecu", Integer.parseInt(ecu_id[i]));
						}
						String [] bus_id =  n_H2.getBUSID(connection, item_id).split("-");
						for(int i=1; i<bus_id.length; i++){
	    					n_H2.deleteFromTable(connection,"bus", Integer.parseInt(bus_id[i]));
	    				}
						n_H2.deleteFromTable(connection,"system", item_id);
    		break;
    	case "bus":n_H2.deleteFromTable(connection,"bus", item_id);
    		break;
    	case "ecu":String [] core_id =  n_H2.getCOREID(connection, item_id).split("-"); 	
					for(int i=1; i<core_id.length; i++){
						String [] task_id =  n_H2.getTASKID(connection, Integer.parseInt(core_id[i])).split("-"); 	
	    				for(int j=1; j<task_id.length; j++){
	    					n_H2.deleteFromTable(connection,"task", Integer.parseInt(task_id[j]));
	    				}
						n_H2.deleteFromTable(connection,"core", Integer.parseInt(core_id[i]));
					}
					n_H2.deleteFromTable(connection,"ecu", item_id);
    		break;
    	case "core": String tIds =  n_H2.getTASKID(connection, item_id);
    				String [] task_id= tIds.split("-"); 	
    				for(int i=1; i<task_id.length; i++){
    					System.out.println("delcore " + task_id[i]+ "\n");
    					n_H2.deleteFromTable(connection,"task", Integer.parseInt(task_id[i]));
    				}
    				n_H2.deleteFromTable(connection,"core", item_id);
    				
    		break;
    	case "task":n_H2.deleteFromTable(connection,"task", item_id);
    		break;
    	case "frame": n_H2.deleteFromTable(connection,"frame", item_id);
    		break;
    	
    	}
		
		return "Элемент удален!";
	}
}
