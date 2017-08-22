package com.manaspurohit.tictactoe.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Custom view for tic tac toe
 */
class TicTacToeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paintBg: Paint = Paint()
    private val paintLine: Paint = Paint()
    private val paintO: Paint = Paint()
    private val paintX: Paint = Paint()

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

    }

    private fun drawGameArea(canvas: Canvas) {
        canvas.drawLine(width / 3.0f, 0.0f, width / 3.0f, height.toFloat(), paintLine)
        canvas.drawLine(width / 3.0f * 2, 0.0f, width / 3.0f * 2, height.toFloat(), paintLine)

        canvas.drawLine(0.0f, height / 3.0f, width.toFloat(), height / 3.0f, paintLine)
        canvas.drawLine(0.0f, height / 3.0f * 2, width.toFloat(), height / 3.0f * 2, paintLine)
    }

}