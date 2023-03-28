package com.cuongnm.basic.matrix.parallelrow;

public class RowMultiplierTask implements Runnable{
    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int row;

    public RowMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int row) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
    }


    @Override
    public void run() {
        for(int i = 0; i < matrix2[0].length; i++){
            result[row][i] = 0;
            for(int k = 0; k < matrix1[row].length; k++){
                result[row][i] += matrix1[row][k] * matrix2[k][i];
            }
        }
    }
}
