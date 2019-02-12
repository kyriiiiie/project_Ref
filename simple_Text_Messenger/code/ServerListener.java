
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ServerListener extends Thread {
    String ip;
    int port;
    ServerSocket serverSocket = null;
    Scanner ss;
    boolean ifQ;
    
    
	public ServerListener(String aIp, int aPort,Scanner s) {
		ip = aIp;
		port = aPort;
	}
	
	
	
	public void run() {
        try {
        	// initialization
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(ip,port));	
			
			ChatManager cm = ChatManager.getChatManager();
			
            while (true) {
                // block
      
                Socket socket = serverSocket.accept();
          
                ChatSocket cs= new ChatSocket(socket);
                cs.start();
                cm.add(cs);
                
                
                
            }
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
        	try {
				serverSocket.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
        }
    }
}

//1. when client Quit, both side show: java.net.SocketException:Connection reset
//2. server still can receive message, show   java.net.SocketException:Connection reset by peer: socket write error
//3. no actual quit for server
