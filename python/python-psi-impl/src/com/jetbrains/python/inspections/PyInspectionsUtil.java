// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.jetbrains.python.inspections;

import com.intellij.codeInsight.controlflow.ControlFlow;
import com.intellij.codeInsight.controlflow.ControlFlowUtil;
import com.intellij.codeInsight.controlflow.Instruction;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.jetbrains.python.codeInsight.controlflow.CallInstruction;
import com.jetbrains.python.codeInsight.controlflow.ControlFlowCache;
import com.jetbrains.python.codeInsight.controlflow.ScopeOwner;
import com.jetbrains.python.codeInsight.dataflow.scope.ScopeUtil;
import com.jetbrains.python.psi.types.TypeEvalContext;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public final class PyInspectionsUtil {
  @ApiStatus.Internal
  public static boolean hasAnyInterruptedControlFlowPaths(@NotNull PsiElement element, @NotNull TypeEvalContext context) {
    final ScopeOwner owner = ScopeUtil.getScopeOwner(element);
    if (owner != null) {
      final ControlFlow flow = ControlFlowCache.getControlFlow(owner);
      final Instruction[] instructions = flow.getInstructions();
      final int start = ControlFlowUtil.findInstructionNumberByElement(instructions, element);
      if (start >= 0) {
        final Ref<Boolean> resultRef = Ref.create(false);
        ControlFlowUtil.iteratePrev(start, instructions, instruction -> {
          if (CallInstruction.Companion.allPredWithoutNoReturn(instruction, context).isEmpty() && !isFirstInstruction(instruction)) {
            resultRef.set(true);
            return ControlFlowUtil.Operation.BREAK;
          }
          return ControlFlowUtil.Operation.NEXT;
        });
        return resultRef.get();
      }
    }
    return false;
  }

  @ApiStatus.Internal
  static boolean isFirstInstruction(Instruction instruction) {
    return instruction.num() == 0;
  }

  private PyInspectionsUtil() {
  }
}
