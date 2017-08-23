package com.manaspurohit.tictactoe

import com.manaspurohit.tictactoe.model.TicTacToeModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Before
    fun setup() {
        TicTacToeModel.resetGame()
    }

    @Test
    fun test_reset() {
        for (i in 0..2) {
            for (j in 0..2) {
                assertEquals(TicTacToeModel.EMPTY, TicTacToeModel.getFieldContent(i, j))
            }
        }
    }

    @Test
    fun set_andGet_FieldContent() {
        assertEquals(TicTacToeModel.EMPTY, TicTacToeModel.getFieldContent(0, 0))

        TicTacToeModel.setFieldContent(0, 0, TicTacToeModel.CIRCLE)
        assertEquals(TicTacToeModel.CIRCLE, TicTacToeModel.getFieldContent(0, 0))

        TicTacToeModel.setFieldContent(0, 0, TicTacToeModel.CROSS)
        assertEquals(TicTacToeModel.CROSS, TicTacToeModel.getFieldContent(0, 0))
    }

    @Test
    fun test_undo() {
        assertEquals(TicTacToeModel.EMPTY, TicTacToeModel.getFieldContent(0, 0))

        TicTacToeModel.setFieldContent(0, 0, TicTacToeModel.CIRCLE)
        TicTacToeModel.switchPlayer()
        assertEquals(TicTacToeModel.CIRCLE, TicTacToeModel.getFieldContent(0, 0))

        TicTacToeModel.undo()
        assertEquals(TicTacToeModel.EMPTY, TicTacToeModel.getFieldContent(0, 0))
    }

    @Test
    fun test_nextPlayer() {
        assertEquals(TicTacToeModel.CIRCLE, TicTacToeModel.nextPlayer)

        TicTacToeModel.setFieldContent(2, 1, TicTacToeModel.nextPlayer)
        TicTacToeModel.switchPlayer()
        assertEquals(TicTacToeModel.CROSS, TicTacToeModel.nextPlayer)

        TicTacToeModel.undo()
        assertEquals(TicTacToeModel.CIRCLE, TicTacToeModel.nextPlayer)
    }

    @Test
    fun test_winner() {
        assertEquals(TicTacToeModel.EMPTY, TicTacToeModel.wonPlayer)

    }


}