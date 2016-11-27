/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package issuetracker;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.Font;
import javafx.collections.*;

public class IssueTracker extends Application {

    Label lblTitle, lblName, lblPass;
    TextField txtName;
    PasswordField txtPass;
    Button btnLogin, btnNewIssue, btnCancel, btnSubmit;
    Stage theStage;
    Scene loginScene, userListScene, userDetailScene;
    BorderPane loginPane, userListPane, userDetailPane;
    GridPane fields;
    TextArea txtArea;
    HBox hBox;
    //List view of issues example
    ListView<String> list = new ListView<String>();
    ObservableList<String> items = FXCollections.observableArrayList("Program is too slow",
            "Login screen button not working", "Not using files for data storage");

    @Override
    public void start(Stage primaryStage) {
        //can now use theStage in other methods
        theStage = primaryStage;
        primaryStage.setTitle("Issue Tracker v1.0");
        showLogin();
        showUserIssuePane();
        showUserDetailsPane();
        loginScene = new Scene(loginPane, 365, 250);
        userListScene = new Scene(userListPane, 365, 250);
        userDetailScene = new Scene(userDetailPane, 365, 250);
        primaryStage.setScene(loginScene);
        primaryStage.show();
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

    public void showUserIssuePane() {

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
        BorderPane.setAlignment(btnNewIssue, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(btnNewIssue, new Insets(12, 0, 0, 0));
        btnNewIssue.setOnAction(e -> newIssue());
    }

    public void showLogin() {
        lblTitle = new Label("Login");
        lblName = new Label("Name:");
        lblPass = new Label("Password:");
        btnLogin = new Button("Login");
        txtName = new TextField();
        txtPass = new PasswordField();
        loginPane = new BorderPane();
        loginPane.setPadding(new Insets(30, 10, 10, 10));
        loginPane.setBottom(btnLogin);
        setTitle();
        loginPane.setTop(lblTitle);
        BorderPane.setAlignment(btnLogin, Pos.TOP_RIGHT);
        fields = new GridPane();
        loginPane.setCenter(fields);
        fields.setPadding(new Insets(40));
        fields.setVgap(10);
        fields.setHgap(10);
        fields.add(lblName, 0, 0);
        fields.add(lblPass, 0, 1);
        fields.add(txtName, 1, 0);
        fields.add(txtPass, 1, 1);
        btnLogin.setOnAction(e -> login());
    }

    public void setTitle() {
        this.lblTitle.setFont(new Font("Arial", 20));
        this.lblTitle.setMaxWidth(Double.MAX_VALUE);
        this.lblTitle.setAlignment(Pos.CENTER);
    }

    public void login() {
        theStage.setScene(userListScene);
    }

    public void newIssue() {
        theStage.setScene(userDetailScene);
    }

    public void cancel() {
        theStage.setScene(userListScene);
    }
}
