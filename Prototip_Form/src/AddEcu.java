import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class AddEcu {

	JTextField  nam, len;
	JLabel b,tl;
	JFrame frame;
	JButton okcore, notecore;
	   
	public AddEcu(){

		frame = new JFrame("Ecu");
				
		frame.setSize(250,100);
		frame.setLocation(500,100);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		Box mainBox = Box.createVerticalBox();
		
	    Box box1 = Box.createHorizontalBox();
		b = new JLabel("Name Ecu");
	 	nam = new JTextField(15);
	 	box1.add(b);
	 	box1.add(Box.createHorizontalStrut(6));
	 	box1.add(nam);
	 	 	 	 
	 	Box box2 = Box.createHorizontalBox();
	 	okcore = new JButton ("Ок");
	 	notecore = new JButton ("Отмена");
	 	box2.add(okcore);
	 	box2.add(notecore);	 	
	 	
	    mainBox.setBorder(new EmptyBorder(12,12,12,12));
	    mainBox.add(box1);
	    mainBox.add(Box.createVerticalStrut(12));
	    mainBox.add(box2);
	
		frame.add(mainBox);
		DDDD AD = new DDDD();
		okcore.addActionListener(AD);
	    notecore.addActionListener(AD);
		
	}
	private URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/called_com.vogella.jersey.jaxb").build();
	}
	
	class DDDD implements ActionListener {
	
		public void actionPerformed(ActionEvent ev){
			if (ev.getSource() == okcore) {
				 int f=0;
				String ecuName = nam.getText();
				 if(ecuName.trim().length()>0){
					ClientConfig config = new ClientConfig();
					Client client = ClientBuilder.newClient(config);
					System.out.println(ecuName);
					WebTarget target = client.target(getBaseURI());
					
					
					///////////////////////////////////////////////USER ID 
					String Response = ""; 
					try{
						Response = target.path("rest").path("add").path("ecu").path(ecuName).path(Frame1.systemIndex).request().accept(MediaType.TEXT_PLAIN).get(String.class);
						f=1;
					}
					catch(NotFoundException e){
						JLabel countLabel = new JLabel("Проверьте введенные данные!"); 
						JOptionPane.showMessageDialog(null, countLabel);
					}
					if(Response.equals("Ошибка!")){
						f=0;
						JLabel countLabel = new JLabel("Такое имя уже занято!"); 
			            JOptionPane.showMessageDialog(null, countLabel);
					}
					System.out.println(Response);
					
					while(Frame1.model1.getRowCount() > 0){
						Frame1.model1.removeRow(0);
					}
					
					String Response1 = target.path("rest").path("getinfo").path("ecu").path(Frame1.systemIndex).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					System.out.println(Response1);
					String dataM [] = Response1.split("-");
					System.out.println(dataM.length);
					if(dataM.length>1){
						for(int i =1; i<dataM.length;i++){
							System.out.println(dataM[i]);
						}
						//data = new Object [dataM.length-1][3];
			
						for(int i = 1; i<dataM.length;i++){
							//data[i-1] = dataM[i].split(":");
							Frame1.model1.addRow(dataM[i].split(":"));
						} 
						
					}
					if(f==1)
						frame.dispose();
				 }
				 else {
					 JLabel countLabel = new JLabel("Введите имя системы!"); 
		             JOptionPane.showMessageDialog(null, countLabel);
				 }
			 
			}
			if (ev.getSource() == notecore) {
				 
				 frame.dispose();
				 
			 }
			 
		}
	}	
}