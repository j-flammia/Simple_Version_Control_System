fun generate(f: String): (Int) -> Int = when (f) { 
    "identity" -> { a: Int -> a }; "half" -> { a: Int -> a / 2 }; else -> { _: Int -> 0 } }
