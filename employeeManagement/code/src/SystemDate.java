
public class SystemDate extends Day
{
	private String dayString;
	private static SystemDate instance;
	
	private SystemDate(String sDay) { 
		super(sDay); dayString = sDay;
	}
	
	public static SystemDate getInstance(){ 
		return instance; 
	}
	
	public String getDay() {
		return dayString;
	}
	
	public static void createTheInstance(String sDay) 
	{
		if (instance==null) 
			instance = new SystemDate(sDay);
		else
			System.out.println("Cannot create one more system date instance.");
	}
}