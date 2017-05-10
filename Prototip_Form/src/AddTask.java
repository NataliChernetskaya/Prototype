import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;





public class AddTask {
	
	JTextField  nam, type, offs, leng, per, fram;
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
		 
		Box box7 = Box.createHorizontalBox();
		tf = new JLabel("Frame");
		fram = new JTextField(15);
		box7.add(tf);
		box7.add(Box.createHorizontalStrut(6));
		box7.add(fram);
	 	 
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
	    mainBox.add (box7);
	    mainBox.add(Box.createVerticalStrut(12));
	    mainBox.add(box2);
	
		frame.add(mainBox);
		DDDD AD = new DDDD();
	    notcore.addActionListener(AD);
	}
	
	class DDDD implements ActionListener {
	
		public void actionPerformed(ActionEvent ev){
			if (ev.getSource() == notcore) {
				 
				 frame.dispose();
				 
			 }
		
			 
		}
	}	
}