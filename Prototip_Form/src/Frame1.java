
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
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
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.glassfish.jersey.client.ClientConfig;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Frame1 {

	 
	public static void main(String[] args) {

					Frame window = new Frame();
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

	class Frame extends JFrame{
		JComboBox comboBox;
		public Frame() {
		JFrame frame = new JFrame();
		frame.setSize(450, 250);
        frame.setLocation(100, 100);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		//frame.getContentPane().setLayout(null);
        Default def = new Default();
        frame.addWindowListener(def);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 6, 200, 100);
		frame.getContentPane().add(panel);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"ModEAS System"));
		panel.setLayout(null);
		
		 String[] elements = new String[] {"CPU 1", "CPU 2", "CPU 3"};
		 
        comboBox = new JComboBox(elements);
        
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setBounds(149, 31, 111, 39);
        comboBox.setAlignmentX(LEFT_ALIGNMENT);
        //comboBox.addActionListener(actionListener);
        panel.add(comboBox);
        
        JButton button = new JButton("Добавить");
        button.setBounds(10, 104, 105, 23);
        panel.add(button);
        
        JButton button_1 = new JButton("Редактировать");
        button_1.setBounds(125, 104, 158, 23);
        panel.add(button_1);
        
        JButton button_2 = new JButton("Удалить");
        button_2.setBounds(293, 104, 89, 23);
        panel.add(button_2);
        
        //кнопки для графиков
        JButton button_3 = new JButton("График по ...1");
        button_3.setBounds(10, 160, 200, 23);
        panel.add(button_3);
        
        JButton button_4 = new JButton("График по ...2");
        button_4.setBounds(220, 160, 200, 23);
        panel.add(button_4);

		
		//////////////события
        /*button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 String Design = comboBox.getSelectedItem().toString();
                 if (Design!=null){
                 Edit_cpu form2 = new Edit_cpu(Design);
                 form2.setVisible(true);
                             }
            }
       });*/
        ActionListener actionListenerEdit = new EditActionListener();
        button_1.addActionListener(actionListenerEdit);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                 ADD_cpu form2 = new ADD_cpu();
                 form2.setVisible(true);
                         }
       });
        
              
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		 }



class Default implements WindowListener {

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Object[] options = {"Да", "Нет!"};
        int n = JOptionPane
                .showOptionDialog(e.getWindow(), "Закрыть программу?",
                        "Подтверждение", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
        if (n == 0) {
            int i = JOptionPane
                    .showOptionDialog(e.getWindow(), "Сохранить данные?",
                            "Подтверждение", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options,
                            options[0]);
        }
        e.getWindow().setVisible(false);
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
public class EditActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
   	ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());
        String name = comboBox.getSelectedItem().toString();
        String Response = target.path("rest").path("todo").path(name).request()
                        .accept(MediaType.TEXT_PLAIN).get(String.class);
                 

        Edit_cpu form2 = new Edit_cpu(name, Response);
        form2.setVisible(true);

      //  }
}

private URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/called_com.vogella.jersey.jaxb").build();
}
    }
	}
