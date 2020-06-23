package com.github.sovel011.sudokusolver.data

import com.github.sovel011.sudokusolver.constants.CellValue

data class Cell(
    val row: Int,
    val column: Int,
    var value: CellValue = CellValue.EMPTY,
    val block: Int = findBlock(row, column)
)

private fun findBlock(row: Int, column: Int) = 3 * (row / 3) + (column / 3)
