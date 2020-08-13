import java.io.*

fun check_nbr(str: String): Boolean {
    for (c in str) {
        if (c < '0' || c > '9') {
            return false
        }
    }
    return true
}

fun main() {
    var file = File("src/words_with_numbers.txt")
    var cnt = 0
    var lines = file.readLines()
    for (line in lines) {
        if (check_nbr(line))
            cnt++
    }
    println(cnt)
    file = File("src/text.txt")
    lines = file.readLines()
    cnt = 0
    for (line in lines) {
        cnt += line.split(' ').size
    }
    println(cnt)
}