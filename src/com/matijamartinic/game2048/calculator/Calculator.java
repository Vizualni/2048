package com.matijamartinic.game2048.calculator;

import com.matijamartinic.game2048.MoveDirection;

public interface Calculator {

    /**
     * @throws com.matijamartinic.game2048.exception.InvalidMove
     * @param tiles
     * @param rowSize
     * @param colSize
     * @param direction
     * @return
     */
    int[][] calculate(int tiles[][], int rowSize, int colSize, MoveDirection direction);
}
