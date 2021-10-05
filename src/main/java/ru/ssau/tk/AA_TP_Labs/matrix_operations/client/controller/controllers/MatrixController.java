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
        calculateButton.setDisable(true);

        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        final List<Matrix> matrices = new ArrayList<>();

        chooseFile.setOnAction(actionEvent -> {
            fileChooser.setTitle("Open Resource File");
            matrices.clear();

            List<File> selectedFiles = fileChooser.showOpenMultipleDialog((Stage) chooseFile.getScene().getWindow());

            if (selectedFiles != null) {
                System.out.println(selectedFiles.toString());

                firstFile.setText(selectedFiles.get(0).getName());
                secondFile.setText(selectedFiles.get(1).getName());

                calculateButton.setDisable(false);
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
            fileChooser.setTitle("Save");
            try {
                MatrixClient.getInstance().sendMatrices(matrices.get(0), matrices.get(1));
                Matrix matrixResult = MatrixClient.getInstance().getMatrix();

                System.out.println(matrixResult);

                File saveFile = fileChooser.showSaveDialog((Stage) calculateButton.getScene().getWindow());

                if (saveFile != null){
                    System.out.println(saveFile.getPath());

                    Matrix.writeMatrixToFile(matrixResult, saveFile.getPath());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void configureFileChooser(FileChooser fileChooser){
        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extentionFilter);

        File userDirectory = new File("matrices");
        if (!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);
    }
}
