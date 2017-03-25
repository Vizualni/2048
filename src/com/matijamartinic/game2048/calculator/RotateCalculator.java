package com.matijamartinic.game2048.calculator;

import com.matijamartinic.game2048.MoveDirection;
import com.matijamartinic.game2048.exception.InvalidMove;

import java.util.Arrays;

final public class RotateCalculator implements Calculator {

    private final com.matijamartinic.util.Arrays arrayUtil;

    public RotateCalculator(com.matijamartinic.util.Arrays arrayUtil)
    {

        this.arrayUtil = arrayUtil;
    }

    public int[][] calculate(int tiles[][], int rowSize, int colSize, MoveDirection direction)
    {
        int [][]newTiles = this.arrayUtil.copy(tiles);

        // rotate and calculate
        switch (direction) {
            case DOWN:
                newTiles = rotateLeft(newTiles);
            case RIGHT:
                newTiles = rotateLeft(newTiles);
            case UP:
                newTiles = rotateLeft(newTiles);
            case LEFT:
                newTiles = this.left(newTiles, rowSize, colSize);
                break;
        }

        // need to rotate again to get to first position
        switch (direction) {
            case UP:
                newTiles = rotateLeft(newTiles);
            case RIGHT:
                newTiles = rotateLeft(newTiles);
            case DOWN:
                newTiles = rotateLeft(newTiles);
        }

        return newTiles;

    }

    /**
     *
     * @param tiles
     * @param rowSize
     * @param colSize
     */
    private int[][] left(int tiles[][], int rowSize, int colSize)
    {
        int [][]newTiles = new int[rowSize][colSize];
        int [][]newTiles2 = new int[rowSize][colSize];

        boolean firstMove = moveAllToLeft(tiles, newTiles, rowSize, colSize);

        findDuplicatesAndMergeThem(newTiles, rowSize, colSize);

        boolean secondMove = moveAllToLeft(newTiles, newTiles2, rowSize, colSize);

        if (Arrays.deepEquals(tiles, newTiles2)) {
            throw new InvalidMove();
        }
        return newTiles2;
    }

    private int[][] rotateLeft(int tiles[][])
    {
        print(tiles);
        int rowSize = tiles.length;
        int colSize = tiles[0].length;
        int [][]rotated = new int[colSize][rowSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                rotated[colSize - j - 1][i] = tiles[i][j];
            }
        }
        print(rotated);

        System.out.println();
        return rotated;
    }


    private void print(int [][]tiles)
    {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                System.out.print(tiles[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param originalTiles
     * @param copyToTiles
     * @param rowSize
     * @param colSize
     */
    private boolean moveAllToLeft(int originalTiles[][], int copyToTiles[][], int rowSize, int colSize)
    {
        for (int i=0; i<rowSize; i++) {
            int colPosition = 0;
            for (int j = 0; j < colSize; j++) {
                if (originalTiles[i][j] > 0) {
                    copyToTiles[i][colPosition] = originalTiles[i][j];
                    colPosition++;
                }

            }
            // reset all the rest
            for (int j = colPosition; j < colSize; j++) {
                copyToTiles[i][j] = 0;
            }
        }

        return true;
    }

    /**
     *
     * @param tiles
     * @param rowSize
     * @param colSize
     */
    private void findDuplicatesAndMergeThem(int tiles[][], int rowSize, int colSize)
    {
        for (int i=0; i<rowSize; i++) {
            for (int j=1; j<colSize; j++) {
                if (tiles[i][j] > 0 && tiles[i][j] == tiles[i][j-1]) {
                    tiles[i][j-1]++;
                    tiles[i][j] = 0;
                }
            }
        }
    }

}
