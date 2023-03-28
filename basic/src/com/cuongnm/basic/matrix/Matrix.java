package com.cuongnm.basic.matrix;

import java.util.Random;

public class Matrix {
    public static double[][] generate(int row, int col){
        double[][] ret = new double[row][col];
        Random r = new Random();
        for(int i = 0 ; i < row; i++){
            for(int j = 0 ; j < col; j++){
                ret[i][j] = r.nextDouble()*10;
            }
        }
        return ret;
    }

    public static void print(double[][] result){
        for(int i = 0 ; i < result.length; i++){
            for(int j = 0 ; j < result[0].length; j++){
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }
}
