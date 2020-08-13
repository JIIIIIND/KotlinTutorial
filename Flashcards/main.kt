import java.util.Scanner

fun selectMenu() {
    var input = ""
    val scan = Scanner(System.`in`)
    val cards = Cards()
    val fileControl = FileControl()
    loop@ while (true) {
        println("Input the action (add, remove, import, export, ask, exit):")
        input = scan.nextLine()
        when (input) {
            "add" -> cards.insertCard(scan)
            "remove" -> cards.deleteCard(scan)
            "import" -> fileControl.import(cards, scan)
            "export" -> fileControl.export(cards, scan)
            "ask" -> cards.ask(scan)
            "exit" -> break@loop
            else -> println("command is not found.")
        }
    }
    println("Bye bye!")
}

fun main() {
    selectMenu()
}