import java.util.ArrayList;

public class CmdTakeLeave extends RecordedCommand{
	Employee e;
	LeaveRecord lr;
	Day startDay;
	Day endDay; 
	ArrayList<Role> actingheads = new ArrayList<>();
	ArrayList<String> teamNames = new ArrayList<>();
	ArrayList<String> actingheadNames = new ArrayList<>();
	public void execute(String cmdParts[]) {
		try {		
			//initialization
			Company company = Company.getInstance();
			e = company.searchEmployee(cmdParts[1]);
			startDay = new Day(cmdParts[2]);
			endDay = new Day(cmdParts[3]);		
			
			// record all actinghead's names and their team names
			for(int i = 0;i<(cmdParts.length-4)/2;i++) {
				teamNames.add(cmdParts[4+2*i]);
				actingheadNames.add(cmdParts[5+2*i]);
			}
			
			// check and throw four exceptions here
			// ExActingHeadMissing, ExHeadCanNotTakeLeave
			// ExActingHeadNotFound, ExActingHeadOnLeave
			e.isLackActingHead(teamNames);
			e.ifCanTakeLeave(new LeaveRecord(startDay,endDay));
			company.isActingHeadInTeam(teamNames, actingheadNames);
			company.isOverlappedLeavesOfAH(actingheadNames, new LeaveRecord(startDay,endDay));
			
			// create leave record and actingRoles
			lr = e.createLeaveRecord(startDay, endDay);
			for(int i = 0;i< teamNames.size();i++) {
				Employee ah = company.searchEmployee(actingheadNames.get(i));
				Role ahRole = new RActingHead(teamNames.get(i),ah,lr);
				
				company.createActingHead(teamNames.get(i), ahRole);
				actingheads.add(ahRole);
			}
			
			e.reduceYrLeavesLeft(startDay.calDiff(endDay));
			addUndoCommand(this);
			clearRedoList();
			
			System.out.println("Done.");
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		} catch (ExActingHeadMissing e) {
			System.out.println(e.getMessage());
		} catch (ExLeaveOverlap e) {
			System.out.println(e.getMessage()); 
		} catch (ExWrongLeaveDate e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientLeaveBalance e) {
			System.out.println(e.getMessage());
		} catch (ExActingHeadNotFound e) {
			System.out.println(e.getMessage());
		}catch (ExActingHeadOnLeave e) {
			System.out.println(e.getMessage());
		} catch (ExActingHeadOnWork e) {
			System.out.println(e.getMessage());
		} 
		
	}
	@Override
	public void undoMe() {
		e.addYrLeavesLeft(startDay.calDiff(endDay));
		e.removeLeaveRecord(lr);
		
		// remove actingheads
		for(Role r: actingheads) {
			Team t = Company.getInstance().searchTeam(r.getName());
			t.removeActingHead(r);			
		}
		
		addRedoCommand(this);
	}
	@Override
	public void redoMe() {
		e.addLeaveRecord(lr);
		e.reduceYrLeavesLeft(startDay.calDiff(endDay));
		
		// readd actingheads
		for(Role r:actingheads) {
			Team t = Company.getInstance().searchTeam(r.getName());
			t.addActingHead(r);
		}
		
		addUndoCommand(this);
	}
}
