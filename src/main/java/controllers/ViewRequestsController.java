package controllers;

import exceptions.InstructorNotFound;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Student;
import javafx.stage.Stage;
import services.InstructorService;

import java.io.IOException;

public class ViewRequestsController {

    @FXML
    public TableView<Student> studentTable;

    @FXML
    public TableColumn<Student, String> studentNameColumn;
    @FXML
    public TableColumn<Student, Double> studentEntryHourColumn;
    @FXML
    public TableColumn<Student, Double> studentExitHourColumn;
    public static String studentToSend;


    @FXML
    public void initialize() throws InstructorNotFound {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentEntryHourColumn.setCellValueFactory(new PropertyValueFactory<>("entryHour"));
        studentExitHourColumn.setCellValueFactory(new PropertyValueFactory<>("exitHour"));
        addApprovalButtonToTable();
        addRejectionButtonToTable();
        setRequests();
        studentTable.setItems(requestList);
    }

    public ObservableList<Student> requestList;

    public void setRequests() throws InstructorNotFound {
        InstructorService.loadInstructor();
        requestList = FXCollections.observableArrayList(InstructorService.getRequests());
    }

    private void addApprovalButtonToTable() {
        TableColumn<Student, Void> colBtn = new TableColumn("Accept Button Column");

        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<TableColumn<Student, Void>, TableCell<Student, Void>>() {
            @Override
            public TableCell<Student, Void> call(final TableColumn<Student, Void> param) {
                final TableCell<Student, Void> cell = new TableCell<Student, Void>() {

                    private final Button btn = new Button("Accept");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Student student = getTableView().getItems().get(getIndex());
                            studentToSend = student.getStudentName();
                            try {
                                InstructorService.loadInstructor();
                                InstructorService.addStudent(student.getStudentName(), student.getEntryHour(), student.getExitHour());

                                Parent view2 = FXMLLoader.load(getClass().getClassLoader().getResource("send_message.fxml"));
                                Scene tableScene = new Scene(view2);
                                Stage window = new Stage();
                                window.setScene(tableScene);
                                window.show();

                                studentTable.getItems().remove(getTableView().getItems().get(getIndex()));
                                InstructorService.deleteRequest(student.getStudentName());
                                InstructorService.loadInstructor();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InstructorNotFound instructorNotFound) {
                                instructorNotFound.printStackTrace();
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        studentTable.getColumns().add(colBtn);

    }

    public static String getStudentToSend() {
        return studentToSend;
    }

    private void addRejectionButtonToTable() {
        TableColumn<Student, Void> colBtn = new TableColumn("Reject Button Column");

        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<TableColumn<Student, Void>, TableCell<Student, Void>>() {
            @Override
            public TableCell<Student, Void> call(final TableColumn<Student, Void> param) {
                final TableCell<Student, Void> cell = new TableCell<Student, Void>() {

                    private final Button btn = new Button("Reject");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Student student = getTableView().getItems().get(getIndex());
                            studentToSend = student.getStudentName();
                            try {
                                studentTable.getItems().remove(getTableView().getItems().get(getIndex()));
                                InstructorService.deleteRequest(student.getStudentName());
                                InstructorService.loadInstructor();

                                Parent view2 = FXMLLoader.load(getClass().getClassLoader().getResource("send_message.fxml"));
                                Scene tableScene = new Scene(view2);
                                Stage window = new Stage();
                                window.setScene(tableScene);
                                window.show();

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InstructorNotFound instructorNotFound) {
                                instructorNotFound.printStackTrace();
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        studentTable.getColumns().add(colBtn);

    }


    public void Back(ActionEvent event) throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getClassLoader().getResource("instructor_interface.fxml"));
        Scene tableScene = new Scene(view2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableScene);
        window.show();
    }
}


