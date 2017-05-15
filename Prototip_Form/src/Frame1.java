import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.glassfish.jersey.client.ClientConfig;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.ConnectException;
import java.net.URI;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import org.glassfish.jersey.client.ClientConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Frame1 {
	public static JComboBox<String> comboBox;
	public static String systemIndex = "";
	public static DefaultTableModel model;
	public static DefaultTableModel model1;
 
	public static void main(String[] args) {

					Login window = new Login();
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
	class Frame extends JFrame {

		
		 private JFrame frame, frame1;
		    
		    private JButton  open, add, delete,deletebus, deleteecu, pac, otchot, but, but1;
		    private JToolBar toolBar;
		    private JScrollPane scroll,scroll1;
		    private JTable books,books1;
		    private JComboBox author;
		    private JTextField bookName;
		    private JButton filter;
		    private JLabel sortingLabel;
		    String elements[];
		    Object headers[],headers1[];
		    Object data[][],data1[][];
		    String sorting[];
		    String str = new String("");
		    JLabel b,b1, b2;
		@SuppressWarnings("null")
		public Frame() {
			frame = new JFrame("ModEAS");
			frame.setSize(1050, 400); ///1000
	        frame.setLocation(100,100);
	        frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);      
		
			ScrollPane sc = new ScrollPane();
			
	        //Создание кнопок и прикрепление иконок
			toolBar = new JToolBar("Панель инструментов");
        
	        frame.setLayout(new BorderLayout());
	        frame.add(toolBar, BorderLayout.NORTH);	        

	        add = new JButton("Добавить систему");
	        //add.setToolTipText("Добавить компонент");
	        toolBar.add(add);
	        frame.setLayout(new BorderLayout());
	        frame.add(toolBar, BorderLayout.NORTH);

	        delete = new JButton("Удалить систему");
	       // delete.setToolTipText("Удалить компонент");
	        toolBar.add(delete);
	        frame.setLayout(new BorderLayout());
	        frame.add(toolBar, BorderLayout.NORTH);
     
	        pac = new JButton("Просмотреть компоненты");
	        pac.setToolTipText("Сведения о компонентах");
	        toolBar.add(pac);
	        frame.setLayout(new BorderLayout());
	        frame.add(toolBar, BorderLayout.NORTH);
        
	        otchot=new JButton("Отчёт");
	        otchot.setToolTipText("Графики");
	       // toolBar.add(otchot);
	        frame.setLayout(new BorderLayout());
	        frame.add(toolBar, BorderLayout.NORTH);
	        
	        //request to server
	      	ClientConfig config = new ClientConfig();
	      	Client client = ClientBuilder.newClient(config);
	      	
	        WebTarget target = client.target(getBaseURI());
	        String Response = "";
	        try{
	        	Response = target.path("rest").path("getinfo").path("system").path("1").path("1").request().accept(MediaType.TEXT_PLAIN).get(String.class);
	        }
	        catch(ProcessingException e){
	        	JLabel countLabel = new JLabel("Нет подключения к серверу!"); 
				JOptionPane.showMessageDialog(null, countLabel);
	        }
	        System.out.println(Response);
	        String dataS [] = Response.split("-");
         
	         JPanel panel = new JPanel();
	         frame.add(panel, BorderLayout.CENTER);
	         String[] elements = null;
	         if(dataS.length>1){
	        	 elements = new String[dataS.length-1];
		         for(int i=1; i<dataS.length; i++){
		        	elements[i-1]=dataS[i];
		            System.out.println( elements[i-1]);
		         }
	         }
	         Frame1.comboBox = new JComboBox(elements);
	         Frame1.comboBox.setBounds(149, 31, 200, 39); ///111
	         Frame1.comboBox.setAlignmentX(LEFT_ALIGNMENT);
	         panel.add(Frame1.comboBox,BorderLayout.CENTER);
	         //comboBox.setEditable(true);
         
	         comboBoxListener cbL = new comboBoxListener();
	         Frame1.comboBox.addActionListener(cbL);
         
	         /*EditCBListener editCB = new EditCBListener();
	         comboBox.addItemListener(editCB);*/
          
	         Box mainBox = Box.createVerticalBox();
	         mainBox.setBorder(new TitledBorder("BUS"));
	         Box box1 = Box.createHorizontalBox();
	 		 b = new JLabel("BUS Parameters");
	      	 box1.add(b);
	 		 headers = new Object[]{"№", "Name", "Speed"};
	         Object[][] data = null;
 		
	         Frame1.model = new DefaultTableModel(data, headers);
	         books = new JTable(Frame1.model);
	         scroll = new JScrollPane(books);
	         books.setGridColor(Color.BLUE);
	         Box box2 = Box.createHorizontalBox();
	 		 box2.add(scroll);
	 		 Box boxbut = Box.createHorizontalBox();
	 		 but = new JButton("Добавить");
	 		 boxbut.add(but);
	 		 mainBox.add(box1);
	 		 mainBox.add(box2);
	 		 mainBox.add(boxbut);
	 		deletebus = new JButton("Удалить");
			 boxbut.add(deletebus);
			 //mainBox.add(deletebus);
	 		 frame.add(mainBox, BorderLayout.EAST);
			 
			 books.setCellSelectionEnabled(false);

			 Box mainBox1 = Box.createVerticalBox();
			 mainBox1.setBorder(new TitledBorder("ECU"));
	         Box box3 = Box.createHorizontalBox();
	 		 b1 = new JLabel("ECU Parameters");
	      	 box3.add(b1);
	         headers1 = new Object[]{"№", "Name"};
	         Object[][] data1 = null;
		    
	         Frame1.model1 = new DefaultTableModel(data1, headers1);
	         books1 = new JTable(Frame1.model1);
	         scroll1 = new JScrollPane(books1);
	         books1.setGridColor(Color.BLUE);
	         //Размещение таблицы с данными
	         Box box4 = Box.createHorizontalBox();
	 		 box4.add(scroll1);
	 		 Box boxbut1 = Box.createHorizontalBox();
	 		 but1 = new JButton("Добавить");
	 		 boxbut1.add(but1);
	 		 mainBox1.add(box3);
			 mainBox1.add(box4);
			 mainBox1.add(boxbut1);
			 
			 deleteecu = new JButton("Удалить");
			 boxbut1.add(deleteecu);
			 //mainBox1.add(deleteecu);
			 
			 frame.add(mainBox1, BorderLayout.WEST);
		 
			 EditTableListener editTableListener = new EditTableListener();
			 Frame1.model.addTableModelListener(editTableListener);
			 Frame1.model1.addTableModelListener(editTableListener);		 
		 
	         DDDD AD = new DDDD();
	         pac.addActionListener(AD);
	         add.addActionListener(AD);
	         but1.addActionListener(AD);
	         but.addActionListener(AD);
	         delete.addActionListener(AD);
	         deleteecu.addActionListener(AD);
	         deletebus.addActionListener(AD);

	}
		
	private URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/called_com.vogella.jersey.jaxb").build();
    }
	
	class comboBoxListener implements ActionListener { 
		public void actionPerformed(ActionEvent event) { 
			int f1=0, f2=0;
			if (Frame1.comboBox.getSelectedIndex()>=0){
				String sysName = Frame1.comboBox.getSelectedItem().toString();
				
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				System.out.println(sysName);
				WebTarget target = client.target(getBaseURI());
				String Response = "";
				try{
					Response = target.path("rest").path("getinfo").path("system").path("0").path(sysName).request().accept(MediaType.TEXT_PLAIN).get(String.class);
				}
				catch(ProcessingException e){
			        	JLabel countLabel = new JLabel("Возникли проблемы!"); 
						JOptionPane.showMessageDialog(null, countLabel);
			        }
				System.out.println(Response);
				 Frame1.systemIndex = Response;      
				//очистить, если не пустая
				while(Frame1.model.getRowCount() > 0){
					Frame1.model.removeRow(0);
				}
				while(Frame1.model1.getRowCount() > 0){
					Frame1.model1.removeRow(0);
				}
				        
				String Response1 = "";
				try{
					Response1= target.path("rest").path("getinfo").path("bus").path(Response).request().accept(MediaType.TEXT_PLAIN).get(String.class);
				}
				
				catch(ProcessingException e){
		        	JLabel countLabel = new JLabel("Возникли проблемы!"); 
					JOptionPane.showMessageDialog(null, countLabel);
		        }
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
						Frame1.model.addRow(dataM[i].split(":"));
					} 
				}
				else{
					f1=1;
				} 	    	   	       
		 	    try{  
		 	    	Response1 = target.path("rest").path("getinfo").path("ecu").path(Response).request().accept(MediaType.TEXT_PLAIN).get(String.class);
				}
				catch(ProcessingException e){
		        	JLabel countLabel = new JLabel("Возникли проблемы!"); 
					JOptionPane.showMessageDialog(null, countLabel);
		        }
				System.out.println(Response1);
				String dataECU [] = Response1.split("-");
				System.out.println(dataECU.length);
				if(dataECU.length>1){
					for(int i =1; i<dataECU.length;i++){
						System.out.println(dataECU[i]);
					}
					//data1 = new Object [dataECU.length-1][2];
		
					for(int i = 1; i<dataECU.length;i++){
						//data1[i-1] = dataECU[i].split(":");
						Frame1.model1.addRow(dataECU[i].split(":"));
					} 
				}
				else{
					f2=1;
				}
			       
				if(f1==1 && f2==1){
					JLabel countLabel = new JLabel("Нет элементов для " + sysName); 
					JOptionPane.showMessageDialog(null, countLabel);
				} 
				else if(f1==1){
					JLabel countLabel = new JLabel("Нет элементов BUS для " + sysName); 
					JOptionPane.showMessageDialog(null, countLabel);
				} 
				else if (f2==1) {
					JLabel countLabel = new JLabel("Нет элементов ECU для " + sysName); 
					JOptionPane.showMessageDialog(null, countLabel);
				}	
			}
		} 		
	}
		
	/*class EditCBListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent ev) {
			if(ev.getSource() == comboBox){
				int rowIndex = comboBox.getSelectedIndex();
				if(rowIndex>=0){
					String sys_name=(String) comboBox.getSelectedItem();
					System.out.println("sys_name = " + sys_name);
			//books1.getSelectionModel().clearSelection();
			 ClientConfig config = new ClientConfig();
		      Client client = ClientBuilder.newClient(config);
		        WebTarget target = client.target(getBaseURI());
		        
		        	//model.removeRow(rowIndex);		        				        
		        String result = target.path("rest").path("edit").path(sys_name).request().accept(MediaType.TEXT_PLAIN).get(String.class);
		        JLabel countLabel = new JLabel(result); 
	             JOptionPane.showMessageDialog(null, countLabel);   
			}
			}
			
		}	
		
	}
*/
	class EditTableListener implements TableModelListener{
		@Override
		public void tableChanged(TableModelEvent ev) {
			if(ev.getSource() == Frame1.model){
				int rowIndex = books.getEditingRow();//getSelectedRow();
				if(rowIndex>=0){
					String bus_id=(String) Frame1.model.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
					String bus_name=(String) Frame1.model.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);
					String bus_speed = (String) Frame1.model.getValueAt(rowIndex,2);
			
					//books1.getSelectionModel().clearSelection();
					ClientConfig config = new ClientConfig();
					Client client = ClientBuilder.newClient(config);
					WebTarget target = client.target(getBaseURI());
		        
		        	//model.removeRow(rowIndex);
					try{
						String result = target.path("rest").path("edit").path("bus").path(bus_name).path(bus_speed).path(bus_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					}
					catch(ProcessingException e){
			        	JLabel countLabel = new JLabel("Проверьте введенные данные!"); 
						JOptionPane.showMessageDialog(null, countLabel);
			        }
					//JLabel countLabel = new JLabel(result); 
					//JOptionPane.showMessageDialog(null, countLabel);   
				}
			}
			if(ev.getSource() == Frame1.model1){
				int rowIndex = books1.getEditingRow();//getSelectedRow();
				if(rowIndex>=0){
					String ecu_id=(String) Frame1.model1.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
					String ecu_name=(String) Frame1.model1.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);			
			
					//books1.getSelectionModel().clearSelection();
					ClientConfig config = new ClientConfig();
					Client client = ClientBuilder.newClient(config);
					WebTarget target = client.target(getBaseURI());
		        
		        	//model1.removeRow(rowIndex);	
					try{
						String result = target.path("rest").path("edit").path("ecu").path(ecu_name).path(ecu_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					}
					catch(ProcessingException e){
			        	JLabel countLabel = new JLabel("Проверьте введенные данные!"); 
						JOptionPane.showMessageDialog(null, countLabel);
			        }
					//JLabel countLabel = new JLabel(result); 
					//JOptionPane.showMessageDialog(null, countLabel);   
				}
			}
		}
	}
	
	class DDDD implements ActionListener {
		public void actionPerformed(ActionEvent ev){
			 if (ev.getSource() == pac) {                
	                if (books1.getSelectedRow() != -1) {
	                	Edit_cpu oop = new Edit_cpu((String) Frame1.model1.getValueAt(books1.getSelectedRow(), 0),(String) Frame1.model1.getValueAt(books1.getSelectedRow(), 1));
	                   // oop.setVisible(true);
	                } 
	                else {
	                    JOptionPane.showMessageDialog(frame, "Вы не выбрали ECU");
	                }
			 }
			 
			 if (ev.getSource() == but1) {
				 if(Frame1.systemIndex.equals(null)){
					 JOptionPane.showMessageDialog(frame, "Вы не выбрали систему!");
				 }
				 else{
					 AddEcu oecu = new AddEcu();
				 }
			 }
             if (ev.getSource() == but) {
            	 if(Frame1.systemIndex.equals("")){
					 JOptionPane.showMessageDialog(null, "Вы не выбрали систему!");
				 }
				 else{
					 AddBus oecu = new AddBus();
				 }
 			 }
             if (ev.getSource() == add) {
            	 AddSyst ns = new AddSyst();
            	 
  			 }
			 
			 if (ev.getSource() == deleteecu) {
				 System.out.println("delecu");
				 if (books1.getSelectedRow() != -1) {
					 String ecu_id;
					 int rowIndex = books1.getSelectedRow();
					 if(rowIndex>=0){
						 ecu_id=(String) Frame1.model1.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
								//bus_name=(String) model1.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);						
						 System.out.println("ID = " + ecu_id);
						//books1.getSelectionModel().clearSelection();
						 ClientConfig config = new ClientConfig();
						 Client client = ClientBuilder.newClient(config);
						 WebTarget target = client.target(getBaseURI());
					        
						 Frame1.model1.removeRow(rowIndex);	
						 String result = "";
						 try{
							 result = target.path("rest").path("delitem").path("ecu").path(ecu_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					 	}
						 catch(ProcessingException e){
					        	JLabel countLabel = new JLabel("Возникли проблемы при удалении!"); 
								JOptionPane.showMessageDialog(null, countLabel);
					        }
						 JLabel countLabel = new JLabel(result); 
						 JOptionPane.showMessageDialog(null, countLabel);   
					 }
					
				 }
				 else {
					 JLabel countLabel = new JLabel("Не выбран элемент!"); 
		             JOptionPane.showMessageDialog(null, countLabel);  
				 }
			 }
				 
				 if (ev.getSource() == deletebus) {
					 System.out.println("delbus");
					 if (books.getSelectedRow() != -1){
						 String bus_id;
						 int rowIndex = books.getSelectedRow();
						 if(rowIndex>=0){
							 bus_id=(String) Frame1.model.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
							//bus_name=(String) model1.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);												
							//books1.getSelectionModel().clearSelection();
							 ClientConfig config = new ClientConfig();
						     Client client = ClientBuilder.newClient(config);
						     WebTarget target = client.target(getBaseURI());
						        
						     Frame1.model.removeRow(rowIndex);	
						     String result ="";
						     try{
						     	result = target.path("rest").path("delitem").path("bus").path(bus_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
						     }
						     catch(ProcessingException e){
						        	JLabel countLabel = new JLabel("Возникли проблемы при удалении!"); 
									JOptionPane.showMessageDialog(null, countLabel);
						        }
						     JLabel countLabel = new JLabel(result); 
						     JOptionPane.showMessageDialog(null, countLabel);   
						 }
					 } 
					 else {
						 JLabel countLabel = new JLabel("Не выбран элемент!"); 
			             JOptionPane.showMessageDialog(null, countLabel);  
					 }
				 }
				 
				 if (ev.getSource() == delete) { 
					 System.out.println("delecu");
					 if (Frame1.comboBox.getSelectedIndex()>=0){
						 //int index = comboBox.getSelectedIndex();
						 String sysName = Frame1.comboBox.getSelectedItem().toString();				
						 ClientConfig config = new ClientConfig();
					     Client client = ClientBuilder.newClient(config);
					     System.out.println(sysName);
					     WebTarget target = client.target(getBaseURI());
					     String Response = target.path("rest").path("getinfo").path("system").path("0").path(sysName).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					     System.out.println(Response);
					     String result = target.path("rest").path("delitem").path("system").path(Response).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					     System.out.println(result);
					     while(Frame1.model.getRowCount() > 0){
					    	 Frame1.model.removeRow(0);
					     }
					     while(Frame1.model1.getRowCount() > 0){
					    	 Frame1.model1.removeRow(0);
					     }		
					 	
				        Response = target.path("rest").path("getinfo").path("system").path("1").path("1").request().accept(MediaType.TEXT_PLAIN).get(String.class);
				        System.out.println(Response);
				        String dataS [] = Response.split("-");
			         
				        if(Frame1.comboBox.getItemCount()>0){
				        	 Frame1.comboBox.removeAllItems();
				         }
				         JPanel panel = new JPanel();
				         frame.add(panel, BorderLayout.CENTER);
				         String[] elements = null;
				         if(dataS.length>1){
				        	 elements = new String[dataS.length-1];
					         for(int i=1; i<dataS.length; i++){
					        	 Frame1.comboBox.addItem(dataS[i]);
					            System.out.println( elements[i-1]);
					         }
				         }
				        
				        
				         //Frame1.comboBox = new JComboBox(elements);
				        
					     //Frame1.comboBox.remove(Frame1.comboBox.getSelectedIndex());//(index);
					     JLabel countLabel = new JLabel(result); 
					     JOptionPane.showMessageDialog(null, countLabel);   	 
					 }
					 else {
						 JLabel countLabel = new JLabel("Не выбран элемент!"); 
			             JOptionPane.showMessageDialog(null, countLabel);  
					 }
				 }
				
			 }
		}
	}
