package com.github.sovel011.sudokusolver.service.strategy

import com.github.sovel011.sudokusolver.data.SudokuGrid

interface SolutionStrategy {

    fun solve(sudokuGrid: SudokuGrid): SudokuGrid
}