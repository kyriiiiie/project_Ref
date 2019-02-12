
@SuppressWarnings("serial")
public class ExInsufficientLeaveBalance extends Exception{
	public ExInsufficientLeaveBalance() {super("Insufficient Balance!");}
	public ExInsufficientLeaveBalance(String message) {super(message);}
}
