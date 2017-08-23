package com.manaspurohit.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.manaspurohit.tictactoe.view.TicTacToeView
import org.w3c.dom.Text

class TicTacToeActivity : AppCompatActivity() {

    private lateinit var ticTacToeView: TicTacToeView
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        ticTacToeView = findViewById(R.id.ticTacToeView) as TicTacToeView
        tvStatus = findViewById(R.id.tvStatus) as TextView

        ticTacToeView.resetGame()

        val btnClear = findViewById(R.id.btnReset) as Button
        btnClear.setOnClickListener { ticTacToeView.resetGame() }
    }

    fun showMessage(message: String) {
        tvStatus.text = message
    }

    fun showSnackbar(message: String) {
        Snackbar.make(findViewById(R.id.layoutContent), message, Snackbar.LENGTH_LONG).setAction(
                "Undo", { ticTacToeView.undo() } ).show()
    }
}
