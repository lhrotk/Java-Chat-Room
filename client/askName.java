package client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class askName extends JFrame{
	private JLabel message = new JLabel("Choose a name:");
	private JTextArea name = new JTextArea();
	public	JButton enter = new JButton("Enter!");
	public	JButton leave = new JButton("LEAVE");
	askName(){
		setLayout(null);
		setSize(300,150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Enter Your Name");
        
        message.setBounds(10, 10, 280, 20);
        name.setBounds(10, 35, 280, 20);
        enter.setBounds(60, 75, 80, 20);
        leave.setBounds(160, 75, 80, 20);
        
        add(message);
        add(name);
        add(enter);
        add(leave);
	}
	
	public void resetLabel(){
		message.setText("this name is already existing");
		name.setText("");
	}
	
	public String getName(){
		return name.getText();
	}
}
