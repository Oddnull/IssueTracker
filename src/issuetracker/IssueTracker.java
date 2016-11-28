package IssueTracker;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import javafx.scene.text.Font;
import javafx.collections.*;

public class IssueTracker extends Application {

    Label lblTitle, lblName, lblPass;
    TextField txtName, txtAddName, txtAddPass;
    PasswordField txtPass;
    Button btnUsrLogin, btnNewIssue, btnCancel, btnSubmit, btnMgrLogin, btnAddUser;
    Button btnAddSubmit, btnAddCancel;
    Stage theStage;
    Scene loginScene, userListScene, userDetailScene, mgrListScene, addUserScene;
    BorderPane loginPane, userListPane, userDetailPane, mgrListPane, addPane;
    GridPane fields, addFields;
    TextArea txtArea;
    HBox hBox, tempHBox, mgrBox, addHBox;
    //List view of issues example
    ListView<String> list;
    ObservableList<String> items = FXCollections.observableArrayList("Program is too slow",
            "Login screen button not working", "Not using files for data storage");

    public void start(Stage primaryStage) {
        //can now use theStage in other methods
        theStage = primaryStage;
        primaryStage.setTitle("Issue Tracker v1.0");
        showLogin();
        showUserIssuePane();
        showUserDetailsPane();
        showMgrIssuePane();
        showAddUserPane();

        mgrListScene = new Scene(mgrListPane, 365, 250);
        loginScene = new Scene(loginPane, 365, 250);
        userListScene = new Scene(userListPane, 365, 250);
        userDetailScene = new Scene(userDetailPane, 365, 250);
        addUserScene = new Scene(addPane, 365, 250);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public void showAddUserPane() {
        lblTitle = new Label("Add User (mgr)");
        lblName = new Label("Name:");
        lblPass = new Label("Password:");
        addHBox = new HBox();
        addHBox.setSpacing(10);
        btnAddSubmit = new Button("Submit");
        btnAddCancel = new Button("Cancel");
        addHBox.getChildren().addAll(btnAddSubmit, btnAddCancel);
        addHBox.setAlignment(Pos.TOP_RIGHT);
        txtAddName = new TextField();
        txtAddPass = new TextField();
        addPane = new BorderPane();
        addPane.setPadding(new Insets(30, 10, 10, 10));
        addPane.setBottom(addHBox);
        setTitle();
        addPane.setTop(lblTitle);
        BorderPane.setAlignment(addHBox, Pos.TOP_RIGHT);
        addFields = new GridPane();
        addPane.setCenter(addFields);
        addFields.setPadding(new Insets(40));
        addFields.setVgap(10);
        addFields.setHgap(10);
        addFields.add(lblName, 0, 0);
        addFields.add(lblPass, 0, 1);
        addFields.add(txtAddName, 1, 0);
        addFields.add(txtAddPass, 1, 1);
        //btnAddSubmit.setOnAction(e->usrLogin());
        //btnAddCancel.setOnAction(e->mgrLogin());

    }

    public static ArrayList<String> getRegexPattern(String text, String expression) {
        ArrayList<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile(expression).matcher(text);
        while (m.find()) {
            allMatches.add(m.group());
        }
        return allMatches;
    }

    public void showUserDetailsPane() {
        hBox = new HBox();
        btnCancel = new Button("Cancel");
        //TODO submit needs to put a status 'new' for a new issue
        //TODO submit button is active only if its a new issue
        btnSubmit = new Button("Submit");
        userDetailPane = new BorderPane();
        userDetailPane.setPadding(new Insets(10, 10, 10, 10));
        lblTitle = new Label("Issue Details (user)");
        setTitle();
        userDetailPane.setTop(lblTitle);
        BorderPane.setMargin(lblTitle, new Insets(12, 12, 12, 12));
        txtArea = new TextArea();
        userDetailPane.setCenter(txtArea);
        hBox.getChildren().addAll(btnSubmit, btnCancel);
        userDetailPane.setBottom(hBox);
        hBox.setSpacing(10);
        BorderPane.setMargin(hBox, new Insets(12, 0, 0, 0));
        hBox.setAlignment(Pos.TOP_RIGHT);
        btnCancel.setOnAction(e -> cancel());
    }

    public void showMgrIssuePane() {
        list = new ListView<String>();
        mgrListPane = new BorderPane();
        mgrListPane.setPadding(new Insets(10, 10, 10, 10));
        lblTitle = new Label("List of Issues (mgr)");
        setTitle();
        mgrListPane.setTop(lblTitle);
        BorderPane.setMargin(lblTitle, new Insets(12, 12, 12, 12));
        list.setItems(items);
        mgrListPane.setCenter(list);
        btnNewIssue = new Button("New Issue");
        btnAddUser = new Button("Add User");
        mgrBox = new HBox();
        BorderPane.setMargin(mgrBox, new Insets(12, 0, 0, 0));
        mgrBox.getChildren().addAll(btnAddUser, btnNewIssue);
        mgrBox.setSpacing(10);
        mgrListPane.setBottom(mgrBox);
        mgrBox.setAlignment(Pos.TOP_RIGHT);
        btnNewIssue.setOnAction(e -> newIssue());
        btnAddUser.setOnAction(e -> addUser());
    }

    public void showUserIssuePane() {
        list = new ListView<String>();
        userListPane = new BorderPane();
        userListPane.setPadding(new Insets(10, 10, 10, 10));
        lblTitle = new Label("List of Issues (user)");
        setTitle();
        userListPane.setTop(lblTitle);
        BorderPane.setMargin(lblTitle, new Insets(12, 12, 12, 12));
        list.setItems(items);
        userListPane.setCenter(list);
        btnNewIssue = new Button("New Issue");
        userListPane.setBottom(btnNewIssue);
        BorderPane.setAlignment(btnNewIssue, Pos.TOP_RIGHT);
        BorderPane.setMargin(btnNewIssue, new Insets(12, 0, 0, 0));
        btnNewIssue.setOnAction(e -> newIssue());
    }

    public void showLogin() {
        lblTitle = new Label("Login");
        lblName = new Label("Name:");
        lblPass = new Label("Password:");
        //temporary buttons---------------------
        tempHBox = new HBox();
        tempHBox.setSpacing(10);
        btnMgrLogin = new Button("mgrLogin");
        btnUsrLogin = new Button("usrLogin");
        tempHBox.getChildren().addAll(btnMgrLogin, btnUsrLogin);
        tempHBox.setAlignment(Pos.TOP_RIGHT);
        //final program will have on button and validate mngr, usr or dev
        txtName = new TextField();
        txtPass = new PasswordField();
        loginPane = new BorderPane();
        loginPane.setPadding(new Insets(30, 10, 10, 10));
        loginPane.setBottom(tempHBox);
        setTitle();
        loginPane.setTop(lblTitle);
        BorderPane.setAlignment(btnUsrLogin, Pos.TOP_RIGHT);
        fields = new GridPane();
        loginPane.setCenter(fields);
        fields.setPadding(new Insets(40));
        fields.setVgap(10);
        fields.setHgap(10);
        fields.add(lblName, 0, 0);
        fields.add(lblPass, 0, 1);
        fields.add(txtName, 1, 0);
        fields.add(txtPass, 1, 1);
        btnUsrLogin.setOnAction(e -> usrLogin());
        btnMgrLogin.setOnAction(e -> mgrLogin());
    }

    public void setTitle() {
        this.lblTitle.setFont(new Font("Arial", 20));
        this.lblTitle.setMaxWidth(Double.MAX_VALUE);
        this.lblTitle.setAlignment(Pos.CENTER);
    }

    public void usrLogin() {
        theStage.setScene(userListScene);
    }

    public void newIssue() {
        theStage.setScene(userDetailScene);
    }

    public void cancel() {
        theStage.setScene(userListScene);
    }

    public void mgrLogin() {
        theStage.setScene(mgrListScene);
    }

    public void addUser() {
        theStage.setScene(addUserScene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
