import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import org.glassfish.jersey.client.ClientConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Frame1 {
 
	public static void main(String[] args) {

					Login window = new Login();
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
	class Frame extends JFrame{

		JComboBox comboBox;
		 private JFrame frame, frame1;
		    private DefaultTableModel model,model1;
		    private JButton save, open, add, delete, pac, otchot;
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
		    org.w3c.dom.Document doc;
		@SuppressWarnings("null")
		public Frame() {
		 frame = new JFrame();
		frame.setSize(1000, 300);
        frame.setLocation(100,100);
        frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
        
		
		 ScrollPane sc = new ScrollPane();
        //Создание кнопок и прикрепление иконок
        save = new JButton("Сохранить");
        save.setToolTipText("Сохранить список компонентов");
        toolBar = new JToolBar("Панель инструментов");
        toolBar.add(save);
        frame.setLayout(new BorderLayout());
        frame.add(toolBar, BorderLayout.NORTH);

        open = new JButton("Загрузить");
        open.setToolTipText("Загрузить с бд");
        toolBar.add(open);
        frame.setLayout(new BorderLayout());
        frame.add(toolBar, BorderLayout.NORTH);

        add = new JButton("Добавить");
        add.setToolTipText("Добавить компонент");
        toolBar.add(add);
        frame.setLayout(new BorderLayout());
        frame.add(toolBar, BorderLayout.NORTH);

        delete = new JButton("Удалить");
        delete.setToolTipText("Удалить компонент");
        toolBar.add(delete);
        frame.setLayout(new BorderLayout());
        frame.add(toolBar, BorderLayout.NORTH);
     
         pac = new JButton("Просмотреть");
         pac.setToolTipText("Сведения о компонентах");
         toolBar.add(pac);
         frame.setLayout(new BorderLayout());
         frame.add(toolBar, BorderLayout.NORTH);
        
         otchot=new JButton("Отчёт");
         otchot.setToolTipText("Графики");
         toolBar.add(otchot);
         frame.setLayout(new BorderLayout());
         frame.add(toolBar, BorderLayout.NORTH);
         
         ClientConfig config = new ClientConfig();
	      Client client = ClientBuilder.newClient(config);

	        WebTarget target = client.target(getBaseURI());
	        String Response = target.path("rest").path("getinfo").path("system").path("1").path("1").request().accept(MediaType.TEXT_PLAIN).get(String.class);
	        System.out.println(Response);
	        String dataS [] = Response.split("-");
         
         JPanel panel = new JPanel();
         frame.add(panel, BorderLayout.CENTER);
         String[] elements = new String[dataS.length-1];
         for(int i=1; i<dataS.length; i++)
        	 elements[i-1]=dataS[i];
         comboBox = new JComboBox(elements);
         comboBox.setBounds(149, 31, 111, 39);
         comboBox.setAlignmentX(LEFT_ALIGNMENT);
         panel.add(comboBox,BorderLayout.CENTER);
         
         
         
         
         
          
         Box mainBox = Box.createVerticalBox();
         Box box1 = Box.createHorizontalBox();
 		 b = new JLabel("BUS Parameter");
      	 box1.add(b);
 		 headers = new Object[]{"№", "Name", "Speed"};
         Object[][] data = null;
 		//ClientConfig config = new ClientConfig();
 	      //Client client = ClientBuilder.newClient(config);

 	        //WebTarget target = client.target(getBaseURI());
 	        /*Response = target.path("rest").path("getinfo").path("bus").path("12").request().accept(MediaType.TEXT_PLAIN).get(String.class);
 	        System.out.println(Response);
 	        String dataM [] = Response.split("-");
 	       System.out.println(dataM.length);
 	       for(int i =1; i<dataM.length;i++){
	        	System.out.println(dataM[i]);
 	       }
 	       data = new Object [dataM.length-1][3];

 	        for(int i = 1; i<dataM.length;i++){
 	        	data[i-1] = dataM[i].split(":");
 	        } 
         */
         model = new DefaultTableModel(data, headers);
         books = new JTable(model);
         scroll = new JScrollPane(books);
         books.setGridColor(Color.BLUE);
         Box box2 = Box.createHorizontalBox();
 		 box2.add(scroll);
 		 mainBox.add(box1);
		 mainBox.add(box2);
		 frame.add(mainBox, BorderLayout.EAST);
		 books.setCellSelectionEnabled(false);
		 Box mainBox1 = Box.createVerticalBox();
         Box box3 = Box.createHorizontalBox();
 		 b1 = new JLabel("ECU Parameter");
      	 box3.add(b1);
         headers1 = new Object[]{"№", "Name"};
         Object[][] data1 = null;
	    
	       /*Response = target.path("rest").path("getinfo").path("ecu").path("12").request().accept(MediaType.TEXT_PLAIN).get(String.class);
	        System.out.println(Response);
	         String dataECU [] = Response.split("-");
	       System.out.println(dataECU.length);
	       for(int i =1; i<dataECU.length;i++){
	        	System.out.println(dataECU[i]);
	       }
	       data1 = new Object [dataECU.length-1][2];

	        for(int i = 1; i<dataECU.length;i++){
	        	data1[i-1] = dataECU[i].split(":");
	        } 
         */
         model1 = new DefaultTableModel(data1, headers1);
         books1 = new JTable(model1);
         scroll1 = new JScrollPane(books1);
         books1.setGridColor(Color.BLUE);
         //Размещение таблицы с данными
         Box box4 = Box.createHorizontalBox();
 		 box4.add(scroll1);
 		 mainBox1.add(box3);
		 mainBox1.add(box4);
		 frame.add(mainBox1, BorderLayout.WEST);
		 
		 
         /*JPanel panel = new JPanel();
         frame.add(panel, BorderLayout.CENTER);
         String[] elements = new String[] {"System1", "System2", "System3"};
         comboBox = new JComboBox(elements);
         comboBox.setBounds(149, 31, 111, 39);
         comboBox.setAlignmentX(LEFT_ALIGNMENT);
         panel.add(comboBox,BorderLayout.CENTER); */
         DDDD AD = new DDDD();
         pac.addActionListener(AD);
         otchot.addActionListener(AD);

		 }
		private URI getBaseURI() {
            return UriBuilder.fromUri("http://localhost:8080/called_com.vogella.jersey.jaxb").build();
    }
		class comboBoxListener implements ActionListener { 
			public void actionPerformed(ActionEvent event) { 
				 String sysName = comboBox.getSelectedItem().toString();
				 ClientConfig config = new ClientConfig();
			      Client client = ClientBuilder.newClient(config);

			        WebTarget target = client.target(getBaseURI());
			        String Response = target.path("rest").path("getinfo").path("system").path("0").path(sysName).request().accept(MediaType.TEXT_PLAIN).get(String.class);
			        System.out.println(Response);
			        
			        
			        Response = target.path("rest").path("getinfo").path("bus").path(Response).request().accept(MediaType.TEXT_PLAIN).get(String.class);
		 	        System.out.println(Response);
		 	        String dataM [] = Response.split("-");
		 	       System.out.println(dataM.length);
		 	       for(int i =1; i<dataM.length;i++){
			        	System.out.println(dataM[i]);
		 	       }
		 	       //data = new Object [dataM.length-1][3];

		 	        for(int i = 1; i<dataM.length;i++){
		 	        	//data[i-1] = dataM[i].split(":");
		 	        	model.addRow(dataM[i].split(":"));
		 	        } 
		 	        
		 	      // model = new DefaultTableModel(data, headers);
		 	       
		 	       
		 	      Response = target.path("rest").path("getinfo").path("ecu").path(Response).request().accept(MediaType.TEXT_PLAIN).get(String.class);
			        System.out.println(Response);
			         String dataECU [] = Response.split("-");
			       System.out.println(dataECU.length);
			       for(int i =1; i<dataECU.length;i++){
			        	System.out.println(dataECU[i]);
			       }
			       //data1 = new Object [dataECU.length-1][2];

			        for(int i = 1; i<dataECU.length;i++){
			        	//data1[i-1] = dataECU[i].split(":");
			        	model1.addRow(dataECU[i].split(":"));
			        } 
			        
			
			} 
			
		}


	class DDDD implements ActionListener {
		public void actionPerformed(ActionEvent ev){
			 if (ev.getSource() == pac) {
	                
	                if (books1.getSelectedRow() != -1) {
	                	Edit_cpu oop = new Edit_cpu((String) model1.getValueAt(books1.getSelectedRow(), 1));
	                   // oop.setVisible(true);
	                } else {
	                    JOptionPane.showMessageDialog(frame, "Вы не выбрали ECU");
	                }
	            }
			 if (ev.getSource() == otchot) {
	                

				 PieChart demo = new PieChart("JFreeChart: StackedXYBarChart");
			        demo.pack();
			        // И показываем
			       RefineryUtilities.centerFrameOnScreen(demo);
			       demo.setVisible(true);
				 /*DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				 dataset.setValue(10, "ECU1", "Core1");
				 dataset.setValue(10, "ECU1", "Task 1");
				 dataset.setValue(10, "ECU1", "Frame 1");
				 
				 JFreeChart chart = ChartFactory.createBarChart("Modeling ECU1", "Modeling components", "Quantity", dataset, PlotOrientation.VERTICAL,false, true,false);
				 CategoryPlot p= chart.getCategoryPlot();
				 p.setRangeGridlinePaint(Color.black);
				 
				 ChartFrame f=new ChartFrame ("ModEAS", chart); 
				 f.setVisible(true);
				 f.setSize(450,350);*/
		 
	            }
	}
		}

	}