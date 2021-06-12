package svcs

import java.io.File

fun main(args: Array<String>) {
    val commandMain = if (args.isNotEmpty()) args[0] else ""
    val command = if (args.size > 1) args[1] else ""
    val config = File("config.txt")
    val index = File("index.txt")
    try {
        when(commandMain) {
            "--help" -> printMan()
            "config" -> { println("\nreceived \"${commandMain}\""); config(config, command) }
            "add" -> { println("received \"${commandMain}\""); add(index, command) }
            "log" -> { println("received \"${commandMain}\""); log() }
            "commit" -> { println("received \"${commandMain}\""); commit() }
            "checkout" -> { println("received \"${commandMain}\""); checkout() }
            "" -> printMan()
            else -> wrongCommand(args.joinToString { " " })
        }
    } catch (e: Exception ) { println(": received \"${commandMain}\""); printMan() }
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
    try {
        val text = config.readText()
        println("text is: \"$text\"")
        if (command.isEmpty()) {
            print("empty command\n")
            if (text.isEmpty()) {
                print("empty text\n")
                println("Please, tell me who you are.")
            } else println("The username is $text")
        } else config.writeText(command)
    } catch (e: Exception) {println("some shit broke here...")}
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
