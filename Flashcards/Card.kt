import java.util.Scanner

class Cards {
    val list = mutableMapOf<String, String>()

    fun addList(term: String, def: String) {
        if (!list.containsKey(term)) {
            list[term] = def
        }
    }

    fun insertCard(cnt: Int, scan: Scanner) {
        println("The card #$cnt:")
        var term = scan.nextLine()
        while (checkTerm(term)) {
            println("The card \"$term\" already exists. Try again:")
            term = scan.nextLine()
        }
        println("The definition of the card #$cnt:")
        var def = scan.nextLine()
        while (checkDef(def)) {
            println("The definition \"$def\" already exists. Try again:")
            def = scan.nextLine()
        }
        this.addList(term, def)
    }
    fun checkTerm(term: String): Boolean {
        if (list.containsKey(term)) {
            return true
        }
        return false
    }
    fun checkDef(def: String): Boolean {
        if (list.containsValue(def)) {
            return true
        }
        return false
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