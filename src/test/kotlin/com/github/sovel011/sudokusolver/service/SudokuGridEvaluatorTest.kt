package com.github.sovel011.sudokusolver.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.sovel011.sudokusolver.constants.CellValue
import com.github.sovel011.sudokusolver.constants.GridState
import com.github.sovel011.sudokusolver.data.Cell
import com.github.sovel011.sudokusolver.data.SudokuGrid
import com.github.sovel011.sudokusolver.service.impl.SudokuGridEvaluatorImpl
import org.junit.Test
import org.junit.jupiter.api.Assertions

class SudokuGridEvaluatorTest {

    private val sudokuGridEvaluator = SudokuGridEvaluatorImpl()

    @Test
    fun `Empty Sudoku grid is valid`() {
        Assertions.assertEquals(GridState.VALID, sudokuGridEvaluator.evaluateGrid(SudokuGrid()))
        Assertions.assertFalse(sudokuGridEvaluator.isComplete(SudokuGrid()))
    }

    @Test
    fun `Multiple occurrences of value in same row is invalid`() {
        val cells = listOf(Cell(0, 0, CellValue.ONE), Cell(0, 1, CellValue.ONE))
        val grid = SudokuGrid.withCells(cells)
        Assertions.assertEquals(GridState.INVALID, sudokuGridEvaluator.evaluateGrid(grid))
    }

    @Test
    fun `Multiple occurrences of value in same column is invalid`() {
        val cells = listOf(Cell(0, 0, CellValue.FIVE), Cell(1, 0, CellValue.FIVE))
        val grid = SudokuGrid.withCells(cells)
        Assertions.assertEquals(GridState.INVALID, sudokuGridEvaluator.evaluateGrid(grid))
    }

    @Test
    fun `Multiple occurrences of value in same block is invalid`() {
        val cells = listOf(Cell(3, 8, CellValue.NINE), Cell(5, 6, CellValue.NINE))
        val grid = SudokuGrid.withCells(cells)
        Assertions.assertEquals(GridState.INVALID, sudokuGridEvaluator.evaluateGrid(grid))
    }

    @Test
    fun `Fully populated grid is evaluated as completed`() {
        val fileContent = SudokuGridEvaluatorTest::class.java.getResource("/completed-grid.json").readText()
        val completedGrid = jacksonObjectMapper().readValue(fileContent, SudokuGrid::class.java)
        Assertions.assertEquals(GridState.COMPLETE, sudokuGridEvaluator.evaluateGrid(completedGrid))
        Assertions.assertTrue(sudokuGridEvaluator.isComplete(completedGrid))
    }
}