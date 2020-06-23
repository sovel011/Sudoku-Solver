package com.github.sovel011.sudokusolver.data

import com.github.sovel011.sudokusolver.constants.CellValue

data class SudokuGrid(val cells: List<Cell> = emptyGrid()) {

    companion object {
        fun withCells(cells: List<Cell>): SudokuGrid {
            val sudokuGrid = SudokuGrid()
            cells.forEach { sudokuGrid.updateCell(it.row, it.column, it.value) }
            return sudokuGrid
        }

        private fun emptyGrid(): List<Cell> {
            val cellList = mutableListOf<Cell>()
            for (i in 0..8) {
                for (j in 0..8) {
                    cellList.add(Cell(i, j))
                }
            }
            return cellList
        }
    }

    fun getCell(row: Int, column: Int) = cells.first { it.row == row && it.column == column }

    fun updateCell(row: Int, column: Int, value: CellValue) {
        getCell(row, column).value = value
    }
}