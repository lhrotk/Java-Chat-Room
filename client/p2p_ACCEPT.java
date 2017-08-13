package client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class p2p_ACCEPT extends JFrame{
	public JButton accept = new JButton("Accept?");
	public JButton refuse = new JButton("Refuse!");
	JLabel hint = new JLabel();
	
	p2p_ACCEPT(String name){
		setLayout(null);
		setSize(300,150);
		setResizable(false);
		setTitle(name);
		hint.setText(name+" wants to chat with you");
		
		hint.setBounds(10,50,280,20);
		accept.setBounds(50,85,90,20);
		refuse.setBounds(160,85,90,20);
		
		add(accept);
		add(refuse);
		add(hint);
	}

}
