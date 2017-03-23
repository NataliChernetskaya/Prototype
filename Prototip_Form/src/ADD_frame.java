import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

 class ADD_frame extends JFrame{
	  
	 JTextField  nam, cor, bus;
	 JComboBox comboBox, comboBox1, comboBox2;
	    JButton ok,cancel;
	public ADD_frame (){
		super("Пример с кнопками выбора, флажками и переключателями");
		setLocation(620, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Box mainBox = Box.createVerticalBox();
		
		 
	    Box box1 = Box.createHorizontalBox();
		JTextField b = new JTextField(15);
		//JComboBox comboBox = new JComboBox(elements);

		box1.add(b);
		box1.setBorder(new TitledBorder("Name"));
		
		Box box2 = Box.createHorizontalBox();
		final JTextField b1 = new JTextField(15);
		box2.add(b1);
		box2.setBorder(new TitledBorder("Length"));
		
		Box box4 = Box.createHorizontalBox();
		JButton button1 = new JButton("OK");
		JButton button2 = new JButton("Отмена");
		box4.add(button1);
		box4.add(button2); 
		
		mainBox.add(box1);
		mainBox.add(box2);
		mainBox.add(box4);
		setContentPane(mainBox);
		pack();
		
	}
}