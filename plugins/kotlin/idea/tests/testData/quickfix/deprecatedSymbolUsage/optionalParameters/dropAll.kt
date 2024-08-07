// "Replace with 'newFun(p1, p2)'" "true"

interface I {
    @Deprecated("", ReplaceWith("newFun(p1, p2)"))
    fun oldFun(p1: String = "", p2: Int = 0)

    fun newFun(p1: String = "", p2: Int = 0, p3: Int = -1)
}

fun foo(i: I) {
    i.<caret>oldFun()
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix