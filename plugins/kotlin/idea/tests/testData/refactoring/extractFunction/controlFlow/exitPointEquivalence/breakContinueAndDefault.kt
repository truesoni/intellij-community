// PARAM_TYPES: kotlin.Int
// PARAM_TYPES: kotlin.Int
// PARAM_DESCRIPTOR: value-parameter a: kotlin.Int defined in foo
// PARAM_DESCRIPTOR: val b: kotlin.Int defined in foo
// SIBLING:
fun foo(a: Int) {
    val b: Int = 1
    loop1@ for (p in 1..b) {
        loop2@ for (n in 1..b) {
            <selection>if (a > 0) throw Exception("")
            if (a + b > 0) break@loop2
            if (a - b > 0) continue@loop1</selection>
        }
    }
}

// IGNORE_K2