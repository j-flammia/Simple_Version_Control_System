fun main(args: Array<String>) {
    if (args.size == 3) {
        for (i in 1..3) {
            println("Argument $i: ${args[i - 1]}. It has ${args[i - 1].length} characters")
        }
    } else println("Invalid number of program arguments")
}