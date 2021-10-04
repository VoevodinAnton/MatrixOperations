package ru.ssau.tk.AA_TP_Labs.matrix_operations.client.controller;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) {
        MatrixClient matrixClient = MatrixClient.getInstance();
        matrixClient.setHost("localhost");
        try{
            matrixClient.start();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
