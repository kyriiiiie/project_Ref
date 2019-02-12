
@SuppressWarnings("serial")
public class ExAnnualLeavesOutOfRange extends Exception{
	public ExAnnualLeavesOutOfRange() {super("Annual leaves out of range (0-300)!");}
	public ExAnnualLeavesOutOfRange(String message) {super(message);}
}
