package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class oneServer implements Runnable {
	
	SuperSock socket;
	ConnectionInfo info;
	String name;
	BufferedReader in;
	PrintWriter writer;
	boolean status;
	String content;
	ArrayList<String> list;
	ArrayList<SuperSock> userList;
	
	public oneServer(SuperSock socket, ConnectionInfo info) {
		super();
		this.socket = socket;
		// for who connected
		this.info = info;
		status = false;
		// precondition for join to chat room: is the name valid?
	}
	@Override
	public void run() {
		try{
			try {
				//get input and output stream
				in = new BufferedReader(new InputStreamReader(socket.getSocket().getInputStream()));
				writer = new PrintWriter(socket.getSocket().getOutputStream());
			} catch (IOException e) {

				System.out.println("Can't get io stream: " + e);
				socket.getSocket().close();
				return;
			}
			
			try{
				while(!status){ 
					//ask the client for a valid user name first
					// check if the name is already in hashtable info, no then give the permission to join
					name = in.readLine();
					System.out.println(name);
					if(!info.addUser(name, (SuperSock)socket)){
						writer.println("existing user name");
						writer.flush();
					}else{
						writer.println("login success");
						writer.flush();
						info.change_listener(name, Integer.parseInt(in.readLine()));
						status = true;
					}
				}
				
				while(!(content = in.readLine()).equals("LEAVE")){
					// LIST
					if(content.equals("LIST")){
						list = info.checkUser();
						// get all the online users' id into a array
						//write to the user who command LIST
						String number_of_users = list.size() + " users online";
						writer.println(number_of_users); 
						for(Iterator it = list.iterator();it.hasNext();){
				             String member = (String) it.next();
				             writer.println(member);
				             writer.println(info.check_SuperSock(member).getListening_port());
				             writer.println(info.check_SuperSock(member).getSocket().getInetAddress());
				        }
						writer.flush();
					}else{ 
						//BROADCAST A NONE 'LIST/LEAVE' MESSAGE TO OTHERS
						userList = info.broadCast(this.name);
						//get all the users' name list except the commander
						
						//System.out.println(content);
						//System.out.println(userList.size());
						for(int i = 0; i < userList.size(); i++){
							//Broadcast to others
							 System.out.println(i);
							 PrintWriter temp = new PrintWriter(userList.get(i).getSocket().getOutputStream());
				             temp.println(name + " : \n\t" + content);
				             temp.flush();
				        }
					}
				}
				
				//LEAVE: close socket and io
				System.out.println("out!");
				info.deleteUser(this.name);
				try {
					writer.close();
					in.close();
					socket.getSocket().close();
					return;
				} catch (IOException e1) {
				}
			} catch(IOException e){
				System.out.println("Runtime IO Error" + e);
				socket.getSocket().close();
			}
		}catch(Exception e){
			System.out.println("oneServer thread Error: "+ e);
			info.deleteUser(name);
		}
	}
	
}
