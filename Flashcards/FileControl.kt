import java.io.*
import java.util.Scanner

class FileControl {
    fun import(cards: Cards, scan: Scanner) {
        println("File name:")
        val name = scan.nextLine()
        val file = File(name)
        if (file.exists()) {
            val list = file.readLines()
            for (line in list) {
                var (term, def) = line.split("\" \"")
                term = term.filter {it != '\"'}
                def = def.filter {it != '\"'}
                if (cards.list.containsKey(term)) {
                    cards.list.remove(term)
                }
                cards.addList(term, def)
            }
            println("${list.size} cards have been loaded.\n")
        } else println("File not found.\n")
    }

    fun export(cards: Cards, scan: Scanner) {
        println("File name:")
        val name = scan.nextLine()
        val file = File(name)
        file.writeText("")
        for (node in cards.list) {
            file.appendText("\"" + node.key + "\" " + "\"" + node.value + "\"" +"\n")
        }
        println("${cards.list.size} cards have been saved.\n")
    }
}