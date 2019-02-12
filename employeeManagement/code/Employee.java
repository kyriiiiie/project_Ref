import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee>{
	
	// initialization
	private String name;
	private int yrLeavesEntitled;
	private int yrLeavesLeft;
	private ArrayList<Role> roles;
	private ArrayList<LeaveRecord> allLeaveRecord;
	
	public Employee(String n,int yle) {
		name = n;
		yrLeavesEntitled = yle;
		yrLeavesLeft = yle;
		roles = new ArrayList<>();
		allLeaveRecord = new ArrayList<>();
	}
	
	// add operation
	public void addRole(Role aRole) {
		roles.add(aRole);
		Collections.sort(roles);
	}
	
	public LeaveRecord createLeaveRecord(Day sd, Day ed) throws ExWrongLeaveDate, ExLeaveOverlap, ExInsufficientLeaveBalance {
		LeaveRecord lr = new LeaveRecord(sd,ed);
		
		// handle exceptions first		
		// Exception 1: wrong date
		if(sd.getCompareDay() < SystemDate.getInstance().getCompareDay())
			throw new ExWrongLeaveDate("Wrong Date. System date is already "+ SystemDate.getInstance().toString()+"!");
		
		// Exception 3: insufficient balance
		if(sd.calDiff(ed) > yrLeavesLeft)
			throw new ExInsufficientLeaveBalance("Insufficient balance.  "+yrLeavesLeft+" days left only!");
		
		allLeaveRecord.add(lr);
		Collections.sort(allLeaveRecord);
		return lr;
	}
	
	public void addLeaveRecord(LeaveRecord lr) {
		allLeaveRecord.add(lr);
	}
	
	public void addYrLeavesLeft(int days) {
		yrLeavesLeft += days;
	}
	
	// remove operation
	public void removeRole(Role r) {
		roles.remove(r);
	}
	
	public void removeLeaveRecord(LeaveRecord lr) {
		allLeaveRecord.remove(lr);
	}
	
	public void reduceYrLeavesLeft(int days) {
		yrLeavesLeft -= days;
	}
	
	// list operation
	public void listRoles() {
		if(roles.size() == 0)
			System.out.println("No role");
		for(Role r:roles) {
			System.out.println(r.toString());
		}
	}
	
	public void listlLeaveRecord() {
		if(allLeaveRecord.size() == 0) {
			System.out.println("No leave record");
			return;
		}
		for(LeaveRecord l:allLeaveRecord) {
			System.out.println(l.toString());
		}
	}
	
	//search operation
	public Role searchRole(String tn) {
		for(Role r:roles) {
			if(tn.equals(r.getName()))
				return r;			
		}
		return null;
	}
	
	public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) {
		for(int i=0;i<list.size();i++) {
			if(nameToSearch.equals(list.get(i).getName())) {
				return list.get(i);
			}
		}
		return null;
	}
	
	// check
	public LeaveRecord isOverlap(LeaveRecord r) {
		for(LeaveRecord l:allLeaveRecord) {
			if(r.isOverlap(l)) {
				return l;
			}
		}
		return null;
	}
	
	public void isLackActingHead(ArrayList<String> list) throws ExActingHeadMissing {
		for(Role r:roles) {
			if(r instanceof RLeader) {
				boolean isExist = false;
				for(String s:list) {
					if(s.equals(r.getName())) {
						isExist = true;
						break;
					}
				}
				
				if(!isExist)
					throw new ExActingHeadMissing("Please name a member to be the acting head of "+r.getName());
			}
		}
	}
	
	public void ifCanTakeLeave(LeaveRecord lr) throws ExActingHeadOnWork {
		for(Role r:roles) {
			Team t = Company.getInstance().searchTeam(r.getName());
			RActingHead tempAH = t.canTakeLeave(lr, this);
			
			if(tempAH != null)
				throw new ExActingHeadOnWork("Cannot take leave.  "+this.name+ " is the acting head of "+tempAH.getName()+" during "+tempAH.getLeaveRecord().toString()+"!");
		}
	}
	
	// others
	public int getYrLeavesLeft() {
		return yrLeavesLeft;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int compareTo(Employee another) {
		return this.name.compareTo(another.name);
	}
	
	@Override
	public String toString() {
		return name+" (Entitled Annual Leaves: "+yrLeavesEntitled+" days)";
	}
}
