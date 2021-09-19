package ru.ssau.tk.AA_TP_Labs.matrix_operations.library;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MatrixTest {

    @Test
    public void testSum() {
        Matrix matrixA = new Matrix(new double[][]{{1, 2}, {1, 2}});
        Matrix matrixB = new Matrix(new double[][]{{1, 2}, {3, 4}});

        Matrix matrixResult = Matrix.sum(matrixA, matrixB);

        assertEquals(matrixResult.getMatrix()[1][1], 6.0);
    }

    @Test
    public void testMultiply() {
        Matrix matrixA = new Matrix(new double[][]{{1, 2}, {1, 2}});
        Matrix matrixB = new Matrix(new double[][]{{1}, {3}});

        Matrix matrixResult = Matrix.multiply(matrixA, matrixB);

        assertEquals(matrixResult.getMatrix()[0][0], 7.0);
    }
}