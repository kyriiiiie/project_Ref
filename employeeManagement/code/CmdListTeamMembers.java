
public class CmdListTeamMembers implements Command{
    public void execute(String[] cmdParts) {
        Company company = Company.getInstance();
        company.listTeamMembers();
    }
}
