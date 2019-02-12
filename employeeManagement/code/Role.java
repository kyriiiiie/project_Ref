
public abstract class Role implements Comparable<Role>{
	private String teamName;
	
	public Role(String tn) {
		teamName = tn;
	}
	
	public String getName() {
		return teamName;
	}
	
	@Override
	public int compareTo(Role another) {
		return this.teamName.compareTo(another.getName());
	}
	
	@Override
	public abstract String toString();
}
