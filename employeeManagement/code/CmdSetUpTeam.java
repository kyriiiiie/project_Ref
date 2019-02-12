
public class CmdSetUpTeam extends RecordedCommand{
	Team t;
	
	public void execute(String cmdParts[]) {
		try {
			Company company = Company.getInstance();
			
			// handle Insufficient command arguments
			if(cmdParts.length != 3) 
				throw new ExInsufficientArguments();
			
			// exceptions throw in this method
			t = company.createTeam(cmdParts[1], cmdParts[2]);
			
			
			addUndoCommand(this);
			clearRedoList();
			
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch(ExTeamExist e) {
			System.out.println(e.getMessage());
		} catch(ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void undoMe() {
		Company company = Company.getInstance();
		company.removeTeam(t);
		addRedoCommand(this);
	}
	
	public void redoMe() {
		Company company = Company.getInstance();
		company.addTeam(t);
		
		addUndoCommand(this);
	}
}
