package server;


import java.util.ArrayList;
import java.util.HashMap;

/*
 * This is a collection of all connected users represent by a hashtable.
 * Key is each of the users's name and the value is each users' input stream.
 * Since the same table info is passing to every thread connected, using synchronized
 * to make sure when one client is calling the feature, the function like boradCast cannot be using by another client to prevent unexpected result.
 * 
 * The description is as the name of the function
 */




public class ConnectionInfo {
	private static HashMap<String, SuperSock> users =new HashMap<String, SuperSock>();
	
	
	public synchronized boolean addUser(String name, SuperSock writer){
		if(users.containsKey(name)){
			return false;
		}else{
			users.put(name, writer);
			return true;
		}
		
	}
	
	public synchronized ArrayList<String> checkUser(){

		ArrayList<String> list = new ArrayList<String>();
		for(String name: users.keySet()){
			list.add(name);
		}
		return list;
	}
	
	public synchronized ArrayList<SuperSock> broadCast(String sender){
		ArrayList<SuperSock> iolist = new ArrayList<SuperSock>();
		for(String name: users.keySet()){
			System.out.println(name);
			if(!name.equals(sender))
				iolist.add(users.get(name));
		}
		return iolist;
	}
	
	public synchronized void deleteUser(String name){
		users.remove(name);
	}
	
	public synchronized SuperSock check_SuperSock(String name){
		return users.get(name);
	}
	
	public synchronized void change_listener(String name,int number){
		users.get(name).setListening_port(number);
	}
}
