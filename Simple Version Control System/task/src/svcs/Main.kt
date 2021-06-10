package svcs

import java.io.File

fun main(args: Array<String>) {
    val command = args[0].split(" ")
    val config = File("config.txt"); val index = File("index.txt")
    try {
        when(command[0]) {
            "--help" -> printMan()
            "config" -> config(config, command)
            "add" -> add(index, command)
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

fun config(config: File, command: List<String>) {
    val text = config.readText()
    if (command[1].isEmpty()) {
        if (text.isEmpty()) {
            println("Please, tell me who you are.")
        } else println("The username is $text")
    } else config.writeText(command[1])
}

fun add(index: File, command: List<String>){
    if (command[1].isEmpty()) {
        if (index.readText().isEmpty()) println("Add a file to the index.")
        else println("Tracked Files: \n ${index.readText()}")
    } else
        try {
            val file = File(command[1])
            index.appendText("\n$file")
        } catch (e: NoSuchFileException) {println("Can't find '$f'.")}
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
