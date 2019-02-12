
@SuppressWarnings("serial")
public class ExActingHeadOnLeave extends Exception{
	public ExActingHeadOnLeave() {super("Actinghead's leave time is overlapped");}
	public ExActingHeadOnLeave(String message) {super(message);}
}
