package com.github.sovel011.sudokusolver.service

import com.github.sovel011.sudokusolver.constants.GridState
import com.github.sovel011.sudokusolver.data.SudokuGrid

interface SudokuGridEvaluator {

    /**
     * Analyzes Sudoku grid and determines it's current state including whether it is invalid or completed
     * @param sudokuGrid Input sudoku grid
     * @return Current state of the Sudoku grid
     */
    fun evaluateGrid(sudokuGrid: SudokuGrid): GridState

    /**
     * Default method which returns true if the input Sudoku grid is completed or not
     * @param sudokuGrid Input sudoku grid
     * @return true if the input grid is completed, otherwise false
     */
    fun isComplete(sudokuGrid: SudokuGrid): Boolean {
        return evaluateGrid(sudokuGrid) == GridState.COMPLETE
    }
}