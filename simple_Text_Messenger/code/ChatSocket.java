
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

public class ChatSocket extends Thread {
    private Socket socket;

    public ChatSocket(Socket s) {
        this.socket = s;
    }
    
    public Socket getSocket() {
    	return socket;
    }

    public void out(String out) {
        try {
            socket.getOutputStream().write(out.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    
    public void run() {
        try {

        	Scanner in = new Scanner(System.in);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), "UTF-8"));
            String line=null;

          //to show server. work as server's receiver 
            while ((line = br.readLine())!=null) {
            	if(line.equals("Quit")) {            		
					System.out.println("User "+socket.getInetAddress()+" has leave the chat");
					ChatManager.getChatManager().remove(this);	
					//try
					if(ChatManager.getChatManager().getClientNum() == 1) {
						System.out.println("No client now. You may close the application (Quit/close the window), or just wait.");
					}
					break;
				}
            	else		
            		System.out.println(socket.getInetAddress() + " says: " + line); // if the message is not "quit"             
            	
            	ChatManager.getChatManager().publish(this, line);
            }
            br.close();
            in.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
