import java.util.Scanner

class Cards {
    val list = mutableMapOf<String, String>()

    fun addList(term: String, def: String) {
        if (!list.containsKey(term)) {
            list[term] = def
        }
    }

    fun ask(scan: Scanner) {
        println("How many times to ask?")
        val cnt = scan.nextLine().toInt()
        for (i in 1..cnt) {
            val term = list.keys.random()
            println("Print the definition of \"${term}\":")
            val answer = scan.nextLine()
            checkAnswer(term, answer)
        }
    }

    fun insertCard(scan: Scanner) {
        println("The card:")
        var term = scan.nextLine()
        if (list.containsKey(term)) {
            println("The card \"$term\" already exists.\n")
            return
        }
        println("The definition of the card:")
        var def = scan.nextLine()
        if (list.containsValue(def)) {
            println("The definition \"$def\" already exists.\n")
            return
        }
        this.addList(term, def)
        println("The pair (\"$term\":\"$def\") has been added.\n")
    }

    fun deleteCard(scan: Scanner) {
        println("Which card?")
        val input = scan.nextLine()
        if (list.containsKey(input)) {
            println("The card has been removed\n")
            list.remove(input)
        } else println("Can't remove \"$input\": there is no such card\n")
    }

    fun checkAnswer(key: String, def: String) {
        if (!list.isEmpty() && list[key].equals(def)) {
            println("Correct!")
        } else if (list.containsValue(def)) {
            var find = ""
            for (key in list) {
                if (key.value.equals(def)) {
                    find = key.key
                }
            }
            println("Wrong. The right answer is \"${list[key]}\", but your definition is correct for \"$find\"")
        } else println("Wrong. The right answer is \"${list[key]}\".")
    }
}