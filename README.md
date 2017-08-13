# Java-Chat-Room

A simple java chat room project with UI

## Functionality

This program supports only plain text chatting. All users online are in one chatting group.

Users can find all others online by click the fresh button on main frame. They can also start private dialog with any others online users by right click their name bar.

## How to Run

compile programs under server and client folder individually with javac command. run with 'java server.loader' first and run 'java client.loader' to create one client at a time.

To get start, client must enter the address of the machine your server program is running on. If you run all programs in one computer, you should enter 127.0.0.1 and default port 23596. The port number can be changed in file SocketServer.java.

![image](https://github.com/lhrotk/Java-Chat-Room/blob/master/screenshots/address.png)

Then the system ask for your user name. Pick any name your like which is different from names of current online users.

![image](https://github.com/lhrotk/Java-Chat-Room/blob/master/screenshots/chooseName.png)

In main frame, you can chat with group members. Press refresh button to find all online users. 

![image](https://github.com/lhrotk/Java-Chat-Room/blob/master/screenshots/mainFrame.png)

If you want a private conversation with one user, right click his name bar and send a request. The user who is invited to a private conversation get a window prompt up on the screen asking wether to accept conversation request.

![image](https://github.com/lhrotk/Java-Chat-Room/blob/master/screenshots/chatRequest.png)


![image](https://github.com/lhrotk/Java-Chat-Room/blob/master/screenshots/privateChat.png)
