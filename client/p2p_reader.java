package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class p2p_reader implements Runnable{
	p2p_FRAME frame;
	Socket socket;
	String name;
	
	p2p_reader(p2p_FRAME frame, Socket socket, String name){
		this.frame = frame;
		this.socket = socket;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
			try {
				final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while(true){
					//System.out.println("1");
					String content = in.readLine();
					//System.out.println("2");
					if(content.equals("byebyebyebye")){
						frame.dispose();
						in.close();
						socket.close();
						break;
					}else{
						frame.addMessage(name + ":\n" + "  "+ content + "\n");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}

}
