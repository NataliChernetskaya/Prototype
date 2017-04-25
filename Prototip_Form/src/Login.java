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
import javax.swing.border.EmptyBorder;
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

public class Login extends JFrame{

	 
	 JTextField  num, num1;
	 JComboBox comboBox, comboBox1, comboBox2;
	 JButton button1, button2, save, add, delete, prosm,prosm1 ;
	 JToolBar toolBar;
	 JFrame frame;
	 JButton ok,cancel;
	 JLabel b,b1;
	
		public Login() {
		 frame = new JFrame();
		frame.setSize(250, 150);
        frame.setLocation(500,100);
        frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
       		
		 Box mainBox = Box.createVerticalBox();
         Box box1 = Box.createHorizontalBox();
 		 b = new JLabel("Login");
      	 num = new JTextField(15);
      	 box1.add(b);
      	 box1.add(Box.createHorizontalStrut(6));
      	 box1.add(num);
      	 
      	Box box2 = Box.createHorizontalBox();
		b1 = new JLabel("Password");
     	num1 = new JTextField(15);
     	
     	box2.add(b1);
     	box2.add(Box.createHorizontalStrut(6));
     	box2.add(num1);
     	
     	Box box3 = Box.createHorizontalBox();
		button1 = new JButton("Submit");
     	box3.add(button1);
     	DDDD AD = new DDDD();
        button1.addActionListener(AD);
     	 
     	b.setPreferredSize(b1.getPreferredSize());
     	
     	mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box3);
        frame.add(mainBox);
        frame.setVisible(true);
		}
		
		class DDDD implements ActionListener {
			public void actionPerformed(ActionEvent ev){
				 String n = num.getText();
				 String n1 = num1.getText();
				 if (ev.getSource() == button1) {

		                if ( n.equals("log") && n1.equals("pas")) {
		                	Frame window = new Frame();
		                	frame.setVisible(false);
		                } else {
		                    JOptionPane.showMessageDialog(frame, "Не верно!");
		                }
		            }
				 
		}
	 }	
}

