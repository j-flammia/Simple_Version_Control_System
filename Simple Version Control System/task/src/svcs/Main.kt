package svcs

fun main(args: Array<String>) {
    try {
        when(args[0]) {
            "--help" -> printMan()
            "config" -> config()
            "add" -> add()
            "log" -> log()
            "commit" -> commit()
            "checkout" -> checkout()
            else -> wrongCommand(args[0])
        }
    } catch (e: Exception) { printMan() }
}

fun printMan() {
    println("These are SVCS commands:\n" +
            "config     Get and set a username.\n" +
            "add        Add a file to the index.\n" +
            "log        Show commit logs.\n" +
            "commit     Save changes.\n" +
            "checkout   Restore a file.")
}

fun config() {
    println("Get and set a username.")
}

fun add() {
    println("Add a file to the index.")
}

fun log() {
    println("Show commit logs.")
}

fun commit() {
    println("Save changes.")
}

fun checkout() {
    println("Restore a file.")
}

fun wrongCommand(input: String) {
    println("'$input' is not a SVCS command.")
}
