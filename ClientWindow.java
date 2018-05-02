import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class ClientWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Client client;
	private JTextField textField;
	public ClientWindow() {
		initialize();
		client = new Client();
		client.connectToServer();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				client.closeClient();
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				client.closeClient();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JComboBox comboBox = new JComboBox(Station.names);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = comboBox.getSelectedItem().toString();
				client.SendStopInStationToServer(text);
				int rand=(int)(Math.random()*15);
				textField.setText("the bus arrives in "+rand+" minutes" );
				textField.setVisible(true);
			}
		});
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		panel.add(btnNewButton);
		textField.setVisible(true);
		
	}

}
