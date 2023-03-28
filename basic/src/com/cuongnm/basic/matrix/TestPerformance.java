package com.cuongnm.basic.matrix;

import com.cuongnm.basic.matrix.parallelgroup.ParallelGroupMultiplier;
import com.cuongnm.basic.matrix.parallelrow.ParallelRowMultiplier;
import com.cuongnm.basic.matrix.serial.SerialMultiplier;

public class TestPerformance {
    public static void main(String[] args) {
        double[][] m1 = Matrix.generate(500, 500);
        double[][] m2 = Matrix.generate(500, 500);
        double[][] result = new double[m1.length][m2[0].length];

        long startSerial = System.currentTimeMillis();
        SerialMultiplier.multiply(m1, m2, result);
        System.out.printf("Serial: %d%n", System.currentTimeMillis() - startSerial);
//        Matrix.print(result);

//        long startInvidualParallel = System.currentTimeMillis();
//        ParallelIndividualMultiplier.multiply(m1, m2, result);
//        System.out.printf("Parallel: %d%n", System.currentTimeMillis() - startInvidualParallel);
//        Matrix.print(result);

        long startRowParallel = System.currentTimeMillis();
        ParallelRowMultiplier.multiply(m1, m2, result);
        System.out.printf("Parallel row: %d%n", System.currentTimeMillis() - startRowParallel);
//        Matrix.print(result);

        long startGroupParallel = System.currentTimeMillis();
        ParallelGroupMultiplier.multiply(m1, m2, result);
        System.out.printf("Parallel group: %d%n", System.currentTimeMillis() - startGroupParallel);

    }
}
