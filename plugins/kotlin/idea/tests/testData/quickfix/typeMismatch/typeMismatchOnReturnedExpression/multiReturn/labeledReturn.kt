// "Change return type of enclosing function 'foo' to 'Any'" "true"
// WITH_STDLIB
fun foo(n: Int): Boolean {
    if (true) return "foo"<caret>
    n.let {
        return@foo 1
    }
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.ChangeCallableReturnTypeFix$ForEnclosing
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.ChangeTypeQuickFixFactories$UpdateTypeQuickFix