import java.util.Scanner

fun main() {
    val cards = Cards()
    val scan = Scanner(System.`in`)
    println("Input the number of the cards:")
    val cnt = scan.nextInt()
    scan.nextLine()
    for (i in 1..cnt) {
        println("The card #$i:")
        val term = scan.nextLine()
        println("The definition of the card #$i:")
        val def = scan.nextLine()
        cards.addList(term, def)
    }
    for (term in cards.list) {
        println("Print the definition of \"${term.key}\":")
        val answer = scan.nextLine()
        cards.checkAnswer(term.key, answer)
    }
}