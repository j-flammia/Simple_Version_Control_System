fun main() {
    val list = List(readLine()!!.toInt()) { readLine()!!.toInt() }
    val nums = readLine()!!.split(" "); var output = "YES"
    for (e in list.indices) {
        if (e != list.size - 1) {
            if (list[e] == nums[0].toInt() && list[e + 1] == nums[1].toInt()) {
                output = "NO"
            } else if (list[e] == nums[1].toInt() && list[e + 1] == nums[0].toInt()) {
                output = "NO"
            }
        }
    }
    println(output)
}
