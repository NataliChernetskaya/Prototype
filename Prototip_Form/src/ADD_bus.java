import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import java.awt.event.*;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.glassfish.jersey.client.ClientConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

 class ADD_bus extends JFrame{
	  
	 JTextField  nam, cor, bus, b, b1;
	 JComboBox comboBox, comboBox1, comboBox2;
	    JButton ok,cancel;
	public ADD_bus (){
		super("Добавить");
		setLocation(550, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Box mainBox = Box.createVerticalBox();
		
		 
	    Box box1 = Box.createHorizontalBox();
		 b = new JTextField(15);
		//JComboBox comboBox = new JComboBox(elements);

		box1.add(b);
		box1.setBorder(new TitledBorder("Name"));
		
		Box box2 = Box.createHorizontalBox();
		 b1 = new JTextField(15);
		box2.add(b1);
		box2.setBorder(new TitledBorder("Speed"));
		
		Box box4 = Box.createHorizontalBox();
		JButton button1 = new JButton("OK");
		ActionListener actionListenerOk = new OkActionListener();
		button1.addActionListener(actionListenerOk);
		JButton button2 = new JButton("Отмена");
		ActionListener actionListenerCancel = new CancelActionListener();
		button2.addActionListener(actionListenerCancel);
		box4.add(button1);
		box4.add(button2); 
		
		mainBox.add(box1);
		mainBox.add(box2);
		mainBox.add(box4);
		setContentPane(mainBox);
		pack();
		
	}
	public class OkActionListener implements ActionListener {
	     public void actionPerformed(ActionEvent e) {
	    	ClientConfig config = new ClientConfig();
             Client client = ClientBuilder.newClient(config);

             WebTarget target = client.target(getBaseURI());

             String Response = target.path("rest").path("todo").path(b.getText()).path(b1.getText()).request()
                             .accept(MediaType.TEXT_PLAIN).get(String.class);
               
             System.out.println(Response);
             JLabel countLabel = new JLabel(Response); 
             JOptionPane.showMessageDialog(null, countLabel);
             if(!Response.equals("Error " + null))
             {
            	   setVisible(false);
                   dispose();
             }         
     }

     private URI getBaseURI() {
             return UriBuilder.fromUri("http://localhost:8080/called_com.vogella.jersey.jaxb").build();
     }
	     }
	public class CancelActionListener implements ActionListener {
	     public void actionPerformed(ActionEvent e) {
	    	 setVisible(false);
             dispose();
	     }
	}
}

