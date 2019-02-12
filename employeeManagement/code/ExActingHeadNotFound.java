
@SuppressWarnings("serial")
public class ExActingHeadNotFound extends Exception{
	public ExActingHeadNotFound() {super("Employee not found for the Team!");}
	public ExActingHeadNotFound(String message) {super(message);}
}
