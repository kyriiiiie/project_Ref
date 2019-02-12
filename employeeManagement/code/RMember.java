
public class RMember extends Role{
	public RMember(String tn) {
		super(tn);
	}
	
	@Override
	public String toString() {
		return super.getName();
	}
}
