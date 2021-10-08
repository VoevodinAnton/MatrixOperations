package ru.ssau.tk.AA_TP_Labs.matrix_operations.server.controller;

import ru.ssau.tk.AA_TP_Labs.matrix_operations.library.Matrix;
import ru.ssau.tk.AA_TP_Labs.matrix_operations.server.model.utils.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Server {
    private static Server instance;

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(Constants.PORT)) {
            System.out.println("Server socket created");

            while (true) {
                Socket socket = serverSocket.accept();

                ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());

                while (!socket.isClosed()) {
                    System.out.println("Server reading from channel");

                    try {
                        ArrayList<Matrix> matrices = (ArrayList<Matrix>) objIn.readObject();
                        if (matrices != null) {
                            Matrix matrixResult = Matrix.multiply(matrices.get(0), matrices.get(1));

                            objOut.writeObject(matrixResult);
                            objOut.flush();
                        }
                    } catch (SocketException ex) {
                        System.err.println("connection reset");
                        break;
                    }
                }
                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
