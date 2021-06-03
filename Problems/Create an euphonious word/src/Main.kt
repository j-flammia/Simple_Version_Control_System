fun main() {
    val input = readLine()!!; val vowelList = "aeiouy"
    var vowels = 0; var consonants = 0; var total = 0
        for (i in input) {
            if (i in vowelList) {
                vowels++
                consonants = 0
            } else {
                consonants++
                vowels = 0
            }
            if (vowels == 3 || consonants == 3) {
                total++
                vowels = 1
                consonants = 1
            }
        }
        println(total)
    }