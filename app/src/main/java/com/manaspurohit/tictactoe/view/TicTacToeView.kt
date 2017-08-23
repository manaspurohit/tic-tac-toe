package com.manaspurohit.tictactoe.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.manaspurohit.tictactoe.TicTacToeActivity
import com.manaspurohit.tictactoe.model.TicTacToeModel

/**
 * Custom view for tic tac toe
 */
class TicTacToeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paintBg: Paint = Paint()
    private val paintLine: Paint = Paint()
    private val paintO: Paint = Paint()
    private val paintX: Paint = Paint()

    private var tempMove: PointF? = null

    init {
        val strokeWidth = 5.0f
        paintBg.color = Color.BLACK
        paintBg.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = strokeWidth

        paintO.color = Color.GREEN
        paintO.style = Paint.Style.STROKE
        paintO.strokeWidth = strokeWidth

        paintX.color = Color.RED
        paintX.style = Paint.Style.STROKE
        paintX.strokeWidth = strokeWidth
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) {
            return
        }

        canvas.drawRect(0.0f, 0.0f, width.toFloat(), height.toFloat(), paintBg)
        drawGameArea(canvas)
        drawPlayers(canvas)

        tempMove?.let {
            when (TicTacToeModel.nextPlayer) {
                TicTacToeModel.CIRCLE -> {
                    canvas.drawCircle(it.x, it.y, width / 6.0f, paintO)
                }
                TicTacToeModel.CROSS -> {
                    canvas.drawLine(it.x - width / 6, it.y - height / 6,
                            it.x + width / 6,
                            it.y + height / 6, paintX)

                    canvas.drawLine(it.x + width / 6, it.y - height / 6,
                            it.x - width / 6, it.y + height / 6, paintX)
                }
            }
        }
    }

    private fun drawGameArea(canvas: Canvas) {
        canvas.drawLine(width / 3.0f, 0.0f, width / 3.0f, height.toFloat(), paintLine)
        canvas.drawLine(width / 3.0f * 2, 0.0f, width / 3.0f * 2, height.toFloat(), paintLine)

        canvas.drawLine(0.0f, height / 3.0f, width.toFloat(), height / 3.0f, paintLine)
        canvas.drawLine(0.0f, height / 3.0f * 2, width.toFloat(), height / 3.0f * 2, paintLine)
    }

    private fun drawPlayers(canvas: Canvas) {
        for (i in 0..2) {
            for (j in 0..2) {
                when (TicTacToeModel.getFieldContent(i, j)) {
                    TicTacToeModel.CIRCLE -> {
                        val centerX = i * width / 3 + width / 6
                        val centerY = j * height / 3 + height / 6
                        val radius = height / 6 - 2

                        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat(), paintO)
                    }
                    TicTacToeModel.CROSS -> {
                        canvas.drawLine(i * width / 3.0f,
                                j * height / 3.0f,
                                width * (i + 1) / 3.0f,
                                height * (j + 1) / 3.0f,
                                paintX)
                        canvas.drawLine(width * (i + 1) / 3.0f,
                                j * height / 3.0f,
                                i * width / 3.0f,
                                height * (j + 1) / 3.0f,
                                paintX)
                    }
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null || TicTacToeModel.wonPlayer != TicTacToeModel.EMPTY) {
            return true
        }

        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                tempMove = PointF(event.x, event.y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                val x: Int = event.x.toInt() / (width / 3)
                val y: Int = event.y.toInt() / (height / 3)

                when (TicTacToeModel.getFieldContent(x, y)) {
                    TicTacToeModel.EMPTY -> {
                        TicTacToeModel.setFieldContent(x, y, TicTacToeModel.nextPlayer)
                        TicTacToeModel.switchPlayer()

                        when (TicTacToeModel.wonPlayer) {
                            TicTacToeModel.CIRCLE, TicTacToeModel.CROSS -> {
                                val winner = if (TicTacToeModel.wonPlayer == TicTacToeModel.CIRCLE)
                                    "O" else "X"
                                (context as TicTacToeActivity).showMessage("The winner is:" + " " + winner)
                            }
                            else -> {
                                val next = if (TicTacToeModel.nextPlayer == TicTacToeModel.CIRCLE)
                                    "O" else "X"
                                (context as TicTacToeActivity).showSnackbar("The next player is:" + " " + next)
                                (context as TicTacToeActivity).showMessage("")
                            }
                        }
                    }
                    else -> {
                        (context as TicTacToeActivity).showMessage("You cannot place here")
                    }
                }

                tempMove = null
                invalidate()
            }
        }

        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }

    fun resetGame() {
        TicTacToeModel.resetGame()
        (context as TicTacToeActivity).showMessage("")
        invalidate()
    }

    fun undo() {
        TicTacToeModel.undo()
        invalidate()
    }

}