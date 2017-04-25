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

@Path("/delete")
public class DeleteResourse {
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nameT}/{itemId}")
    public String getHTML(@PathParam("nameT") String nT,  @PathParam("itemId") int id) throws ClassNotFoundException, SQLException {
    	connectH2 n_H2 = new connectH2();
    	Connection connection = null;
    	try {
			connection = n_H2.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ошибка подключения к базе!";
		}
    	
    	try {
			n_H2.deleteFromTable(connection, nT, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	           
        return "Элемент удален!";
    }
}
