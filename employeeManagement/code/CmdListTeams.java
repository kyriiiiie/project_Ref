
public class CmdListTeams {
	public void execute(String[] cmdParts) {
        Company company = Company.getInstance();
        company.listTeams();
    }
}
