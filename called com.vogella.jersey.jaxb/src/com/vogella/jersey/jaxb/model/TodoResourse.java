package com.vogella.jersey.jaxb.model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/todo")
public class TodoResourse {
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("{todos}/{month}")
        public String getHTML(@PathParam("todos") int id, @PathParam("month") String name) {
                Todo todo = new Todo();
                if(id == 12)
                {
                	todo.setSummary("Success " + 0);
                	todo.setDescription("Id = " + id+1 );
                }
                else
                {
                	todo.setSummary("Error");
                }
                return todo.getSummary() + " " + todo.getDescription();
        }
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("{num}")
        public String getHTML(@PathParam("num")  String id) {
                Todo todo = new Todo();
                return todo.getCoresCPU(id) + " " + todo.getBusCPU(id);
                
        }
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("{cpu}/{cor}/{bus}")
        public String getHTML(@PathParam("cpu") String id, @PathParam("cor") String c, @PathParam("bus") String b) {
                Todo todo = new Todo();

                	todo.setCore(id,c);
                	todo.setBus(id,b);
               
                return "Изменено: CPU1"+"\n"+" Cores: " + todo.getCoresCPU(id) + "\n " + "Bus: " +todo.getBusCPU(id);
        }

}