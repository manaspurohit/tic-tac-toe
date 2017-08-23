package com.manaspurohit.tictactoe.model

import android.graphics.Point

/**
 * Data model for tic tac toe
 */
object TicTacToeModel {
    const val EMPTY: Short = 0
    const val CIRCLE: Short = 1
    const val CROSS: Short = 2

    private var model = arrayOf(
            shortArrayOf(EMPTY, EMPTY, EMPTY),
            shortArrayOf(EMPTY, EMPTY, EMPTY),
            shortArrayOf(EMPTY, EMPTY, EMPTY))

    var nextPlayer: Short = CIRCLE
        private set

    var wonPlayer: Short = EMPTY
        private set

    private var undoStep: Point? = null

    fun resetGame() {
        nextPlayer = CIRCLE
        wonPlayer = EMPTY

        model = model.map { it.map { EMPTY }.toShortArray() }.toTypedArray()
    }

    fun getFieldContent(x: Int, y: Int): Short = model[x][y]

    fun setFieldContent(x: Int, y: Int, value: Short) {
        undoStep = Point(x, y)
        model[x][y] = value
        checkWinner()
    }

    private fun checkWinner() {
        for (i in 0..2) {
            if (model[i][1] != EMPTY && model[i][1] == model[i][0] && model[i][1] == model[i][2]) {
                wonPlayer = model[i][1]
                undoStep = null
                return
            }
        }

        for (i in 0..2) {
            if (model[1][i] != EMPTY && model[1][i] == model[0][i] && model[1][i] == model[2][i]) {
                wonPlayer = model[1][i]
                undoStep = null
                return
            }
        }

        if (model[1][1] != EMPTY &&
                (model[1][1] == model[0][0] && model[1][1] == model[2][2] ||
                        model[1][1] == model[2][0] && model[1][1] == model[0][2])) {
            wonPlayer = model[1][1]
            undoStep = null
            return
        }
    }

    fun switchPlayer() {
        nextPlayer = if (nextPlayer == CIRCLE) CROSS else CIRCLE
    }

    fun undo() {
        undoStep?.let {
            model[it.x][it.y] = EMPTY
            switchPlayer()
        }
    }

}