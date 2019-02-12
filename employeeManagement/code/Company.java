import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Company
{
	// initialization 
	private ArrayList<Employee> allEmployees;
	private ArrayList<Team> allTeams;

	private static Company instance = new Company();

	private Company() 
	{
		allEmployees = new ArrayList<>();
		allTeams = new ArrayList<>();
	}

	public static Company getInstance() 
	{
		return instance;
	}
	
	// add operation
	public Employee createEmployee(String aName, int aDay) throws ExAnnualLeavesOutOfRange , ExEmployeeExist
	{
		Employee e = new Employee(aName, aDay);
		
		// handle exceptions here
		// Exception 1: Annual leaves out of range (0-300)!
		if(aDay < 0 || aDay > 300)
			throw new ExAnnualLeavesOutOfRange();
		
		// Exception 2: Employee already exists!
		if(Employee.searchEmployee(allEmployees,aName) != null)
			throw new ExEmployeeExist();
		
		allEmployees.add(e);
		Collections.sort(allEmployees); //allEmployees
		return e;
	}
	
	public void addEmployee(Employee e) {
		allEmployees.add(e);
		Collections.sort(allEmployees);
	}
	
	public Team createTeam(String tn, String hdn) throws ExEmployeeNotFound, ExTeamExist
	{
		Employee e = Employee.searchEmployee(allEmployees,hdn);
		
		// handle exceptions here
		// Exception 1: Employee not found!
		if(e == null) 
			throw new ExEmployeeNotFound();
		
		Team t = new Team(tn,e);
		// Exception 2: Team already exists!
		if(searchTeam(tn) != null)
			throw new ExTeamExist();
		
		allTeams.add(t);
		Collections.sort(allTeams); 
		addMember(tn,hdn);
		return t; 
	}
	
	public void addTeam(Team t) {
		Role r = new RLeader(t.getName());
		t.getHead().addRole(r);
		allTeams.add(t);
	}
	
	public void addTeamMember(String teamName, String memberName) throws ExEmployeeNotFound, ExTeamNotFound, ExEmployeeAlreadyJoin {
		Employee member = Employee.searchEmployee(allEmployees, memberName);
		// handle exceptions
		// Exception 1: Employee not found!
		if(member == null)
			throw new ExEmployeeNotFound();
		
		Team team = searchTeam(teamName);
		// Exception 2: Team not found!
		if(team == null)
			throw new ExTeamNotFound();
		// Exception 3: Employee has already joined the team!
		if(member.equals(team.getHead()) || team.searchMember(member))
			throw new ExEmployeeAlreadyJoin();
		
		team.addMember(member);
		member.addRole(new RMember(teamName));
	}
	
	
	public void addMember(String teamName, String memberName) {
		Team team = searchTeam(teamName);
		Employee member = Employee.searchEmployee(allEmployees, memberName);
		team.addMember(member);
		if(team.getHead().equals(member))
			member.addRole(new RLeader(teamName));
		else
			member.addRole(new RMember(teamName));
	}
	
	public void createActingHead(String teamName,Role ahRole){
		searchTeam(teamName).addActingHead(ahRole);
	}
	
	
	// remove operation
	public void removeEmployee(Employee e) {
		allEmployees.remove(e);
	}	
	
	public void removeTeam(Team t) {
		t.getHead().removeRole(t.getHead().searchRole(t.getName()));
		allTeams.remove(t);
	}
		
	public void removeMember(String teamName, String memberName) {
		Team team = searchTeam(teamName);
		Employee member = Employee.searchEmployee(allEmployees, memberName);
		team.removeMember(member);
		member.removeRole(member.searchRole(teamName));
	}
	
	// list operation
	public void listEmployees() {
		for(Employee e:allEmployees) {
			System.out.printf("%s\n", e.toString());
		}
	}
	
	public void listTeamMembers() {
		for(Team t:allTeams) {
			System.out.println(t.getName()+":");
			t.listTeamMembers();
			t.listActingHeads();
			System.out.println();
		}			
	}
	
	public void listRoles(String eName) throws ExEmployeeNotFound {
		Employee e = Employee.searchEmployee(allEmployees, eName);
		if(e == null)
			throw new ExEmployeeNotFound();
		
		for(Employee m : allEmployees) {
			if(e.equals(m)) 
				e.listRoles();
		}
	}
	
	public void listTeams() 
	{
		Team.list(allTeams);
	}
	
	public void listAllLeaveRecord() {
		for(Employee e:allEmployees) {
			System.out.println(e.getName()+":");
			e.listlLeaveRecord();
		}
	}
	
	// search operation	
	public Team searchTeam(String tname) {
		for(Team t:allTeams) {
			if(tname.equals(t.getName())){
				return t;
			}
		}
		return null;
	}
	
	public Employee searchEmployee(String nameToSearch) {
		return Employee.searchEmployee(allEmployees, nameToSearch);
	}
	
	public void isActingHeadInTeam(ArrayList<String> tn,ArrayList<String> ahn) throws ExActingHeadNotFound {
		for(int i = 0;i<tn.size();i++) {
			Employee e = Employee.searchEmployee(allEmployees, ahn.get(i));
			if(e == null)
				throw new ExActingHeadNotFound("Employee ("+ahn.get(i)+") not found for "+tn.get(i)+"!");
			if(!searchTeam(tn.get(i)).searchMember(e)) {
				throw new ExActingHeadNotFound("Employee ("+ahn.get(i)+") not found for "+tn.get(i)+"!");
			}
		}
	}
	
	public void isOverlappedLeavesOfAH(ArrayList<String> ahn,LeaveRecord lr) throws ExActingHeadOnLeave {
		for(String s:ahn) {
			Employee e = Employee.searchEmployee(allEmployees, s);
			LeaveRecord tempRecord = e.isOverlap(lr);
			if(tempRecord != null)
				throw new ExActingHeadOnLeave(s+" is on leave during "+tempRecord.toString()+"!");				
		}
	}
}