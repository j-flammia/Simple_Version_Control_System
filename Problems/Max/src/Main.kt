fun fizzbuzz(from: Int, to: Int, transformation: (Int) -> String) {
    for (number in from..to) {
        println(transformation(number))
    }
}

fun main() {
    fizzbuzz(1, 100) { number ->
        if (number % 15 == 0) {
            "fizzbuzz"
        }
        if (number % 3 == 0) {
            "fizz"
        }
        if (number % 5 == 0) {
            "buzz"
        }
        number.toString()
    }
}