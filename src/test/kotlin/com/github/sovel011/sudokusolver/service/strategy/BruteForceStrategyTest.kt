package com.github.sovel011.sudokusolver.service.strategy

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.sovel011.sudokusolver.constants.CellValue
import com.github.sovel011.sudokusolver.data.SudokuGrid
import com.github.sovel011.sudokusolver.service.SudokuGridEvaluatorTest
import com.github.sovel011.sudokusolver.service.impl.SudokuGridEvaluatorImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class BruteForceStrategyTest {

    private val sudokuGridEvaluator = SudokuGridEvaluatorImpl()
    private val bruteForceStrategy = BruteForceStrategy(sudokuGridEvaluator)


    @Test
    fun `solve successfully executes on already completed grid`() {
        val fileContent = SudokuGridEvaluatorTest::class.java.getResource("/completed-grid.json").readText()
        val completedGrid = jacksonObjectMapper().readValue(fileContent, SudokuGrid::class.java)

        Assertions.assertEquals(completedGrid, bruteForceStrategy.solve(completedGrid))
    }

    @Test
    fun `solve successfully executes on nearly completed grid`() {
        val fileContent = SudokuGridEvaluatorTest::class.java.getResource("/nearly-completed-grid.json").readText()
        val inputGrid = jacksonObjectMapper().readValue(fileContent, SudokuGrid::class.java)

        val completedGrid = bruteForceStrategy.solve(inputGrid)
        Assertions.assertEquals(CellValue.SEVEN, completedGrid.getCell(8, 7).value)
        Assertions.assertEquals(CellValue.NINE, completedGrid.getCell(8, 8).value)
    }

    @Test
    fun `solve successfully executes on easy puzzle`() {
        val fileContent = SudokuGridEvaluatorTest::class.java.getResource("/easy-puzzle.json").readText()
        val inputGrid = jacksonObjectMapper().readValue(fileContent, SudokuGrid::class.java)

        val completedGrid = bruteForceStrategy.solve(inputGrid)
        Assertions.assertTrue(sudokuGridEvaluator.isComplete(completedGrid))
    }
}