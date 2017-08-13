package client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class p2p_FRAME extends JFrame{
		public JButton send = new JButton("Send");
		public JButton quit = new JButton("Quit");
		private JTextArea input = new JTextArea();
		public JTextArea show = new JTextArea();
		private JScrollPane scroll_in=new JScrollPane(input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    private JScrollPane scroll_out = new JScrollPane(show,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    
	    p2p_FRAME(){
	    	//set the frame's attribute
			setLayout(null);
			setSize(500,550);
			setResizable(false);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //set the testarea's attribute
	        show.setEditable(false);
	        input.setEditable(true);
	        show.setLineWrap(true);
	        input.setLineWrap(true);
	        scroll_in.setBounds(10,320,480,150);
	        scroll_out.setBounds(10,10,480,300);
	        this.add(scroll_in);
	        this.add(scroll_out);
	        //set button
	        quit.setBounds(400,495,90,20);
	        this.add(quit);
	        send.setBounds(300,495,90,20);
	        this.add(send);
	    }
	    public void setName(String name){
	    	setTitle(name);
	    }
	    public String getInput(){
	    	String result = input.getText();
	    	input.setText("");
	    	return result;
	    }
	    
	    public void addMessage(String str){
	    	show.append(str);
	    	show.setCaretPosition(show.getText().length());
	    }

	    
	    public static void main(String[] args){
	    	p2p_FRAME chat = new p2p_FRAME();
	    	chat.setVisible(true);
	    }
}
