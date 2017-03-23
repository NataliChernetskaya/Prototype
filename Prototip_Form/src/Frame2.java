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

 class Edit_cpu extends JFrame{
	 
	
	 
	 JTextField  nam, cor, bus;
	 JComboBox comboBox, comboBox1, comboBox2;
	 JButton button1, button2;
	    JButton ok,cancel;
	    JTextField b,b1, b2;
	    String bu="", cores="";
	public Edit_cpu (String c, String param){
		super("Пример с кнопками выбора, флажками и переключателями");
		setLocation(500, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Box mainBox = Box.createVerticalBox();

		 
	    Box box1 = Box.createHorizontalBox();
		b = new JTextField(c);
		//final JComboBox comboBox = new JComboBox(elements);
        b.setEditable(false);
		box1.add(b);
		//box1.add(comboBox); 
		box1.setBorder(new TitledBorder("Name"));
		for(int i=0; i<7; i++)
		{
			if(i<4){ 		
				
				cores=cores + String.valueOf(param.charAt(i));
				}
			else
				{
				
				bu = bu+ String.valueOf(param.charAt(i));
				}
			
		}
		
		System.out.println(cores);
		System.out.println(bu);
		
		
		
		
		Box box2 = Box.createHorizontalBox();
		b1 = new JTextField(15);
		final JButton but= new JButton ("Редактировать");
		box2.add(b1);
		box2.add(but); 
		box2.setBorder(new TitledBorder("Cores"));
		b1.setText(cores);
		
		Box box3 = Box.createHorizontalBox();
		 b2 = new JTextField(15);
		final JButton but1= new JButton ("Редактировать");
		box3.add(b2);
		box3.add(but1);
		box3.setBorder(new TitledBorder("Bus"));
		b2.setText(bu);
		
		Box box4 = Box.createHorizontalBox();
		JButton button1 = new JButton("OK");
		JButton button2 = new JButton("Отмена");
		box4.add(button1);
		box4.add(button2); 
		//box4.setBorder(new TitledBorder("Bus"));
		ActionListener actionListenerOk = new OkActionListener();
		button1.addActionListener(actionListenerOk);
		ActionListener actionListenerCancel = new CancelActionListener();
		button2.addActionListener(actionListenerCancel);
		
		mainBox.add(box1);
		mainBox.add(box2);
		mainBox.add(box3);
		mainBox.add(box4);
		setContentPane(mainBox);
		pack();
		
		
		////////события
        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


 	                 ADD form3 = new ADD(b1.getText());
 	                 form3.setVisible(true);
                }
       });
        
        but1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


 	                 Edit_bus form4 = new Edit_bus(b2.getText());
 	                 form4.setVisible(true);
                }
       });
        		
}
	public class OkActionListener implements ActionListener {
	     public void actionPerformed(ActionEvent e) {
	    	ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);

            WebTarget target = client.target(getBaseURI());

            String Response = target.path("rest").path("todo").path(b.getText()).path(b1.getText()).path(b2.getText()).request()
                            .accept(MediaType.TEXT_PLAIN).get(String.class);
              
            System.out.println(Response);
            JLabel countLabel = new JLabel(Response); 
            JOptionPane.showMessageDialog(null, countLabel);

           	   setVisible(false);
                  dispose();
         
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

