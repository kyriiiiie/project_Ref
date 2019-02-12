
public class CmdHire extends RecordedCommand{
	Employee e;
	
	public void execute(String cmdParts[]) {
		try {
			Company company = Company.getInstance();
			
			// handle Insufficient command arguments
			if(cmdParts.length != 3) 
				throw new ExInsufficientArguments();
			
			// other exceptions throw in this method
			e = company.createEmployee(cmdParts[1],Integer.parseInt(cmdParts[2]));
						
			addUndoCommand(this);
			clearRedoList();
			
			System.out.println("Done.");
		} catch (ExEmployeeExist e) {
			System.out.println(e.getMessage());
		} catch(ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch(ExAnnualLeavesOutOfRange e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void undoMe() {
		Company company = Company.getInstance();
		company.removeEmployee(e);
		
		addRedoCommand(this);
	}
	
	public void redoMe() {
		Company company = Company.getInstance();
		company.addEmployee(e);
		
		addUndoCommand(this);
	}
}
