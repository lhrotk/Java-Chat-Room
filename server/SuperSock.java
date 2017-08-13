package server;

import java.net.Socket;

public class SuperSock{
	Socket socket;
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	int listening_port;
	public int getListening_port() {
		return listening_port;
	}
	public void setListening_port(int listening_port) {
		this.listening_port = listening_port;
	}
}
