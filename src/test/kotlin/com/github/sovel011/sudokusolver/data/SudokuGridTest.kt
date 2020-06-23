package com.github.sovel011.sudokusolver.data

import com.github.sovel011.sudokusolver.constants.CellValue
import org.junit.Test
import org.junit.jupiter.api.Assertions

class SudokuGridTest {

    @Test
    fun `sudoku grid is initialized with empty cells`() {
        val sudokuGrid = SudokuGrid()

        Assertions.assertEquals(81, sudokuGrid.cells.size)
        Assertions.assertTrue(sudokuGrid.cells.all { it.value == CellValue.EMPTY })
        Assertions.assertEquals(0, sudokuGrid.getCell(0, 0).block)
        Assertions.assertEquals(0, sudokuGrid.getCell(0, 1).block)
        Assertions.assertEquals(0, sudokuGrid.getCell(0, 2).block)
        Assertions.assertEquals(4, sudokuGrid.getCell(4, 4).block)
        Assertions.assertEquals(8, sudokuGrid.getCell(8, 8).block)
    }

    @Test
    fun `values in sudoku cells can be updated`() {
        val sudokuGrid = SudokuGrid()

        Assertions.assertEquals(CellValue.EMPTY, sudokuGrid.getCell(5, 1).value)
        sudokuGrid.updateCell(5, 1, CellValue.FOUR)
        Assertions.assertEquals(CellValue.FOUR, sudokuGrid.getCell(5, 1).value)
    }

    @Test
    fun `Sudoku grid can be initialized with a set of cell values`() {
        val initialCells = listOf(
            Cell(0, 0, CellValue.ONE),
            Cell(0, 1, CellValue.TWO),
            Cell(0, 2, CellValue.THREE),
            Cell(0, 3, CellValue.FOUR),
            Cell(0, 4, CellValue.FIVE)
        )

        val sudokuGrid = SudokuGrid.withCells(initialCells)

        Assertions.assertEquals(CellValue.ONE, sudokuGrid.getCell(0, 0).value)
        Assertions.assertEquals(CellValue.TWO, sudokuGrid.getCell(0, 1).value)
        Assertions.assertEquals(CellValue.THREE, sudokuGrid.getCell(0, 2).value)
        Assertions.assertEquals(CellValue.FOUR, sudokuGrid.getCell(0, 3).value)
        Assertions.assertEquals(CellValue.FIVE, sudokuGrid.getCell(0, 4).value)
    }
}