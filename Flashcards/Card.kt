class Cards {
    val list = mutableMapOf<String, String>()

    fun addList(term: String, def: String) {
        if (!list.containsKey(term)) {
            list[term] = def
        }
    }

    fun checkAnswer(key: String, def: String) {
        if (!list.isEmpty() && list[key].equals(def)) {
            println("Correct!")
        } else println("Wrong. The right answer is \"${list[key]}\".")
    }
}