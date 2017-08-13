package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class mainFrame extends JFrame {
	private JTextArea input=new JTextArea(20,21);
    private JTextArea show=new JTextArea(20,21);
    private JScrollPane scroll_in=new JScrollPane(input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JScrollPane scroll_out = new JScrollPane(show,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    public JButton send = new JButton("Send");
    public JButton exit = new JButton("Log Out");
    private JScrollPane table = null;
    public JButton fresh_button;
    public UserTable temp;
    String name;
    
    public String getInput(){
    	String result = input.getText();
    	input.setText("");
    	return result;
    }
    
    public void addMessage(String str){
    	show.append(str);
    	show.setCaretPosition(show.getText().length());
    }
    
	mainFrame(String name){
		//set the frame's attribute
		this.name = name;
		setTitle(name);
		setLayout(null);
		setSize(600,520);
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Java Chatting Group");
        //set the testarea's attribute
        show.setEditable(false);
        input.setEditable(true);
        show.setLineWrap(true);
        input.setLineWrap(true);
        scroll_in.setBounds(10,320,580,130);
        scroll_out.setBounds(10,10,300,300);
        this.add(scroll_in);
        this.add(scroll_out);
        //set button
        exit.setBounds(500,465,90,20);
        this.add(exit);
        send.setBounds(400,465,90,20);
        this.add(send);
        //construct online user list
        //to be changed
        temp = new UserTable(320,10,270,280);
        table = temp.getTable();
        this.add(table);
        fresh_button = temp.getButton();
        fresh_button.setBounds(320, 290, 270, 20);
        this.add(fresh_button);
		
	}
	
	public void setName(String name){
		setTitle(name);
        temp.resetName(name);
	}
}
