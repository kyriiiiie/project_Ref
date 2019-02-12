public class CmdAddTeamMember extends RecordedCommand{
	String teamName;
	String memberName;
	
	public void execute(String cmdParts[]) {
		try {
			Company company = Company.getInstance();
			
			// handle Insufficient command arguments
			if(cmdParts.length != 3) 
				throw new ExInsufficientArguments();
			
			// other exceptions throw in this method
			company.addTeamMember(cmdParts[1], cmdParts[2]);
			teamName = cmdParts[1];
			memberName = cmdParts[2];
			
			addUndoCommand(this);
			clearRedoList();
			
			System.out.println("Done.");
		} catch (ExEmployeeAlreadyJoin e) {
			System.out.println(e.getMessage());
		} catch(ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch(ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch(ExTeamNotFound e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void undoMe() {
		Company company = Company.getInstance();
		company.removeMember(teamName, memberName);
		
		addRedoCommand(this);
	}
	
	public void redoMe() {
		Company company = Company.getInstance();
		company.addMember(teamName, memberName);
		
		addUndoCommand(this);
	}
}
