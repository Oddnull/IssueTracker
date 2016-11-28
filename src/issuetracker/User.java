package IssueTracker;

import java.util.*;

public class User {

    private String userName;
    private String password;
    private boolean isManager;
    private ArrayList<Issue> assignedIssues;

    public User() {
        this.userName = "";
        this.password = "";
        this.isManager = false;
    }

    public User(String userName, String password, boolean isManager) {
        this.userName = userName;
        this.password = password;
        this.isManager = isManager;
        assignedIssues = new ArrayList<Issue>();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsManager() {
        return isManager;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User parseUser(String str) {
        User user = null;
        if (str != null) {
            String userName = str.substring(str.indexOf(':') + 1, str.indexOf(',')).trim();
            String password = str.substring(str.indexOf(',') + 1, str.lastIndexOf(',')).trim();
            String sIsManager = str.substring(str.lastIndexOf(',') + 1, str.indexOf(']')).trim();
            boolean isManager = false;
            if (sIsManager.equals("true")) {
                isManager = true;
            }
            user = new User(userName, password, isManager);
        }
        return user;
    }

    public String toString() {
        return "[ User: " + userName + ", " + password + ", " + isManager + " ]";
    }
}
