
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);

		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
		
		Scanner inFile = new Scanner(new File(filepathname));
		
		String cmdLine1 = inFile.nextLine();
		String[] cmdLine1Parts = cmdLine1.split("\\|");
		System.out.println("\n> "+cmdLine1);
		SystemDate.createTheInstance(cmdLine1Parts[1]);
		
		while (inFile.hasNext())		
		{
			String cmdLine = inFile.nextLine().trim();
			
			if (cmdLine.equals("")) continue;  

			System.out.println("\n> "+cmdLine);
			
			String[] cmdParts = cmdLine.split("\\|"); 
			
			if(cmdParts[0].equals("startNewDay")) 
				(new CmdSetNewDay()).execute(cmdParts);
			else if(cmdParts[0].equals("setupTeam"))
				(new CmdSetUpTeam()).execute(cmdParts);
			else if (cmdParts[0].equals("hire")) 
				(new CmdHire()).execute(cmdParts);
			else if(cmdParts[0].equals("addTeamMember"))
				(new CmdAddTeamMember()).execute(cmdParts);
			else if(cmdParts[0].equals("takeLeave"))
				(new CmdTakeLeave()).execute(cmdParts);
			else if(cmdParts[0].equals("listRoles"))
				(new CmdListRoles()).execute(cmdParts);
			else if(cmdParts[0].equals("listTeamMembers"))
				(new CmdListTeamMembers()).execute(cmdParts);
			else if (cmdParts[0].equals("listEmployees"))
				(new CmdListEmployees()).execute(cmdParts);
			else if(cmdParts[0].equals("listTeams"))
				(new CmdListTeams()).execute(cmdParts);
			else if(cmdParts[0].equals("listLeaves"))
				(new CmdListLeaves()).execute(cmdParts);
			else if (cmdParts[0].equals("undo"))
				RecordedCommand.undoOneCommand();
			else if (cmdParts[0].equals("redo"))
				RecordedCommand.redoOneCommand();
		}
		inFile.close();
			
		in.close();
	}
}
