package IssueTracker;

public class Issue {
	private String description;
	private String status; // new, assigned, validated, closed, failed
	private String assignedTo;
	public Issue(){
		this.description = "";
		this.status = "";
	}
	public Issue(String description, String status, String assignedTo){
		this.description = description;
		this.status = status;
		this.assignedTo = assignedTo;
	}
	public String getAssignedTo(){
		return assignedTo;
	}

	public String getDescription(){
		return this.description;		
	}
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public void assignedTo(String userName){
		this.assignedTo = userName;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public static Issue parseIssue(String str){
		Issue issue = null;
		if(str != null){
			String description = str.substring(str.indexOf(':')+1, str.indexOf('~')).trim();
			String status = str.substring(str.indexOf('~')+1, str.lastIndexOf('~')).trim();
			String assignedTo = str.substring(str.lastIndexOf('~')+1, str.lastIndexOf(']')).trim();
			issue = new Issue(description, status, assignedTo);
		}
		System.out.println("parsed"+ issue);
		return issue;
	}
	public String toString(){
		return "[ Issue: "+description +" ~ "+ status+" ~ "+ assignedTo +" ]";
	}
}
