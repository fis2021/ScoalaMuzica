package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentController {

    public void requestInstructor(ActionEvent event)throws IOException {
        Parent view2= FXMLLoader.load(getClass().getClassLoader().getResource("request_panel.fxml"));
        Scene tableScene=new Scene(view2);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableScene);
        window.show();
    }

    public void viewInstructors(ActionEvent event)throws IOException {
        Parent view2= FXMLLoader.load(getClass().getClassLoader().getResource("view_instructors.fxml"));
        Scene tableScene=new Scene(view2);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableScene);
        window.show();
    }

    public void viewMessages(ActionEvent event) throws IOException {
        Parent view2= FXMLLoader.load(getClass().getClassLoader().getResource("view_messages.fxml"));
        Scene tableScene=new Scene(view2);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableScene);
        window.show();
    }

    public void Back(ActionEvent event)throws IOException {
        Parent view2= FXMLLoader.load(getClass().getClassLoader().getResource("student_interface.fxml"));
        Scene tableScene=new Scene(view2);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableScene);
        window.show();
    }

    public void BackToLogin(ActionEvent event)throws IOException {
        Parent view2= FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Scene tableScene=new Scene(view2);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableScene);
        window.show();
    }
}