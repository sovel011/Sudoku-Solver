package com.github.sovel011.sudokusolver.service.strategy

import com.github.sovel011.sudokusolver.constants.CellValue
import com.github.sovel011.sudokusolver.constants.GridState
import com.github.sovel011.sudokusolver.data.SudokuGrid
import com.github.sovel011.sudokusolver.service.SudokuGridEvaluator
import org.springframework.stereotype.Service
import java.util.EnumSet

@Service
class BruteForceStrategy(private val sudokuGridEvaluator: SudokuGridEvaluator) : SolutionStrategy {

    override fun solve(sudokuGrid: SudokuGrid): SudokuGrid {
        return findSolution(sudokuGrid).second
    }

    private fun findSolution(sudokuGrid: SudokuGrid): Pair<GridState, SudokuGrid> {
        val gridState = sudokuGridEvaluator.evaluateGrid(sudokuGrid)
        if (gridState == GridState.COMPLETE || gridState == GridState.INVALID) {
            return Pair(gridState, sudokuGrid)
        }

        for (cell in getEmptyCells(sudokuGrid)) {
            for (cellValue in EnumSet.range(CellValue.ONE, CellValue.NINE)) {
                sudokuGrid.updateCell(cell.row, cell.column, cellValue)
                val (newState, newGrid) = findSolution(sudokuGrid)
                if (gridState == GridState.COMPLETE) {
                    return Pair(newState, newGrid)
                }
            }
        }

        return Pair(gridState, sudokuGrid) // No solution found
    }

    private fun getEmptyCells(sudokuGrid: SudokuGrid) = sudokuGrid.cells.filter { it.value == CellValue.EMPTY }
}