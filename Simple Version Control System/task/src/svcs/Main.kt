package svcs

import java.io.File

fun main(args: Array<String>) {
    val command = if (args.size > 1) args[1] else ""
    val config = File("C:\\Users\\jorda\\src\\config.txt")
    val index = File("C:\\Users\\jorda\\src\\index.txt")
    try {
        when(args[0]) {
            "--help" -> printMan()
            "config" -> config(config, command)
            "add" -> add(index, command)
            "log" -> log()
            "commit" -> commit()
            "checkout" -> checkout()
            else -> wrongCommand(args.joinToString { " " })
        }
    } catch (e: ) { printMan() }
}

fun printMan() {
    println("These are SVCS commands:\n" +
            "config     Get and set a username.\n" +
            "add        Add a file to the index.\n" +
            "log        Show commit logs.\n" +
            "commit     Save changes.\n" +
            "checkout   Restore a file.")
}

fun config(config: File, command: String) {
    val text = config.readText()
    if (command.isEmpty()) {
        if (text.isEmpty()) {
            println("Please, tell me who you are.")
        } else println("The username is $text")
    } else config.writeText(command)
}

fun add(index: File, command: String){
    if (command.isEmpty()) {
        if (index.readText().isEmpty()) println("Add a file to the index.")
        else println("Tracked Files: \n ${index.readText()}")
    } else
        try {
            val file = File(command)
            index.appendText("\n$file")
        } catch (e: NoSuchFileException) {println("Can't find \'${command[1]}\'.")}
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
