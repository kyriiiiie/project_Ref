//import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ServerSend extends Thread{
	private static String ip;
	private int port;
	private static boolean toQuit;
	private static Socket socket;
	private static Scanner in;
	
	private static void setQuit() {
		toQuit = false;
	}
	
	public ServerSend(String aIp, int aPort,Scanner s) {
		ip = aIp;
		port = aPort;
		toQuit = true;
		in = s;
		try {
			socket = new Socket(ip,port);
		} catch (IOException e) {
			System.out.println("Connection failed");
		}
		System.out.println("ServerSender connected!");
	};
	
	
	private static class Speaker extends Thread{
		private PrintWriter pw;
		
		public Speaker() {
			
			try {
				pw=new PrintWriter(socket.getOutputStream(),true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//
			System.out.println("Writer initialized");
			
		}
		
		@Override
		public void run() {
			//InetAddress myip;
			//
			
			//myip = InetAddress.getLocalHost();
			//String localip = myip.getHostAddress(); 
			//
			in.nextLine();
			
			while(toQuit) {
				String s = in.nextLine();
				pw.write(s +"\r");
				pw.flush();
				if(s.equals("Quit")) 
					setQuit();
				}
			
			System.out.println("You will quit the application, press any key.");
		}
		
	}

	
	@Override
	public void run() {
		
			try {
				socket = new Socket(ip, port);
				new Speaker().start();
			}catch(Exception e) {
				System.out.println("ServerMicrophone initialize failed.");
				e.printStackTrace();
			} 
	        

    }
}