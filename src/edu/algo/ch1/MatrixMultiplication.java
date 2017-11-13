package edu.algo.ch1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatrixMultiplication {

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.print(matrix[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] readMatrix(int rows, int columns, BufferedReader br) throws IOException {
        // discard empty line
        br.readLine();

        int[][] matrix = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            String[] columnValues = br.readLine().trim().split(" ");
            for (int column = 0; column < columns; column++) {
                matrix[row][column] = Integer.parseInt(columnValues[column].trim());
            }
        }
        return matrix;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/ch1/matrix.txt"));
        String line;
        long start = System.currentTimeMillis();
        int N = Integer.parseInt(br.readLine().trim());
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.length() <= 0) continue;

            // rows and columns
            String[] temp = line.split(" ");
            int rowsA = Integer.parseInt(temp[0]);
            int columnsA = Integer.parseInt(temp[1]);

            int rowsB = Integer.parseInt(temp[2]);
            int columnsB = Integer.parseInt(temp[3]);

            // Read matrices
            int[][] matrixA = readMatrix(rowsA, columnsA, br);
            int[][] matrixB = readMatrix(rowsB, columnsB, br);
            int[][] expectedOutput = readMatrix(rowsA, columnsB, br);

            System.out.println("Matrix A:");
            printMatrix(matrixA);

            System.out.println("Matrix B:");
            printMatrix(matrixA);

            System.out.println("Expected output:");
            printMatrix(expectedOutput);
            
            // multiply matrix
            int[][] outputMatrix = new int[rowsA][columnsB];
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < columnsB; j++) {
                    outputMatrix[i][j] = 0;
                    for (int k = 0; k < columnsA; k++) {
                        outputMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }

            System.out.println("Output matrix:");
            printMatrix(outputMatrix);

            // Validate output
            boolean matchFailed = false;
            for (int i = 0; i < rowsA && matchFailed == false; i++) {
                for (int j = 0; j < columnsB; j++) {
                    if (outputMatrix[i][j] != expectedOutput[i][j]) {
                        matchFailed = true;
                        break;
                    }
                }
            }
            if (matchFailed)
                System.out.println("Output does not match expected output.");
            else
                System.out.println("Output matches expected output.");

            System.out.println();
        }
        br.close();
        System.out.println("Time: " + ((System.currentTimeMillis() - start)/1000) + "s");
    }
}
