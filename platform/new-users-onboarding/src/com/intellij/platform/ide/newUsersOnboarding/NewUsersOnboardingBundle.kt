// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.platform.ide.newUsersOnboarding

import com.intellij.DynamicBundle
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

@NonNls
private const val BUNDLE = "messages.NewUsersOnboardingBundle"

internal object NewUsersOnboardingBundle {
  private val bundle = DynamicBundle(NewUsersOnboardingBundle::class.java, BUNDLE)

  fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any): @Nls String {
    return bundle.getMessage(key, *params)
  }
}