
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[])throws IOException{
		//pop up the system menu
		Scanner in = new Scanner(System.in);
		String order;
		
		System.out.println("*** SIMPLE TEXT MESSENGER ***");
		System.out.println("***      Ver. 1.0.0.86    ***");
		System.out.println("(C)onnect peer");
		System.out.println("(W)ait for the other peer connecting");
		System.out.println("(Q)uit");
		System.out.print("Please choose:");
		order = in.next();
		in.nextLine();
		
		//client part: to StartClient
		if(order.equals("C") || order.equals("c")) {
			String ip; 
    		int port;
    		
			
			System.out.print("Remote IP: ");
			ip = in.next();
			in.nextLine();
			 
			System.out.print("and port: ");
			port = in.nextInt();
			//put the scanner in to keep it work
			new StartClient(ip,port,in).start();
		}      //server part: call serverListener & ServerSend
		else if(order.equals("W") || order.equals("w")) {
			
        	String ip; 
    		int port;
           
            System.out.print("Input local machine IP: ");
			ip = in.next();
			
			System.out.print("Input the port that you want to bind: ");
			port = in.nextInt();
			
			ServerListener sl = new ServerListener(ip,port,in);
			sl.start();
			
			// use ServerSend to send server's input
			ServerSend ss = new ServerSend(ip,port,in);
			ss.start();
			
		}
		else if(order.equals("Q") || order.equals("q")) {
			System.out.println("You will quit the application, press any key.");
		}
		
	}

}
