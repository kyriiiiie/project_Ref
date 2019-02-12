
public class CmdListLeaves implements Command{
	 public void execute(String[] cmdParts) {
		 Company company = Company.getInstance();
		 
		 if(cmdParts.length == 1) {
	      company.listAllLeaveRecord();
		 }
		 else
		 {
			 Employee tempE = company.searchEmployee(cmdParts[1]);
			 tempE.listlLeaveRecord();
		 }
	        
	 }
}
