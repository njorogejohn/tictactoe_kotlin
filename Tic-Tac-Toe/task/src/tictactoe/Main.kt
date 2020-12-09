package tictactoe

import java.beans.beancontext.BeanContextServiceRevokedEvent
import java.util.*

fun main() {

    val scanner = Scanner(System.`in`)

    //print the empty grid
    var field = "_________"
    var moves = field.toCharArray()
    printTheBoard(moves)

    //make a move
    var allNumbers = true
    var validNumbers = true
    var occupiedCell = false
    var position = 0
    var result = "continue"

    while (result == "continue") {
        makeAMove(allNumbers, validNumbers, occupiedCell, scanner, position, field, moves)

        result = evaluateBoard(moves)
    }

    println(result)



}

private fun evaluateBoard(moves: CharArray): String {
    val xWins = moves[0] == 'X' && moves[1] == 'X' && moves[2] == 'X' ||
            moves[3] == 'X' && moves[4] == 'X' && moves[5] == 'X' ||
            moves[6] == 'X' && moves[7] == 'X' && moves[8] == 'X' ||
            moves[0] == 'X' && moves[3] == 'X' && moves[6] == 'X' ||
            moves[1] == 'X' && moves[4] == 'X' && moves[7] == 'X' ||
            moves[2] == 'X' && moves[5] == 'X' && moves[8] == 'X' ||
            moves[0] == 'X' && moves[4] == 'X' && moves[8] == 'X' ||
            moves[2] == 'X' && moves[4] == 'X' && moves[6] == 'X'

    val oWins = moves[0] == 'O' && moves[1] == 'O' && moves[2] == 'O' ||
            moves[3] == 'O' && moves[4] == 'O' && moves[5] == 'O' ||
            moves[6] == 'O' && moves[7] == 'O' && moves[8] == 'O' ||
            moves[0] == 'O' && moves[3] == 'O' && moves[6] == 'O' ||
            moves[1] == 'O' && moves[4] == 'O' && moves[7] == 'O' ||
            moves[2] == 'O' && moves[5] == 'O' && moves[8] == 'O' ||
            moves[0] == 'O' && moves[4] == 'O' && moves[8] == 'O' ||
            moves[2] == 'O' && moves[4] == 'O' && moves[6] == 'O'

    return if (!moves.contains('_') && !oWins && !xWins) {
        "Draw"
    } else if (xWins) {
        "X wins"
    } else if (oWins) {
        "O wins"
    } else {
        "continue"
    }
}

private fun makeAMove(allNumbers: Boolean, validNumbers: Boolean, occupiedCell: Boolean, scanner: Scanner, position: Int, field: String, moves: CharArray) {
    var allNumbers1 = allNumbers
    var validNumbers1 = validNumbers
    var occupiedCell1 = occupiedCell
    var position1 = position
    do {
        allNumbers1 = true
        validNumbers1 = true
        occupiedCell1 = false
        println("Enter the coordinates: ")
        val coordinates = scanner.nextLine().replace(" ", "").toCharArray()

        for (coordinate in coordinates) {
            if (coordinate.isLetter()) {
                allNumbers1 = false
                println("You should enter numbers")
                break
            }

            if (coordinate.toString().toInt() !in 1..3) {
                validNumbers1 = false
                println("Coordinates should be from 1 to 3!")
                break
            }
        }

        //confirm position selected by user
        if (allNumbers1 && validNumbers1) {
            when {
                coordinates[0] == '1' -> {
                    position1 = coordinates[1].toString().toInt()
                }
                coordinates[0] == '2' -> {
                    position1 = coordinates[0].toString().toInt() + coordinates[1].toString().toInt() + 1
                }
                coordinates[0] == '3' -> {
                    position1 = coordinates[0].toString().toInt() + coordinates[1].toString().toInt() + 3
                }
            }

            //check if the position is blank
            val movesBoard = field.toCharArray()
            occupiedCell1 = if (movesBoard[position1 - 1] != '_') {
                println("This cell is occupied! Choose another one!")
                true
            } else {
                false
            }
        }

    } while (!allNumbers1 || !validNumbers1 || occupiedCell1)

    val plays = moves.filter { it != '_' }.count()

    moves[position1 - 1] = when {
        plays == 0 -> 'X'
        plays % 2 == 0 -> 'X'
        else -> 'O'
    }

    printTheBoard(moves)
}

private fun printTheBoard(moves: CharArray) {
    var index = 0

    println("---------")
    for (i in 0 until 3) {
        print("| ")
        for (j in 0 until 3) {
            print(moves[index])
            print(" ")
            index++
        }
        print("|")
        println()
    }
    println("---------")
}