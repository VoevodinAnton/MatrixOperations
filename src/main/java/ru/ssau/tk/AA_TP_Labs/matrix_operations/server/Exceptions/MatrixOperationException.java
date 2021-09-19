package ru.ssau.tk.AA_TP_Labs.matrix_operations.server.Exceptions;

public class MatrixOperationException extends RuntimeException{

    public MatrixOperationException(){}

    public MatrixOperationException(String message){
        super(message);
    }
}
