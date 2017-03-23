package com.vogella.jersey.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Todo {
        private String summary,description;
        private String [] core = {"111","222"}, bus = {"333","444"};

        public String getSummary() {
                return summary;
        }
        public void setSummary(String summary) {
                this.summary = summary;
        }
        public String getDescription() {
                return description;
        }
        public void setDescription(String description) {
                this.description = description;
        }
        public String getCoresCPU(String id) {
        	if(id.equals("CPU 1"))
            {
            	return core[0];
            }
            else
            {
            	return core[1];
            }
    }
        public String getBusCPU(String id) {
        	if(id.equals("CPU 1"))
            {
            	return bus[0];
            }
            else
            {
            	return bus[1];
            }
    }
        
        public void setCore(String id,String coren) {
        	if(id.equals("CPU 1"))
            {
        		this.core[0] = coren;
            }
            else
            {
            	this.core[1] = coren;
            }
        	
    }
        public void setBus(String id,String  busn) {
        	if(id.equals("CPU 1"))
            {
        		this.bus[0] = busn;
            }
            else
            {
            	this.bus[1] = busn;
            }
    }
   


}