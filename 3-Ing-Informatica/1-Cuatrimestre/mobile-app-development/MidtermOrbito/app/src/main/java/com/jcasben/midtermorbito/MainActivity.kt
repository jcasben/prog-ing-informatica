package com.jcasben.midtermorbito

import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.size
import com.jcasben.midtermorbito.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val POSITIONS =
        arrayOf(
            arrayOf(0, 1, 1, 1),
            arrayOf(0, 0, 1, 3),
            arrayOf(0, 2, 3, 3),
            arrayOf(2, 2, 2, 3)
        )

    enum class State {
        MOVE,
        DROP,
        ROTATE,
        WIN
    }

    private var state = State.DROP

    private lateinit var table: Array<Array<Int>>

    private lateinit var binding: ActivityMainBinding
    private lateinit var grid: GridLayout

    private var whiteTurn = true

    private var moveFromCell = Pair(-1, -1)
    private var adjacents = listOf<Pair<Int, Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        setContentView(binding.root)
        updateUI()
    }

    private fun initUI() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        grid = binding.table
        binding.btnRotate.setOnClickListener { rotate() }
        binding.btnNewGame.setOnClickListener { newGame() }
        table = Array(4) { Array(4) { 0 } }
        copy2DArray(POSITIONS, table)

        for (i in table.indices) {
            for (j in table[i].indices) {
                grid.addView(ImageView(this).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        rowSpec = GridLayout.spec(i, 1f)
                        columnSpec = GridLayout.spec(j, 1f)
                        height = 0
                        width = 0
                    }
                    setImageResource(getResource(table[i][j]))
                    setOnClickListener { onClickCell(i, j) }
                })
            }
        }
    }

    private fun updateUI() {
        binding.playerAtTurn.text = if (whiteTurn) "Player WHITE" else "Player BLACK"
        binding.gameAction.text = when (state) {
            State.MOVE -> "Move" + if (whiteTurn) " BLACK" else " WHITE"
            State.DROP -> "Drop" + if (whiteTurn) " WHITE" else " BLACK"
            State.ROTATE -> "Rotate"
            State.WIN -> {
                "WINNER: " + if (!whiteTurn) "WHITE" else "BLACK"
            }
        }
        binding.btnRotate.isEnabled = state == State.ROTATE

        for (i in table.indices) {
            for (j in table[i].indices) {
                val cell = grid.getChildAt(i * 4 + j) as ImageView
                cell.setImageResource(getResource(table[i][j]))
            }
        }
    }

    private fun rotate() {
        whiteTurn = !whiteTurn
        state = State.MOVE

//        val newTable = arrayOf(
//            arrayOf(0, 0, 0, 0),
//            arrayOf(0, 0, 0, 0),
//            arrayOf(0, 0, 0, 0),
//            arrayOf(0, 0, 0, 0)
//        )

        val newTable = Array(4) { Array(4) { 0 } }
        copy2DArray(POSITIONS, newTable)

        for (i in table.indices) {
            for (j in table.indices) {
                if (table[i][j] == 4 || table[i][j] == 5) {
                    if (POSITIONS[i][j] == 0) newTable[i + 1][j] = table[i][j]
                    else if (POSITIONS[i][j] == 1) newTable[i][j - 1] = table[i][j]
                    else if (POSITIONS[i][j] == 2) newTable[i][j + 1] = table[i][j]
                    else if (POSITIONS[i][j] == 3) newTable[i - 1][j] = table[i][j]
                } else {
                    if (newTable[i][j] != 4 && newTable[i][j] != 5) newTable[i][j] = table[i][j]
                }
            }
        }

        table = newTable
        if (checkWin()) state = State.WIN
        updateUI()
    }

    private fun onClickCell(i: Int, j: Int) {
        when (state) {
            State.MOVE -> {
                if (moveFromCell.first == -1 && moveFromCell.second == -1) {
                    if (whiteTurn && table[i][j] == 5 || !whiteTurn && table[i][j] == 4) {
                        adjacents = getAdjacentCells(i, j, 4, 4)
                        moveFromCell = Pair(i, j)
                        for (adjacent in adjacents) {
                            val cell = grid.getChildAt(adjacent.first * 4 + adjacent.second) as ImageView
                            when (table[adjacent.first][adjacent.second]) {
                                0 -> cell.setImageResource(R.drawable.highlight_down)
                                1 -> cell.setImageResource(R.drawable.highlight_left)
                                2 -> cell.setImageResource(R.drawable.highlight_right)
                                3 -> cell.setImageResource(R.drawable.highlight_up)
                            }
                        }
                    }
                } else {
                    if (Pair(i, j) in adjacents) {
                        table[i][j] = table[moveFromCell.first][moveFromCell.second]
                        table[moveFromCell.first][moveFromCell.second] = POSITIONS[moveFromCell.first][moveFromCell.second]
                        moveFromCell = Pair(-1, -1)
                        state = State.DROP
                    } else {
                        for (adjacent in adjacents) {
                            val cell = grid.getChildAt(adjacent.first * 4 + adjacent.second) as ImageView
                            when (table[adjacent.first][adjacent.second]) {
                                0 -> cell.setImageResource(R.drawable.down)
                                1 -> cell.setImageResource(R.drawable.left)
                                2 -> cell.setImageResource(R.drawable.right)
                                3 -> cell.setImageResource(R.drawable.up)
                            }
                        }
                    }
                }
            }
            State.DROP -> {
                if (table[i][j] != 4 || table[i][j] != 5) {
                    val cell = grid.getChildAt(i * 4 + j) as ImageView
                    if (whiteTurn) {
                        table[i][j] = 4
                        //cell.setImageResource(R.drawable.white)
                        state = State.ROTATE
                    } else {
                        table[i][j] = 5
                        //cell.setImageResource(R.drawable.black)
                        state = State.ROTATE
                    }
                }
            }
            else -> {}
        }
        updateUI()
    }

    private fun getResource(value: Int): Int {
        return when (value) {
            0 -> R.drawable.down
            1 -> R.drawable.left
            2 -> R.drawable.right
            3 -> R.drawable.up
            4 -> R.drawable.white
            5 -> R.drawable.black
            else -> R.drawable.down
        }
    }

    private fun newGame() {
        table = POSITIONS
        whiteTurn = true
        state = State.DROP
        updateUI()
    }

    fun copy2DArray(source: Array<Array<Int>>, destination: Array<Array<Int>>) {
        for (i in source.indices) {
            for (j in source[i].indices) {
                destination[i][j] = source[i][j]
            }
        }
    }

    private fun getAdjacentCells(i: Int, j: Int, rows: Int, cols: Int): List<Pair<Int, Int>> {
        val adjacentCells = mutableListOf<Pair<Int, Int>>()

        if (i > 0 && (table[i - 1][j] != 4 && table[i - 1][j] != 5)) adjacentCells.add(Pair(i - 1, j)) // Up
        if (i < rows - 1 && (table[i + 1][j] != 4 && table[i + 1][j] != 5)) adjacentCells.add(Pair(i + 1, j)) // Down
        if (j > 0 && (table[i][j - 1] != 4 && table[i][j - 1] != 5)) adjacentCells.add(Pair(i, j - 1)) // Left
        if (j < cols - 1 && (table[i][j + 1] != 4 && table[i][j + 1] != 5)) adjacentCells.add(Pair(i, j + 1))

        return adjacentCells
    }

    private fun checkWin(): Boolean {
        // Check rows
        for (i in table.indices) {
            if (table[i][0] != 0 && table[i].all { it == table[i][0] }) {
                return true
            }
        }

        // Check columns
        for (j in table[0].indices) {
            if (table[0][j] != 0 && table.all { it[j] == table[0][j] }) {
                return true
            }
        }

        // Check diagonals
        if (table[0][0] != 0 && (0 until 4).all { table[it][it] == table[0][0] }) {
            return true
        }
        if (table[0][3] != 0 && (0 until 4).all { table[it][3 - it] == table[0][3] }) {
            return true
        }

        return false
    }
}