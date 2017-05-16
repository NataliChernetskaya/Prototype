import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.ws.rs.NotFoundException;
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
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

class Edit_cpu extends JFrame{	 
	public static String ecuId = "", coreId = "", taskId = "";
	JTextField  nam, cor, bus;
	JComboBox comboBox, comboBox1, comboBox2;
	JButton button1, button2, otchot, addcore, addtask, addframe, delete, deletetask, deleteframe, prosm,prosm1 ;
	JToolBar toolBar;
	JFrame frame;
	JButton ok,cancel;
	JLabel b,b1, b2, bC, bF;
	Object headers[],headers1[],headers2[];
	Object data[][],data1[][],data2[][];
	private JScrollPane scroll,scroll1,scroll2;
	private JTable books,books1,books2;
	public static DefaultTableModel model,model1,model2;
	String bu="", cores="";
	
	public Edit_cpu (String ecu_id,String name){
		frame = new JFrame(name);
		frame.setSize(1000, 500);
        frame.setLocation(100,100);
        //frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
        ecuId = ecu_id;
		
		ScrollPane sc = new ScrollPane();
        //Создание кнопок и прикрепление иконок
		
		toolBar = new JToolBar("Панель инструментов");      
        //////////////////////
        otchot=new JButton("Отчёт");
        otchot.setToolTipText("Графики");
        toolBar.add(otchot);
        frame.setLayout(new BorderLayout());
        frame.add(toolBar, BorderLayout.NORTH);
        
         
        Box mainBox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        mainBox.setBorder(new TitledBorder("Core"));
		b = new JLabel("Core Parameters");
     	box1.add(b);
     	
		headers = new Object[]{"№", "Core"};
        /*Object[][] data = {
       	        { "1", "Task1"},
       	        { "2", "Task2"},
       	        { "3", "Task3"},
       	    };*/
		model = new DefaultTableModel(data, headers);
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI());
	        
        String Response1 = target.path("rest").path("getinfo").path("core").path(ecu_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
        System.out.println(Response1);
        String dataM [] = Response1.split("-");
        System.out.println(dataM.length);
        int f=0;
        if(dataM.length>1){
        	for(int i =1; i<dataM.length;i++){
        		System.out.println(dataM[i]);
        	}
        	//data = new Object [dataM.length-1][3];
	
        	for(int i = 1; i<dataM.length;i++){
        		//data[i-1] = dataM[i].split(":");
        		model.addRow(dataM[i].split(":"));
        	} 
        }
        else{
        	f=1;
        }
        
        books = new JTable(model);
        scroll = new JScrollPane(books);
        books.setGridColor(Color.BLUE);
        Box box2 = Box.createHorizontalBox();
		box2.add(scroll);
		
		Box boxcore = Box.createHorizontalBox();
		addcore = new JButton("Добавить");
		boxcore.add(addcore);
		
	    delete = new JButton("Удалить");
	    delete.setToolTipText("Удалить компонент");
	    boxcore.add(delete);
	    
		books.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		CoreSelectionListener csListener = new CoreSelectionListener();
		books.getSelectionModel().addListSelectionListener(csListener);
		
		Box box3 = Box.createHorizontalBox();
		JButton button1 = new JButton("OK");
		JButton button2 = new JButton("Отмена");
		box3.add(button1);
		box3.add(button2); 
		
		mainBox.add(box1);
		mainBox.add(box2);
		
		//////////////////////
		mainBox.add(boxcore);
		///////////////////////
		
		frame.add(mainBox, BorderLayout.WEST);

		Box mainBoxC = Box.createVerticalBox();
		Box mainBoxC1 = Box.createVerticalBox();
		mainBoxC1.setBorder(new TitledBorder("Task"));
        Box boxC = Box.createHorizontalBox();
		bC = new JLabel("Task Parameters");
     	boxC.add(bC);
     	headers1 = new Object[]{"№", "Name", "Type", "Offset", "Length", "Period"};
        Object[][] data1 = null;/*{
       	        { "1", "Task1", "","","","",""},
       	        { "2", "Task2", "","","","",""},
       	        { "3", "Task3", "","","","",""},
       	    };*/
        
        model1 = new DefaultTableModel(data1, headers1);
        books1 = new JTable(model1);
        scroll1 = new JScrollPane(books1);
        books1.setGridColor(Color.BLUE);
        Box boxC1 = Box.createHorizontalBox();
		boxC1.add(scroll1);
		 books1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TaskSelectionListener tsListener = new TaskSelectionListener();
		books1.getSelectionModel().addListSelectionListener(tsListener);
		            		
		Box boxtask = Box.createHorizontalBox();
		addtask = new JButton("Добавить");
		boxtask.add(addtask); 
		deletetask = new JButton("Удалить");
		deletetask.setToolTipText("Удалить компонент");
		boxtask.add(deletetask);     
		mainBoxC1.add(boxC);
		mainBoxC1.add(boxC1);
		mainBoxC1.add(boxtask);
		mainBoxC.add(mainBoxC1);
		frame.add(mainBoxC, BorderLayout.CENTER);
		
		Box mainBoxF = Box.createVerticalBox();
        Box boxF = Box.createHorizontalBox();
        mainBoxF.setBorder(new TitledBorder("Frame"));
		bF = new JLabel("Frame Parameters");
     	boxF.add(bF);
     	headers2 = new Object[]{"№", "Name", "Length"};
        Object[][] data2 = null;/*{
       	        { "1", "Frame1", ""},
       	        { "2", "Frame2", ""},
       	        { "3", "Frame3", ""},
       	    };*/
        
        model2 = new DefaultTableModel(data2, headers2);
        books2 = new JTable(model2);
        scroll2 = new JScrollPane(books2);
        books2.setGridColor(Color.BLUE);
        Box boxF1 = Box.createHorizontalBox();
		boxF1.add(scroll2);
		Box boxframe = Box.createHorizontalBox();
		addframe = new JButton("Добавить");
		boxframe.add(addframe);
		deleteframe = new JButton("Удалить");
		deleteframe.setToolTipText("Удалить компонент");
		boxframe.add(deleteframe);            		
		mainBoxF.add(boxF);
		mainBoxF.add(boxF1);
		mainBoxF.add(boxframe);
		mainBoxC.add(mainBoxF);
		
		////////////////////////
		DDDD AD = new DDDD();
        otchot.addActionListener(AD);
		addcore.addActionListener(AD);
		addtask.addActionListener(AD);
		addframe.addActionListener(AD);
		delete.addActionListener(AD);
		deletetask.addActionListener(AD);
		deleteframe.addActionListener(AD);
		///////////////////////
		
		EditTableListener editTableListener = new EditTableListener();
		model.addTableModelListener(editTableListener);
		model1.addTableModelListener(editTableListener);
		model2.addTableModelListener(editTableListener);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				dispose();
			}
		});
	     
		button2.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 frame.setVisible(false);
	             dispose();
	         }
		});
	     
		if(f==1){
	    	 JLabel countLabel = new JLabel("Нет элементов для " + name); 
             JOptionPane.showMessageDialog(null, countLabel);
	     }        		
	}
	
	private URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/called_com.vogella.jersey.jaxb").build();
	}
	
	class CoreSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			String core_id, core_name;
			int rowIndex = books.getSelectedRow();
			if(rowIndex>=0){
				core_id=(String) model.getValueAt(rowIndex, 0);
				Edit_cpu.coreId = core_id;
				core_name=(String) model.getValueAt(rowIndex, 1);
				int f=0;
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
			    WebTarget target = client.target(getBaseURI());
			        
			    while(model1.getRowCount() > 0){
			    	if(f==0){
			    		//books1.getSelectionModel().clearSelection();
			    		f=1;
			    	}
			    	model1.removeRow(0);
			    }
			    while(model2.getRowCount() > 0){
			    	model2.removeRow(0);
			    }
			        
			    String Response1 = target.path("rest").path("getinfo").path("task").path(core_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
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
			    		model1.addRow(dataM[i].split(":"));
			    	} 
			    }
			    else{
			    	//books1.clearSelection();
		 	    	 JLabel countLabel = new JLabel("Нет элементов для " + core_name); //(String) model.getValueAt(books.getSelectedRow(), 1)); 
		             JOptionPane.showMessageDialog(null, countLabel);
			    }
			}	  
		}
	}
	
	class TaskSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			String task_id, task_name;
			int rowIndex = books1.getSelectedRow();
			if(rowIndex>=0){
				task_id=(String) model1.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
				task_name=(String) model1.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);
				Edit_cpu.taskId = task_id;
				//books1.getSelectionModel().clearSelection();
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
		        WebTarget target = client.target(getBaseURI());
		        
		        while(model2.getRowCount() > 0){
		        	model2.removeRow(0);
		        }
		        
		        String Response1 = target.path("rest").path("getinfo").path("frame").path(task_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
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
	 	        		model2.addRow(dataM[i].split(":"));
	 	        	} 
	 	        }
	 	        else{
	 	        	JLabel countLabel = new JLabel("Нет элементов для " + task_name);//(String) model1.getValueAt(books1.getSelectedRow(), 1)); 
	 	        	JOptionPane.showMessageDialog(null, countLabel);
	 	        }
			}
		}
	}
	
	class EditTableListener implements TableModelListener{

		@Override
		public void tableChanged(TableModelEvent ev) {
			if(ev.getSource() == model){
				int rowIndex = books.getEditingRow();//getSelectedRow();
				if(rowIndex>=0){
					String core_id=(String) model.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
					String core_name=(String) model.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);
					//String bus_speed = (String) model.getValueAt(rowIndex,2);
					//books1.getSelectionModel().clearSelection();
					ClientConfig config = new ClientConfig();
					Client client = ClientBuilder.newClient(config);
					WebTarget target = client.target(getBaseURI());
		        
		        	//model.removeRow(rowIndex);
					String result ="";
					try{
						result = target.path("rest").path("edit").path("core").path(core_name).path(core_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					}
					catch(NotFoundException e){
			        	JLabel countLabel = new JLabel("Проверьте введенные данные!"); 
						JOptionPane.showMessageDialog(null, countLabel);
			        }
					//JLabel countLabel = new JLabel(result); 
					//JOptionPane.showMessageDialog(null, countLabel);   
					if(result.equals("ERROR")){
						JLabel countLabel = new JLabel("Такое имя уже занято!"); 
			            JOptionPane.showMessageDialog(null, countLabel);
			           
					}
				}	
			}
			
			if(ev.getSource() == model1){
				int rowIndex = books1.getEditingRow();//getSelectedRow();
				if(rowIndex>=0){
					String task_id=(String) model1.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
					String task_name=(String) model1.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);
					String task_type=(String) model1.getValueAt(rowIndex,2);
					String task_offset=(String) model1.getValueAt(rowIndex,3);
					String task_length=(String) model1.getValueAt(rowIndex,4);
					String task_period=(String) model1.getValueAt(rowIndex,5);
			
					//books1.getSelectionModel().clearSelection();
					ClientConfig config = new ClientConfig();
					Client client = ClientBuilder.newClient(config);
					WebTarget target = client.target(getBaseURI());
		        
		        	//model1.removeRow(rowIndex);	
					String result ="";
					try{
							result = target.path("rest").path("edit").path(task_name).path(task_type).path(task_offset).path(task_length).path(task_period).path(task_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					}
					catch(NotFoundException e){
			        	JLabel countLabel = new JLabel("Проверьте введенные данные!"); 
						JOptionPane.showMessageDialog(null, countLabel);
			        }
					//JLabel countLabel = new JLabel(result); 
					//JOptionPane.showMessageDialog(null, countLabel);   
					if(result.equals("ERROR")){
						JLabel countLabel1 = new JLabel("Такое имя уже занято!"); 
			            JOptionPane.showMessageDialog(null, countLabel1);
			           
					}
				}
			}
			
			if(ev.getSource() == model2){
				int rowIndex = books2.getEditingRow();//getSelectedRow();
				if(rowIndex>=0){
					String frame_id=(String) model2.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
					String frame_name=(String) model2.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);
					String frame_length=(String) model2.getValueAt(rowIndex,2);
			
					//books1.getSelectionModel().clearSelection();
					ClientConfig config = new ClientConfig();
					Client client = ClientBuilder.newClient(config);
					WebTarget target = client.target(getBaseURI());
		        
		        	//model1.removeRow(rowIndex);	
					String result ="";
					try{
						result = target.path("rest").path("edit").path("frame").path(frame_name).path(frame_length).path(frame_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					}
					catch(NotFoundException e){
			        	JLabel countLabel = new JLabel("Проверьте введенные данные!"); 
						JOptionPane.showMessageDialog(null, countLabel);
			        }
					//JLabel countLabel = new JLabel(result); 
					//JOptionPane.showMessageDialog(null, countLabel);   
					if(result.equals("ERROR")){
						JLabel countLabel1 = new JLabel("Такое имя уже занято!"); 
			            JOptionPane.showMessageDialog(null, countLabel1);
			           
					}
				}
			}
		}
	}
	class DDDD implements ActionListener {
	private Date date(int hour) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2009, Calendar.DECEMBER, 1, hour, 0, 0);
        return calendar.getTime();
    }
	public void actionPerformed(ActionEvent ev){
		if (ev.getSource() == otchot) {
				 String core_id;
				 int rowIndex = books.getSelectedRow();
				 if(rowIndex>=0){
					 core_id=(String) model.getValueAt(rowIndex,0);	
			ClientConfig config = new ClientConfig();
	      	Client client = ClientBuilder.newClient(config);
	      	
	        WebTarget target = client.target(getBaseURI());
	        String Response = "";
	        try{
	        	Response = target.path("rest").path("getinfo").path("task").path("1").path(core_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
	        }
	        catch(ProcessingException e){
	        	JLabel countLabel = new JLabel("Нет подключения к серверу!"); 
				JOptionPane.showMessageDialog(null, countLabel);
	        }
	        System.out.println(Response);
	        String taskName [] = Response.split("-");
			
	        Response = target.path("rest").path("getinfo").path("task").path("2").path(core_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
	        String nn [] =Response.split("-");
	        Response = target.path("rest").path("getinfo").path("task").path("3").path(core_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
	        String nk [] = Response.split("-");
		    Task[] t1;
		    TaskSeries [] t2;
			 /////////////////////////////////////////////////////////////////////////
				 TaskSeriesCollection s1 = new TaskSeriesCollection();
				 	t1=new Task [taskName.length-1];
				 	t2=new TaskSeries [taskName.length-1];
				 	
				 	
				 for (int k=1; k<taskName.length; k++)
				 {
					 System.out.println(k + "  "+taskName[k]+"\n");
					 	//t2[k].setDescription(taskName[k]);
					    t2[k-1] = new TaskSeries(taskName[k]);
						t1[k-1] = new Task(taskName[k], date(1), date(24));
						System.out.println(nn[k] +"  "+ nk[k]+"  "+Integer.parseInt(nn[k])+Integer.parseInt(nk[k]));
						t1[k-1].addSubtask(new Task("Task", date(Integer.parseInt(nn[k])), date(Integer.parseInt(nn[k])+Integer.parseInt(nk[k]))));
						t2[k-1].add(t1[k-1]);
	
				s1.add(t2[k-1]);
				 }
				 			 								
					JFreeChart jchart = ChartFactory.createGanttChart("Diagram", (String) model.getValueAt(books.getSelectedRow(), 1), "Tasks", s1, true, true, true);
					ChartFrame chartFrm = new ChartFrame("ModEAS diagram",jchart,true);
					chartFrm.setVisible(true);;
					chartFrm.setSize(800,500);
					chartFrm.setLocation(300,100);
					chartFrm.validate();
	            }
			 else
			 {JOptionPane.showMessageDialog(frame, "Вы не выбрали CORE");}
		 }
			 if (ev.getSource() == delete) {    
				 if (books.getSelectedRow() != -1){
					 String core_id;
					 int rowIndex = books.getSelectedRow();
					 if(rowIndex>=0){
						 core_id=(String) model.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
						 //bus_name=(String) model1.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);
						 //books1.getSelectionModel().clearSelection();
						 ClientConfig config1 = new ClientConfig();
						 Client client1 = ClientBuilder.newClient(config1);
						 WebTarget target1 = client1.target(getBaseURI());
					        
						 model.removeRow(rowIndex);	
						 while(model1.getRowCount() > 0){
							 model1.removeRow(0);
						 }
						 while(model2.getRowCount() > 0){
							 model2.removeRow(0);
						 }
						 String result = target1.path("rest").path("delitem").path("core").path(core_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
						 JLabel countLabel = new JLabel(result); 
						 JOptionPane.showMessageDialog(null, countLabel);   
					 }
				 } 
				 else {
					 JLabel countLabel = new JLabel("Не выбран элемент!"); 
		             JOptionPane.showMessageDialog(null, countLabel);  
				 }
			 }
			 if (ev.getSource() == deletetask) {
				 if (books1.getSelectedRow() != -1){
					 String task_id;
					 int rowIndex = books1.getSelectedRow();
					 if(rowIndex>=0){
						 task_id=(String) model1.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
								//bus_name=(String) model1.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);
						 //books1.getSelectionModel().clearSelection();
						 ClientConfig config1 = new ClientConfig();
					     Client client1 = ClientBuilder.newClient(config1);
					     WebTarget target1 = client1.target(getBaseURI());
					        
					     model1.removeRow(rowIndex);	
					     while(model2.getRowCount() > 0){
					    	 model2.removeRow(0);
					     }
					     String result = target1.path("rest").path("delitem").path("task").path(task_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
					     JLabel countLabel = new JLabel(result); 
					     JOptionPane.showMessageDialog(null, countLabel);   
					 }
				 }
				 else {
					 JLabel countLabel = new JLabel("Не выбран элемент!"); 
		             JOptionPane.showMessageDialog(null, countLabel);  
				 }
			 }
			if (ev.getSource() == deleteframe) { 
					 if (books2.getSelectedRow() != -1){
						 String frame_id;
						 int rowIndex = books2.getSelectedRow();
						 if(rowIndex>=0){
							 frame_id=(String) model2.getValueAt(rowIndex,0);//(books1.getSelectedRow(), 0);
									//bus_name=(String) model1.getValueAt(rowIndex,1);//(books1.getSelectedRow(), 1);
							 //books1.getSelectionModel().clearSelection();
							 ClientConfig config1 = new ClientConfig();
						     Client client1 = ClientBuilder.newClient(config1);
						     WebTarget target1 = client1.target(getBaseURI());
						        
						     model2.removeRow(rowIndex);		        				        
						     String result = target1.path("rest").path("delitem").path("frame").path(frame_id).request().accept(MediaType.TEXT_PLAIN).get(String.class);
						     JLabel countLabel = new JLabel(result); 
						     JOptionPane.showMessageDialog(null, countLabel);   
						 }
					 }
					 else {
						 JLabel countLabel = new JLabel("Не выбран элемент!"); 
			             JOptionPane.showMessageDialog(null, countLabel);  
					 }
				 }
				 if (ev.getSource() == addcore) {
					 if(Edit_cpu.ecuId.equals("")){
						 JOptionPane.showMessageDialog(null, "Вы не выбрали блок управления!");
					 }
					 else{
					 AddCore oop = new AddCore();
					 }
				 }
				 if (ev.getSource() == addtask) {
					 if(Edit_cpu.coreId.equals("")){
						 JOptionPane.showMessageDialog(null, "Вы не выбрали процессор!");
					 }
					 else{
	 				 
					 AddTask ool = new AddTask();
					 }
				 }
				 if (ev.getSource() == addframe) {
					 if(Edit_cpu.taskId.equals("")){
						 JOptionPane.showMessageDialog(null, "Вы не выбрали задачу!");
					 }
					 else{
					 AddFrame oor = new AddFrame();
					 }
				 }
			 }
		}
}
