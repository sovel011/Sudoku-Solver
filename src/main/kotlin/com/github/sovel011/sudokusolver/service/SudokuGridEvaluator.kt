package com.github.sovel011.sudokusolver.service

import com.github.sovel011.sudokusolver.constants.GridState
import com.github.sovel011.sudokusolver.data.SudokuGrid

interface SudokuGridEvaluator {

    fun evaluateGrid(sudokuGrid: SudokuGrid): GridState

    fun isComplete(sudokuGrid: SudokuGrid): Boolean {
        return evaluateGrid(sudokuGrid) == GridState.COMPLETE
    }
}