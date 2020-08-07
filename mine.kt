package minesweeper
import java.util.*

enum class Status {
    NONE, MINE, NBR, ZERO, CHECK, CORRECT;
}
class Node(value: Int, status: Status) {
    var stat = status
    var data = value
}

class Map(cnt: Int) {
    var map = Array<Array<Node>>(9) {Array<Node>(9) { _ ->Node(0, Status.NONE)} }
    var mine = 0
    var flagCnt = 0
    var mineCnt = 0
    var isFirst = true

    init {
        mapInit(cnt)
        printMap(0)
    }

    private fun mapInit(cnt: Int) {
        val random = Random()
        for (i in 0..8) {
            for (j in 0..8) {
                map[i][j].data = 0
                map[i][j].stat = Status.NONE
            }
        }
        mine = 0
        while (mine < cnt) {
            for (i in 0..8) {
                for (j in 0..8) {
                    if (random.nextInt(10) == 0 && mine < cnt) {
                        if (map[i][j].data != -1) {
                            map[i][j].data = -1
                            map[i][j].stat = Status.MINE
                            mine++
                        }
                    }
                }
            }
        }
        calculate()
    }

    private fun calculate() {
        for (i in 0..8) {
            for (j in 0..8) {
                var cnt = 0
                if (map[i][j].data != -1) {
                    if (i - 1 >= 0) {
                        if (map[i - 1][j].data == -1) {cnt++}
                        if (j - 1 >= 0 && map[i - 1][j - 1].data == -1) {cnt++}
                        if (j + 1 < 9 && map[i - 1][j + 1].data == -1) {cnt++}
                    }
                    if (i + 1 < 9) {
                        if (map[i + 1][j].data == -1) {cnt++}
                        if (j - 1 >= 0 && map[i + 1][j - 1].data == -1) {cnt++}
                        if (j + 1 < 9 && map[i + 1][j + 1].data == -1) {cnt++}
                    }
                    if (j - 1 >= 0 && map[i][j - 1].data == -1) {cnt++}
                    if (j + 1 < 9 && map[i][j + 1].data == -1) {cnt++}
                    if (cnt != 0) { map[i][j].data = cnt }
                }
            }
        }
    }

    private fun printMap(type: Int) {
        println("\n |123456789|")
        println("—|—————————|")
        for (j in 0..8) {
            print(""+ (j + 1) + "|")
            for (i in 0..8) {
                if (type == 1) {
                    print(if (map[i][j].stat == Status.NONE) { '.' }
                    else if (map[i][j].stat == Status.MINE) { 'X' }
                    else if (map[i][j].stat == Status.CHECK || map[i][j].stat == Status.CORRECT) { '*' }
                    else if (map[i][j].stat == Status.ZERO) { '/' }
                    else { map[i][j].data} )
                }
                else {
                    print(if (map[i][j].stat == Status.NONE || map[i][j].stat == Status.MINE) { '.' }
                    else if (map[i][j].stat == Status.CHECK || map[i][j].stat == Status.CORRECT) { '*' }
                    else if (map[i][j].stat == Status.ZERO) { '/' }
                    else { map[i][j].data} )
                }
            }
            println("|")
        }
        println("—|—————————|")
    }

    private fun mineAction(x: Int, y: Int) {
        when (map[y][x].stat){
            Status.NONE -> {
                map[y][x].stat = Status.CHECK
                flagCnt++
                printMap(0)
            }
            Status.MINE -> {
                map[y][x].stat = Status.CORRECT
                mineCnt++
                printMap(0)
            }
            Status.CHECK -> {
                map[y][x].stat = Status.NONE
                flagCnt--
                printMap(0)
            }
            Status.CORRECT -> {
                map[y][x].stat = Status.MINE
                mineCnt--
                printMap(0)
            }
            else -> println("There is a number here!")
        }
    }

    private fun exploreTile(x: Int, y: Int) {
        when {
            map[y][x].data == 0 && (map[y][x].stat == Status.NONE || map[y][x].stat == Status.CHECK) -> {
                map[y][x].stat = Status.ZERO
                if (y - 1 >= 0 && x - 1 >= 0) { exploreTile(x - 1, y - 1) }
                if (y - 1 >= 0) { exploreTile(x, y - 1) }
                if (y - 1 >= 0 && x + 1 < 9) { exploreTile(x + 1, y - 1) }
                if (x - 1 >= 0) { exploreTile(x - 1, y) }
                if (x + 1 < 9) { exploreTile(x + 1, y) }
                if (x - 1 >= 0 && y + 1 < 9) { exploreTile(x - 1, y + 1) }
                if (y + 1 < 9) { exploreTile(x, y + 1) }
                if (y + 1 < 9 && x + 1 < 9) { exploreTile(x + 1, y + 1) }
            }
            map[y][x].data > 0 && (map[y][x].stat == Status.NONE || map[y][x].stat == Status.CHECK) -> {
                map[y][x].stat = Status.NBR
            }
        }
    }

    private fun mapCheck(): Int {
        var noneMap = 0
        for (y in 0..8) {
            for (x in 0..8) {
                when (map[y][x].stat) {
                    Status.NONE -> { noneMap++ }
                    Status.CHECK -> { noneMap++ }
                }
            }
        }
        return if (noneMap == 0)
            1
        else
            0
    }

    private fun freeAction(x: Int, y: Int): Int {
        when {
            map[y][x].stat == Status.MINE -> {
                if (isFirst) {
                    while (map[y][x].stat == Status.MINE) {
                        mapInit(mine)
                    }
                    freeAction(x, y)
                    return 0
                } else {
                    printMap(1)
                    println("You stepped on a mine and failed!")
                    return 1
                }
            }
            map[y][x].data == 0 && map[y][x].stat == Status.NONE -> {
                exploreTile(x, y)
            }
            map[y][x].data > 0 && map[y][x].stat == Status.NONE -> {
                map[y][x].stat = Status.NBR
            }
        }
        printMap(0)
        return 0
    }

    fun playGame(scan: Scanner) {

        loop@ while (mineCnt < mine || flagCnt != 0) {
            print("Set/delete mines marks (x and y coordinates): ")
            val y = scan.nextInt() - 1
            val x = scan.nextInt() - 1
            var action = scan.nextLine()
            action = action.substring(1, action.length)
            when (action) {
                "mine" -> mineAction(x, y)
                "free" -> {
                    if (freeAction(x, y) == 1) {
                        return
                    } else {
                        if (mapCheck() == 1)
                            break@loop
                    }
                }
                else -> println("command Error")
            }
            isFirst = false
        }
        println("Congratulations! You found all the mines!")
    }
}

fun main() {
    val scan = Scanner(System.`in`)
    print("How many mines do you want on the field? ")
    val mines = scan.nextInt()
    val map = Map(mines)
    map.playGame(scan)
}
