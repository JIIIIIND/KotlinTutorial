import java.util.Scanner

fun main() {
    val cards = Cards()
    val scan = Scanner(System.`in`)
    println("Input the number of the cards:")
    val cnt = scan.nextInt()
    scan.nextLine()
    for (i in 1..cnt) {
        cards.insertCard(i, scan)
    }
    for (term in cards.list) {
        println("Print the definition of \"${term.key}\":")
        val answer = scan.nextLine()
        cards.checkAnswer(term.key, answer)
    }
}