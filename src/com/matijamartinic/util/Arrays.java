package com.matijamartinic.util;

public class Arrays {

    public int[][] copy(int array[][])
    {
        int [][]target = new int[array.length][];

        for (int i = 0; i < array.length; i++) {
            target[i] = java.util.Arrays.copyOf(array[i], array[i].length);
        }

        return target;
    }

}
