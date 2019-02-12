import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Team implements Comparable<Team>{
	
	// initialization
	private String teamName;
	private Employee head;
	private Day dateSetup;
	private ArrayList<Employee> allTeamMember;
	private ArrayList<RActingHead> actingHeads;
	
	public Team(String n, Employee hd) {
		teamName = n;
		head = hd;
		dateSetup = SystemDate.getInstance().clone();
		allTeamMember = new ArrayList<>();
		actingHeads = new ArrayList<>();
	}
	
	// add operation
	public void addMember(Employee member) {
		allTeamMember.add(member);
		Collections.sort(allTeamMember);
	}
	
	public void addActingHead(Role r) {
		actingHeads.add((RActingHead) r);
		Collections.sort(actingHeads, new Comparator<RActingHead>()
		  {			
			public int compare(RActingHead ahr1,RActingHead ahr2) {
				return  ahr1.compareTo(ahr2);
			}
		});
	}
	
	// remove operation
	public void removeMember(Employee member) {
		allTeamMember.remove(member);
	}
	
	public void removeActingHead(Role r) {
		actingHeads.remove(r);
	}
	
	// list operation
	public static void list(ArrayList<Team> list) {
		System.out.printf("%-30s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
		for (Team t : list)
			System.out.printf("%-30s%-10s%-13s\n",t.teamName, t.head.getName(),t.dateSetup.toString()); //display t.teamName, etc..
	}
	
	public void listTeamMembers() {
		for(Employee e: allTeamMember) {
			if(e.equals(head)) 
				System.out.println(e.getName()+" (Head of Team)");
			else 
				System.out.println(e.getName());
		}
	}
	
	public void listActingHeads() {
		if(actingHeads.size()!= 0) {
			System.out.println("Acting heads:");
			for(RActingHead ahr: actingHeads) {
				System.out.println(ahr.toString());
			}
		}
	}
	
	// search operation
	public boolean searchMember(Employee member) {
		for(Employee e: allTeamMember) {
			if(member == e)
				return true;
		}
		return false;
	}
	
	// check
	public RActingHead canTakeLeave(LeaveRecord lr,Employee e) {
		for(RActingHead ahr: actingHeads) {
			if(ahr.getActingHead() == e && ahr.getLeaveRecord().isOverlap(lr))
				return ahr;
		}
		return null;
	}
	
	//others
	public String getName() {
		return teamName;
	}
	
	public Employee getHead() {
		return head;
	}
	
	@Override
	public int compareTo(Team another) {
		return this.teamName.compareTo(another.teamName);
	}
}
