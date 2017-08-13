package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class senderThread implements Runnable {

	BufferedReader br;
	// console input
	BufferedReader in;
	PrintWriter write;
	Socket socket;
	String name;
	mainFrame main;
	askName ask;

	
	//constructor
	public senderThread(BufferedReader br, PrintWriter write, BufferedReader in, Socket socket) {
		super();
		this.br = br;
		this.write = write;
		this.in = in;
		this.socket = socket;
		ask = new askName();
		main = new mainFrame("");
	}

	@Override
	public void run() {
		ask.enter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
            	name = ask.getName();
            	if(name.length()==0){
            		return;
            	}
            	write.println(name);
            	write.flush();
            	try {
					if(in.readLine().equals("login success")){
						ask.resetLabel();
						ask.setVisible(false);
						main.setVisible(true);
						main.setName(name);
						receiverThread receiver = new receiverThread(in, main, name);
						Thread t = new Thread(receiver);
						t.start();
						p2p_listener ppp = new p2p_listener(socket.getLocalPort(),name);
						write.println(socket.getLocalPort()-ppp.gettry_number());
						//tem.out.println(socket.getLocalPort()-ppp.gettry_number());
						Thread l = new Thread(ppp);
						l.start();
					}else{
						ask.resetLabel();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					write.close();
					try {
						in.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						socket.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					System.exit(0);
				}
            	ask.resetLabel();
            }
         });
		
		ask.leave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
				write.close();				
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
            	ask.dispose();
            }
         });
		
		main.exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
				write.close();				
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
            	main.dispose();
            	ask.dispose();
            	System.exit(0);
            }
         });
		
		main.send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
            	String content = main.getInput();
				write.println(content);
				write.flush();
				main.addMessage(name+"(Me)"+ "\n\t" + content+"\n" );
            }
         });
		
		main.fresh_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
				write.println("LIST");
				write.flush();
            }
         });
		
		ask.setVisible(true);
		
		
	}

}
