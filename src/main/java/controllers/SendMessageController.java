package controllers;

import exceptions.StudentNotFound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.MessageService;


public class SendMessageController {
    @FXML
    public TextField messageField;
    @FXML
    public Text sendingMessage;

    public void handleSendingButton(ActionEvent actionEvent) throws StudentNotFound {
        MessageService.addMessage(messageField.getText());
        sendingMessage.setText("Message sent");
    }
}
