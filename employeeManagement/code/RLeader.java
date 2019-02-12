
public class RLeader extends Role{
	public RLeader(String tn) {
		super(tn);
	}
	
	@Override
	public String toString() {
		return super.getName()+" (Head of Team)";
	}
}
