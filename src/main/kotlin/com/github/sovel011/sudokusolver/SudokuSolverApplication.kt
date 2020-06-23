package com.github.sovel011.sudokusolver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SudokuSolverApplication

fun main(args: Array<String>) {
	runApplication<SudokuSolverApplication>(*args)
}
