
public class CmdSetNewDay extends RecordedCommand{
	String dayBeforeSet;
	String dayAfterSet;
	public void execute(String cmdParts[]) {
		dayBeforeSet = SystemDate.getInstance().getDay();
		SystemDate.getInstance().set(cmdParts[1]);
		dayAfterSet = cmdParts[1];
		
		addUndoCommand(this);
		clearRedoList();
		
		System.out.println("Done.");
	}
	
	public void undoMe() {
		
		SystemDate.getInstance().set(dayBeforeSet);
		addRedoCommand(this);
	}
	
	public void redoMe() {
		SystemDate.getInstance().set(dayAfterSet);
		
		addUndoCommand(this);
	}
}
