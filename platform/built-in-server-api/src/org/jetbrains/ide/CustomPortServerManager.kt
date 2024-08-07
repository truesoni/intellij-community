// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.ide

import com.intellij.openapi.extensions.ExtensionPointName
import org.jetbrains.annotations.ApiStatus

/**
 * See [Remote Communication](https://youtrack.jetbrains.com/articles/IDEA-A-63/Remote-Communication)
 */
abstract class CustomPortServerManager {
  @ApiStatus.Internal
  companion object {
    @ApiStatus.Internal
    @JvmField
    val EP_NAME: ExtensionPointName<CustomPortServerManager> = ExtensionPointName("org.jetbrains.customPortServerManager")
  }

  abstract val port: Int

  abstract val isAvailableExternally: Boolean

  abstract fun cannotBind(e: Exception, port: Int)

  interface CustomPortService {
    val isBound: Boolean

    fun rebind(): Boolean
  }

  abstract fun setManager(manager: CustomPortService?)
}