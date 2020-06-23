package com.github.sovel011.sudokusolver.service.impl

import com.github.sovel011.sudokusolver.constants.CellValue
import com.github.sovel011.sudokusolver.constants.GridState
import com.github.sovel011.sudokusolver.data.Cell
import com.github.sovel011.sudokusolver.data.SudokuGrid
import com.github.sovel011.sudokusolver.service.SudokuGridEvaluator
import org.springframework.stereotype.Service

@Service
class SudokuGridEvaluatorImpl : SudokuGridEvaluator {

    override fun evaluateGrid(sudokuGrid: SudokuGrid): GridState {
        val hasConflicts = hasConflicts(sudokuGrid)
        val hasEmptyCells = hasEmptyCells(sudokuGrid)

        return when {
            !hasConflicts && !hasEmptyCells -> GridState.COMPLETE
            !hasConflicts -> GridState.VALID
            else -> GridState.INVALID
        }
    }

    private fun hasConflicts(sudokuGrid: SudokuGrid): Boolean {
        for (cell in sudokuGrid.cells) {
            val conflict = sudokuGrid.cells
                .filter { it.value != CellValue.EMPTY }
                .find { isConflictingCell(cell, it) }

            if (conflict != null) {
                return true
            }
        }
        return false
    }

    private fun isConflictingCell(first: Cell, second: Cell): Boolean {
        return first != second
                && first.value == second.value
                && (first.row == second.row || first.column == second.column || first.block == second.block)
    }

    private fun hasEmptyCells(sudokuGrid: SudokuGrid) = sudokuGrid.cells.any { it.value == CellValue.EMPTY }
}