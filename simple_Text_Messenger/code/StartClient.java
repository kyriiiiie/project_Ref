
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//import java.net.*;
import java.util.*;

public class StartClient extends Thread{
	private static String ip;
	private int port;
	private static boolean toQuit;
	private static Socket socket;
	private static Scanner in;

	
	
	//to control the while loop in threads
	private static void setQuit() {
		toQuit = false;
	}
	
	public StartClient(String aIp, int aPort,Scanner s) {
		ip = aIp;
		port = aPort;
		toQuit = true;
		in = s;
		try {
			socket = new Socket(ip,port);
		} catch (IOException e) {
			System.out.println("Connection failed");
		}
		System.out.println("Successfully connected! Now start chatting.");
	};
	
	//receive message from chatManager.publish
	private static class Listener extends Thread{
		private BufferedReader bReader;
		public Listener() {
			
			try {
				bReader = new BufferedReader(
				        new InputStreamReader(
				               socket.getInputStream()));
			} catch (IOException e) {
				//e.printStackTrace();
			}
			//
			System.out.println("Listener initialized");
		}
		
		@Override
		public void run() {
			
			try {
				while(true) {
					String msg = bReader.readLine();
					//after send Quit, Server send back a "Quit" to stop bReader 
					if(msg.equals("Quit"))
						break;
					else {
						System.out.println(msg);
					}
					
				}
				}
				catch(Exception e) {
					//e.printStackTrace();
					//System.out.print("Client: listener failed. ");
				}finally {
					if(bReader !=null) {
			    		try {
							bReader.close();
						} catch (IOException e) {
							System.out.print("Failed to close listener. ");
						}
			    	}
					
					//close socket here
					if(socket !=null) {
			    		try {
							socket.close();
						} catch (IOException e) {
							System.out.print("Failed to close socket. ");
						}
			    	}
				}
		}
	}

	//send messages
	private static class Speaker extends Thread{
		private PrintWriter pw;
		
		public Speaker() {
			
			try {
				pw=new PrintWriter(socket.getOutputStream(),true);
			} catch (IOException e) {
				//e.printStackTrace();
			}
			//
			System.out.println("Writer initialized");
			
		}
		
		@Override
		public void run() {
			
			    // to make scanner work
                in.nextLine();
				
				while(toQuit) {
					String s = in.nextLine();
					pw.write(s +"\r");
					pw.flush();
					if(s.equals("Quit")) 
						setQuit();
					}
				    if(!toQuit) {
				    	if(pw != null)
				    		pw.close();
				    	
				    }
				    
				    if(socket.isClosed())
				    	System.out.println("Connection break.");	
				
			
			
			System.out.println("You will quit the application, press any key.");
			in.nextLine();
			in.close();
		}
		
	}

	
	@Override
	public void run() {
		
			try {
				socket = new Socket(ip, port);
				new Listener().start();
				new Speaker().start();
			}catch(Exception e) {
				System.out.println("Client initialize failed.");
				//e.printStackTrace();
			}
	        

    }
}
//175.159.211.30