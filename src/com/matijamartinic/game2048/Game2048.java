package com.matijamartinic.game2048;

import com.matijamartinic.game2048.calculator.Calculator;
import com.matijamartinic.game2048.exception.GameOver;
import com.matijamartinic.game2048.exception.InvalidMove;

import java.util.ArrayList;

final public class Game2048 {

    private class Coordinates {

        private int x, y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }


    private final Calculator calculator;
    int NUM_OF_ROWS = 4;
    int NUM_OF_COLS = 4;

    private int[][] tiles;

    public Game2048 (Calculator calculator)
    {
        this.calculator = calculator;
        this.tiles = new int[NUM_OF_ROWS][NUM_OF_COLS];
        this.reset();

        // add two random numbers in the beginning
        addRandomNumber(this.getEmptyCoordinates());
        addRandomNumber(this.getEmptyCoordinates());
    }

    /**
     *
     */
    private void reset()
    {
        for (int i=0; i<NUM_OF_ROWS; i++) {
            for (int j=0; j<NUM_OF_COLS; j++) {
                this.tiles[i][j]= 0;
            }
        }
    }

    /**
     * adds random number to the game. If it failes, exception is thrown which means game is over.
     */
    private void addRandomNumber(ArrayList<Coordinates> coordinates)
    {
        int number = Math.random() < 0.90 ? 1: 2;

        if (coordinates.isEmpty()) {
            return;
        }

        int randomIndex = (int)(Math.random()*(coordinates.size() - 1));
        Coordinates randomCoordinate = coordinates.get(randomIndex);
        this.tiles[randomCoordinate.getX()][randomCoordinate.getY()] = number;
    }

    /**
     *
     * @return
     */
    private ArrayList<Coordinates> getEmptyCoordinates() {
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        for (int i=0; i<NUM_OF_ROWS; i++) {
            for (int j=0; j<NUM_OF_COLS; j++) {
                if (this.tiles[i][j] == 0) {
                    coordinates.add(new Coordinates(i, j));
                }
            }
        }
        return coordinates;
    }

    /**
     *
     * @param direction
     */
    public void move(MoveDirection direction)
    {
        try {
            this.tiles = this.calculator.calculate(this.tiles, this.NUM_OF_ROWS, this.NUM_OF_COLS, direction);
        } catch (InvalidMove invalidMove) {
            if (this.getEmptyCoordinates().isEmpty()) {
                throw new GameOver();
            }
            throw invalidMove;
        }

        this.addRandomNumber(this.getEmptyCoordinates());
    }

    public int[][] getCurrentGameState()
    {
        return this.tiles;
    }
}
