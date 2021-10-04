package ru.ssau.tk.AA_TP_Labs.matrix_operations.client.controller.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.ssau.tk.AA_TP_Labs.matrix_operations.client.controller.MatrixClient;

import java.io.IOException;

public class IpFrameController {
    @FXML
    public TextField ipField;
    @FXML
    public Button okButton;

    @FXML
    public void initialize() {
        okButton.setOnAction(actionEvent -> {
            String host = ipField.getText();

            MatrixClient matrixClient = MatrixClient.getInstance();
            matrixClient.setHost(host);
            try{
                matrixClient.start();

                Stage stage = (Stage) okButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/matrixOperationsFrame.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);

            } catch (IOException ex){
                ex.printStackTrace();
            }
        });
    }
}
