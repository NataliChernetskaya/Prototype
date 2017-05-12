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
import javax.ws.rs.ProcessingException;
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





public class AddTask {
	
	JTextField  nam, type, offs, leng, per;
	JLabel b,tt, to, tl, tp, tf ;
	JFrame frame;
	JButton okcore, notcore;
	   
	public AddTask (){
		
		frame = new JFrame("Task");
		
		frame.setSize(250,300);
		frame.setLocation(500,100);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		Box mainBox = Box.createVerticalBox();
		
	    Box box1 = Box.createHorizontalBox();
		b = new JLabel("Name Task");
	 	nam = new JTextField(15);
	 	box1.add(b);
	 	box1.add(Box.createHorizontalStrut(6));
	 	box1.add(nam);
	 	 
	 	Box box3 = Box.createHorizontalBox();
		tt = new JLabel("Type");
		type = new JTextField(15);
		box3.add(tt);
		box3.add(Box.createHorizontalStrut(6));
		box3.add(type);
		 
		Box box4 = Box.createHorizontalBox();
		to = new JLabel("Offset");
		offs = new JTextField(15);
		box4.add(to);
		box4.add(Box.createHorizontalStrut(6));
		box4.add(offs);
		 
		Box box5 = Box.createHorizontalBox();
		tl = new JLabel("Length");
		leng = new JTextField(15);
		box5.add(tl);
		box5.add(Box.createHorizontalStrut(6));
		box5.add(leng);
		 
		Box box6 = Box.createHorizontalBox();
		tp = new JLabel("Period");
		per = new JTextField(15);
		box6.add(tp);
		box6.add(Box.createHorizontalStrut(6));
		box6.add(per);
		 
			 	 
	 	Box box2 = Box.createHorizontalBox();
	 	okcore = new JButton ("Ок");
	 	notcore = new JButton ("Отмена");
	 	box2.add(okcore);
	 	box2.add(notcore);
	 	
	    mainBox.setBorder(new EmptyBorder(12,12,12,12));
	    mainBox.add(box1);
	    mainBox.add(Box.createVerticalStrut(12));
	    mainBox.add (box3);
	    mainBox.add(Box.createVerticalStrut(12));
	    mainBox.add (box4);
	    mainBox.add(Box.createVerticalStrut(12));
	    mainBox.add (box5);
	    mainBox.add(Box.createVerticalStrut(12));
	    mainBox.add (box6);
	    mainBox.add(Box.createVerticalStrut(12));
	    mainBox.add(box2);
	
		frame.add(mainBox);
		DDDD AD = new DDDD();
		okcore.addActionListener(AD);
	    notcore.addActionListener(AD);
		
		}

		private URI getBaseURI() {
			return UriBuilder.fromUri("http://localhost:8080/called_com.vogella.jersey.jaxb").build();
		}

		class DDDD implements ActionListener {

			public void actionPerformed(ActionEvent ev){
				if (ev.getSource() == okcore) {
					String taskName = nam.getText();
					String t = type.getText();
					String offset = offs.getText();
					String l = leng.getText();
					String period = per.getText();
					 if(taskName.trim().length()>0 && t.trim().length()>0 && offset.trim().length()>0 && l.trim().length()>0 && period.trim().length()>0){
						ClientConfig config = new ClientConfig();
						Client client = ClientBuilder.newClient(config);
						System.out.println(taskName);
						WebTarget target = client.target(getBaseURI());


						String Response = ""; 
						try{
							Response = target.path("rest").path("add").path(taskName).path(t).path(offset).path(l).path(period).path(Edit_cpu.coreId).request().accept(MediaType.TEXT_PLAIN).get(String.class);
						}
						
						catch(ProcessingException e){
				        	JLabel countLabel = new JLabel("Проверьте введенные данные!"); 
							JOptionPane.showMessageDialog(null, countLabel);
				        }
						System.out.println(Response);
						
						while(Edit_cpu.model1.getRowCount() > 0){
							Edit_cpu.model1.removeRow(0);
						}
						
						String Response1 = target.path("rest").path("getinfo").path("task").path(Edit_cpu.coreId).request().accept(MediaType.TEXT_PLAIN).get(String.class);
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
								Edit_cpu.model1.addRow(dataM[i].split(":"));
							} 
						frame.dispose();
						}
						 
					 }
					 else {
						 JLabel countLabel = new JLabel("Не все поля заполнены!"); 
			             JOptionPane.showMessageDialog(null, countLabel);
					 }
				 
				}
			if (ev.getSource() == notcore) {
				 
				 frame.dispose();
				 
			 }
		
			 
		}
	}	
}