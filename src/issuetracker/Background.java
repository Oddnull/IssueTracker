package IssueTracker;

import java.io.*;
import java.security.MessageDigest;
import java.util.*;

public class Background {

    User currentUser;
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Issue> issues = new ArrayList<Issue>();

    public Background() {
    }

    //reads "users.txt" file line by line, makes user object, adds user object to Users array
    public void readUser() {
        Scanner scan = null;
        try {
            File file = new File("users.txt");
            scan = new Scanner(file);
            String nextLine;
            while (scan.hasNextLine()) {
                nextLine = scan.nextLine();
                System.out.println("read line" + nextLine);
                if (nextLine != null) {
                    users.add(User.parseUser(nextLine));
                    System.out.println("added user" + User.parseUser(nextLine));
                }
            }
        } catch (Exception e) {

        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }
    //reads "issues.txt". parses each line. creates issue object adds issue object to issues array

    public void readIssues() {
        Scanner scan = null;
        try {
            File file = new File("issues.txt");
            scan = new Scanner(file);
            String nextLine;
            while (scan.hasNext()) {
                nextLine = scan.nextLine();
                if (nextLine != null) {
                    Issue issue = Issue.parseIssue(nextLine);
                    issues.add(issue);
                }
            }
        } catch (Exception e) {
            System.out.println("cannot read issuue" + e);
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }
    //searches users array for userName returns User. if not present returns null

    public User findUser(String userName) {
        User tempUser;
        User foundUser = null;
        for (int i = 0; i < users.size(); i++) {
            tempUser = users.get(i);
            if (tempUser.getUserName().equals(userName)) {
                foundUser = tempUser;
                System.out.println("found user");
                break;
            }
        }
        return foundUser;
    }

    //makes user object and appends user object to file users.txt 
    public void createUser(String userName, String password, boolean isManager) {
        String hashedPassword = hashString(password);
        User newUser = new User(userName, hashedPassword, isManager);
        appendString(newUser.toString(), "users.txt");
        users.add(newUser);
    }

    public void createIssue(String description, String status, String assignedTo) {
        Issue newIssue = new Issue(description, status, assignedTo);
        appendString(newIssue.toString(), "issues.txt");
        issues.add(newIssue);
    }
    //appends String to specified filename

    public void appendString(String str, String fileName) {
        PrintWriter pw = null;
        try {
            FileWriter fw = new FileWriter(fileName, true);
            pw = new PrintWriter(fw);
            pw.println(str);
        } catch (Exception e) {
            System.out.println("cannot write");
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    //converts string into hexadecimal hash. For password encryption
    public String hashString(String str) {
        StringBuffer sb = null;
        try {
            // create byte hash from string
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte[] byteData = md.digest();

            // convert byte to hexadecimal
            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception e) {
            System.out.println("error" + e);
            return null;
        }
        return sb.toString();
    }
}
