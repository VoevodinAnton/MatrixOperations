package ru.ssau.tk.AA_TP_Labs.matrix_operations.client.controller.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.ssau.tk.AA_TP_Labs.matrix_operations.client.controller.MatrixClient;
import ru.ssau.tk.AA_TP_Labs.matrix_operations.library.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixController {

    @FXML
    public Button calculateButton;

    @FXML
    public Button chooseFile;

    @FXML
    public Label firstFile;

    @FXML
    public Label secondFile;

    @FXML
    public void initialize() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        final List<Matrix> matrices = new ArrayList<>();

        chooseFile.setOnAction(actionEvent -> {
            List<File> selectedFiles = fileChooser.showOpenMultipleDialog((Stage) chooseFile.getScene().getWindow());
            System.out.println(selectedFiles.toString());

            firstFile.setText(selectedFiles.get(0).getName());
            secondFile.setText(selectedFiles.get(1).getName());

            if (selectedFiles != null) {
                try {
                    for (File file : selectedFiles) {
                        Matrix matrix = Matrix.readMatrixFromFile(file);
                        matrices.add(matrix);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        calculateButton.setOnAction(actionEvent -> {
            try {
                MatrixClient.getInstance().sendMatrices(matrices.get(0), matrices.get(1));
                Matrix matrixResult = MatrixClient.getInstance().getMatrix();
                System.out.println(matrixResult);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
