package ru.ssau.tk.AA_TP_Labs.matrix_operations.client.controller;

import ru.ssau.tk.AA_TP_Labs.matrix_operations.client.model.utils.Constants;
import ru.ssau.tk.AA_TP_Labs.matrix_operations.library.Matrix;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MatrixClient {
    private static MatrixClient instance;
    private String host;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public static MatrixClient getInstance() {
        if (instance == null) {
            instance = new MatrixClient();
        }
        return instance;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Matrix getMatrix() throws IOException, ClassNotFoundException {
        Matrix matrix = (Matrix) in.readObject();

        return matrix;
    }

    public void sendMatrices(Matrix matrixOne, Matrix matrixTwo) throws IOException {
        List<Matrix> matrices = new ArrayList<>();
        matrices.add(matrixOne);
        matrices.add(matrixTwo);

        out.writeObject(matrices);
        out.flush();
    }

    public void start() throws IOException {
        socket = new Socket(host, Constants.PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }
}
