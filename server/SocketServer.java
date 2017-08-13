package server;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {


	@SuppressWarnings("resource")
	@Override
	public void run() {
		ConnectionInfo info = new ConnectionInfo();
		//a hashmap store online users' name and address
		try {
			// listen a port
			ServerSocket server = null;
			try {
				server = new ServerSocket(23596);
				//create the server
				System.out.println("listening......");
			} catch (Exception e) {
				System.out.println("listening error" + e);
			}
			while (true) {	
				
				
				// accept a connection
				SuperSock socket = new SuperSock();
				try {
					socket.setSocket(server.accept());
					// like scanf in C, waiting for the result
					// if have connected, then record that socket
					
					System.out.println("new connection");
				} catch (Exception e) {
					System.out.println("Accept Error:" + e);
				}
				
				//start a new thread for a new user
				//back to listening afterwards
				oneServer newServer = new oneServer(socket, info);
				Thread t = new Thread(newServer);
				t.start();
			}

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

}

