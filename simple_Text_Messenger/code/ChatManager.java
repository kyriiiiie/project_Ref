
import java.util.Vector;

public class ChatManager {
    private ChatManager(){count = 0;}
    private static final ChatManager CM=new ChatManager();
    public static ChatManager getChatManager(){
        return CM;
    }
    int count;

    private Vector<ChatSocket> vector = new Vector<ChatSocket>();
    //wish to ask server whether to quit when vector.size() = 1

    public void add(ChatSocket cs){
        vector.add(cs);
        count++;
    }
    
    public void remove(ChatSocket cs) {
    	vector.remove(cs);
    	count--;
    }
    
    public int getClientNum() {
    	return count;
    }
    
    public int getSize() {
    	return vector.size();
    }

    //send message to all other users
    public void publish(ChatSocket cs, String msg){
        for (int i = 0; i < vector.size(); i++) {
            ChatSocket csTemp = vector.get(i);
            
            if(!msg.equals("Quit")) {
            	if ((!cs.equals(csTemp))&&(!csTemp.equals(null))) {
            		//
                    csTemp.out(csTemp.getSocket().getInetAddress()+ " says " + msg +"\n");
                }
            }
            else {
            	if(cs.equals(csTemp)) {
            		csTemp.out("Quit"+"\r");
            	}
            }
            
        }
    }
    

}
