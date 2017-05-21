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
        public String getHTML(@PathParam("nameT") String nT, @PathParam("name") String n, @PathParam("parentId") String par_id) throws ClassNotFoundException, SQLException {
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
        		result=n_H2.addtoCORE(connection, n, Integer.parseInt(par_id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка!";
			}
        	}
        	else if(nT.equals("ecu")){
        		try {
        			result=	n_H2.addtoECU(connection, n, Integer.parseInt(par_id));
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return "Ошибка!";
    			}
        	}
        	else if(nT.equals("users")){
        		try {
        			result=	n_H2.addtoUSERS(connection, n, par_id);
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
        @Path("{nameT}/{name}/{lenorspeed}/{parentId}")
        public String getHTML(@PathParam("nameT") String nT, @PathParam("name") String n, @PathParam("lenorspeed") int ls, @PathParam("parentId") int par_id) throws ClassNotFoundException, SQLException {
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
        	if(nT.equals("bus")){
	        	try {
	        		result=	n_H2.addtoBUS(connection, n, ls, par_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "Ошибка!";
				}
        	}
        	else if(nT.equals("frame")){
        		try {
        			result=	n_H2.addtoFRAME(connection, n,ls, par_id);
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
        @Path("{name}/{userId}")
        public String getHTML(@PathParam("name") String n, @PathParam("userId") int uId) throws ClassNotFoundException, SQLException {
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
        		result=	n_H2.addtoSYSTEM(connection, n, uId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка!";
			}  	     		
        	 
               
                return result;
        }
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("{name}/{type}/{offset}/{lenght}/{period}/{parentId}")
        public String getHTML(@PathParam("name") String n, @PathParam("type") String t,@PathParam("offset") int of, @PathParam("lenght") int l,@PathParam("period") int p, @PathParam("parentId") int par_id) throws ClassNotFoundException, SQLException {
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
        		result=n_H2.addtoTASK(connection, n,t,of,l,p, par_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Ошибка!";
			}
        	
               
                return result;
        }


}