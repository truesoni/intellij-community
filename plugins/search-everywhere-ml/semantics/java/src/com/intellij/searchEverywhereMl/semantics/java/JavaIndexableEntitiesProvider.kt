package com.intellij.searchEverywhereMl.semantics.java

import com.intellij.platform.ml.embeddings.search.indices.EntityId
import com.intellij.platform.ml.embeddings.search.indices.FileIndexableEntitiesProvider
import com.intellij.platform.ml.embeddings.search.services.IndexableClass
import com.intellij.platform.ml.embeddings.search.services.IndexableSymbol
import com.intellij.psi.PsiAnonymousClass
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiJavaFile

class JavaIndexableEntitiesProvider : FileIndexableEntitiesProvider {
  override fun isEnabled(file: PsiFile) = file is PsiJavaFile

  override fun extractIndexableSymbols(file: PsiFile): List<IndexableSymbol> {
    return (file as PsiJavaFile).classes.filterNotNull()
      .flatMap { it.methods.toList() }
      .filter { it.name != ANONYMOUS_ID }
      .map { IndexableSymbol(EntityId(it.name.intern())) }
  }

  override fun extractIndexableClasses(file: PsiFile): List<IndexableClass> {
    return (file as PsiJavaFile).classes.filterNotNull()
      .filter { it !is PsiAnonymousClass }
      .map { IndexableClass(EntityId(it.name?.intern() ?: "")) }
  }

  companion object {
    private const val ANONYMOUS_ID = "<anonymous>"
  }
}