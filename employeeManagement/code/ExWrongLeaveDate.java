
@SuppressWarnings("serial")
public class ExWrongLeaveDate extends Exception{
	public ExWrongLeaveDate() {super("Wrong Date. System date has already passed");}
	public ExWrongLeaveDate(String message) {super(message);}
}
