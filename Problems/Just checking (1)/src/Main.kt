fun main() {
    val list = List(readLine()!!.toInt()) { readLine()!! }
    val nums = readLine()!!.split(" ")
    println(if (nums[0] in list && nums[1] in list)"YES" else "NO")
}
