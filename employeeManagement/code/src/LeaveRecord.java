
public class LeaveRecord implements Comparable<LeaveRecord>{
	private Day startDay;
	private Day endDay;
	private int comparableStartDay;
	private int comparableEndDay;
	
	// initialization
	public LeaveRecord(Day sDay, Day eDay) {
		startDay = sDay;
		endDay = eDay;
		comparableStartDay = startDay.getCompareDay();
		comparableEndDay = endDay.getCompareDay();
	}
	
	// judge overlap
	public boolean isOverlap(LeaveRecord another) {
		boolean isol = false;
		if((comparableStartDay >= another.comparableStartDay && comparableStartDay <= another.comparableEndDay) || 
				(comparableEndDay >= another.comparableStartDay && comparableEndDay <= another.comparableEndDay))
		{
			isol = true;
		}
		return isol;
	}
	
	@Override
	public String toString() {
		return startDay.toString()+" to "+ endDay.toString();
	}
	
	@Override
	public int compareTo(LeaveRecord another) {
		return this.startDay.compareTo(another.startDay);
	}
}
