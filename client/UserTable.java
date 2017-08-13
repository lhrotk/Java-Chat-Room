package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserTable {
	private JButton fresh;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane panel;
    String name;
    JPopupMenu menu;
    JMenuItem item;
    
    UserTable(int x, int y, int z, int w){
    	fresh = new JButton("Fresh");
    	String[] columnNames = {"Users", "PORT", "IP"};
    	model = new DefaultTableModel(null, columnNames);
    	table = new JTable(model);
    	panel = new JScrollPane(table);
    	panel.setBounds(x,y,z,w);
    	
    }
    
    public JScrollPane getTable(){
    	return this.panel;
    }
    
    public JButton getButton(){
    	return fresh;
    }
    
    public void resetTable(){
    	model.setRowCount(0);
    }
    
    public void resetName(final String name){
    	this.name = name;
    	menu = new JPopupMenu();
    	item = new JMenuItem();
    	item.addActionListener(new java.awt.event.ActionListener() {  
            public void actionPerformed(java.awt.event.ActionEvent evt) {  
            	int select = table.getSelectedRow();
            	if(select == -1){
            		return;
            	}else{
            		 String to_whom = (String) table.getValueAt(select, 0);
            		 //System.out.println(to_whom);
            		 //System.out.println(name+"1");
            		 String port = (String) table.getValueAt(select, 1);
            		 //System.out.println(port);
            		 String ip = (String) table.getValueAt(select, 2);
            		 if(to_whom.equals(name))
            			 return;
            		 try {
						Socket socket = new Socket(ip, Integer.parseInt(port));
						System.out.println(port);
						p2p_sender ppp = new p2p_sender(socket,true, name, to_whom);
						Thread t = new Thread(ppp);
						t.start();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            }  
        });  
    	item.setText("chat!");
    	menu.add(item);
    	table.addMouseListener(new java.awt.event.MouseAdapter(){
    		public void mouseClicked(java.awt.event.MouseEvent evt){
    			if(evt.getButton() == java.awt.event.MouseEvent.BUTTON3){
    				int focusedRowIndex = table.rowAtPoint(evt.getPoint());
    				if(focusedRowIndex == -1){
    					return;
    				}
    				table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
    				if(!table.getValueAt(focusedRowIndex, 0).equals(name))
    					menu.show(table, evt.getX(), evt.getY());
    			}
    		}
    	});
    }
    
    public void addRow(String name,String port, String IP){
    	model.addRow(new String[]{name, port, IP});
    }
}
