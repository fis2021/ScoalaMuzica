package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Student;
import services.InstructorService;

import java.io.IOException;

public class ViewStudentsController {

    @FXML
    public TableView<Student> studentTable;

    @FXML
    public TableColumn<Student, String> studentNameColumn;
    @FXML
    public TableColumn<Student, Double> studentEntryHourColumn;
    @FXML
    public TableColumn<Student, Double> studentExitHourColumn;

    private ObservableList<Student> studentsList;

    private void setStudents() throws IOException {
        studentsList = FXCollections.observableArrayList(InstructorService.getStudents());
    }

    @FXML
    public void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("Student"));
        studentEntryHourColumn.setCellValueFactory(new PropertyValueFactory<>("entryHour"));
        studentExitHourColumn.setCellValueFactory(new PropertyValueFactory<>("exitHour"));
        studentTable.setItems(studentsList);
    }


    public void Back(ActionEvent event) throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getClassLoader().getResource("instructor_interface.fxml"));
        Scene tableScene = new Scene(view2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableScene);
        window.show();
    }
}


