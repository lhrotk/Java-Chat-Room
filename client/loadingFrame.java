package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class loadingFrame extends JFrame {
	private JTextArea ip=new JTextArea(20,21);
    private JTextArea port=new JTextArea(20,21);
    private JButton join = new JButton("JOIN");
    private JLabel ip_stat = new JLabel("IP:");
    private JLabel port_stat = new JLabel("PORT:");
    private JLabel message = new JLabel("Ready to join online Chatting?");
    loadingFrame(){
    	setLayout(null);
		setSize(300,180);
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ip_stat.setBounds(10, 10, 50, 20);
        port_stat.setBounds(10, 50, 50, 20);
        ip.setBounds(60,10,200,20);
        port.setBounds(60,50,200,20);
        message.setBounds(25, 100, 250, 20);
        join.setBounds(110,130,80,20);
        
        add(ip_stat);
        add(port_stat);
        add(ip);
        add(port);
        add(message);
        add(join);
        
        
    	join.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
            	try{
            		String ip_address = ip.getText();
            		String port_number = port.getText();
            		Socket socket = new Socket(ip_address, Integer.parseInt(port_number));
            		BufferedReader br = new BufferedReader(new InputStreamReader(
    						System.in));
    				//read from the console
    				
    				PrintWriter write = new PrintWriter(socket.getOutputStream());
    				//write to server

    				BufferedReader in = new BufferedReader(new InputStreamReader(
    						socket.getInputStream()));
    				// read from the server

    				//START SENDER THREAD (FOR SENDING MESSAGE)
    				senderThread s = new senderThread(br, write, in, socket);
    				
    				Thread t = new Thread(s);
    				//pack the senderThread to a real thread.
    				dispose();
    				
    				t.start();
    				
    				t.join();
            		}
            	catch(Exception e1){
            		e1.printStackTrace();
            		message.setText("Address is not valid");
            		ip.setText("");
            		port.setText("");
            	}
            }
         });
    }
}
