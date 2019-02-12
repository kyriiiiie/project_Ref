
public class CmdListRoles implements Command{
    public void execute(String[] cmdParts) {
        Company company = Company.getInstance();
        
        try {
			company.listRoles(cmdParts[1]);
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		}
    }
}
