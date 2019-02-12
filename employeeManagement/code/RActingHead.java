
public class RActingHead extends Role{
	LeaveRecord lr;
	Employee ah;
	public RActingHead(String teamName, Employee ah, LeaveRecord lr) {
		super(teamName);
		this.ah = ah;
		this.lr = lr;
	}
	
	public Employee getActingHead() {
		return ah;
	}
	public LeaveRecord getLeaveRecord() {
		return lr;
	}
	
	public int compareTo(RActingHead another) {
		return lr.compareTo(another.getLeaveRecord());
	}
	
	@Override
	public String toString() {
		return lr.toString()+": "+ah.getName();
	}
}
