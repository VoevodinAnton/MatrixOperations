package ru.ssau.tk.AA_TP_Labs.matrix_operations.library;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Matrix {
    private double[][] matrix;

    public Matrix(int row, int column) {
        this.matrix = new double[row][column];
    }

    public Matrix(double[][] matrix){
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getNumberOfRows(){
        return matrix.length;
    }

    public int getNumberOfColumn(){
        return matrix[0].length;
    }

    public double getValue(int row, int column){
        return matrix[row][column];
    }

    public void updateValue(int row, int column, double value){
        matrix[row][column] = value;
    }

    public static Matrix sum(Matrix a, Matrix b){
        Matrix matrixResult = new Matrix(a.getNumberOfRows(), a.getNumberOfColumn());
        matrixResult.matrix = IntStream.range(0, a.matrix.length)
                .mapToObj(r -> IntStream.range(0, a.matrix[r].length)
                        .mapToDouble(c -> a.matrix[r][c] + b.matrix[r][c]).toArray())
                .toArray(double[][]::new);
        return matrixResult;
    }

    public static Matrix multiply(Matrix a, Matrix b){
        Matrix matrixResult = new Matrix(a.getNumberOfColumn(), a.getNumberOfRows());

        matrixResult.matrix = Arrays.stream(a.matrix)
                .map(r -> IntStream.range(0, b.matrix[0].length)
                        .mapToDouble(i -> IntStream.range(0, b.matrix.length)
                                .mapToDouble(j -> r[j] * b.matrix[j][i]).sum())
                        .toArray())
                .toArray(double[][]::new);

        System.out.println(Arrays.stream(a.matrix)
                .map(r -> IntStream.range(0, b.matrix[0].length)));

        return matrixResult;
    }

    public static void writeMatrixToFile(Matrix matrix){

    }
}