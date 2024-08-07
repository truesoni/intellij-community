package com.jetbrains.performanceScripts.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.performanceScripts.lang.IJPerfLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class IJPerfElementType extends IElementType {

  public IJPerfElementType(@NotNull @NonNls String debugName) {
    super(debugName, IJPerfLanguage.INSTANCE);
  }
}
