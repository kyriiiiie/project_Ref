
@SuppressWarnings("serial")
public class ExEmployeeAlreadyJoin extends Exception{
	public ExEmployeeAlreadyJoin() {super("Employee has already joined the team!");}
	public ExEmployeeAlreadyJoin(String message) {super(message);}
}
