// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.internal.statistic.collectors.fus.actions.persistence

import com.intellij.ide.actions.ToolWindowMoveAction.Anchor
import com.intellij.ide.actions.ToolWindowViewModeAction.ViewMode
import com.intellij.internal.statistic.collectors.fus.actions.persistence.ToolWindowCollector.ToolWindowUtilValidator
import com.intellij.internal.statistic.eventLog.EventLogGroup
import com.intellij.internal.statistic.eventLog.events.*
import com.intellij.internal.statistic.eventLog.events.EventFields.Enum
import com.intellij.internal.statistic.service.fus.collectors.CounterUsagesCollector
import com.intellij.toolWindow.ToolWindowEventSource

internal object ToolWindowEventLogGroup : CounterUsagesCollector() {
  private val GROUP = EventLogGroup("toolwindow", 63)

  override fun getGroup(): EventLogGroup = GROUP

  @JvmField
  val TOOLWINDOW_ID: StringEventField = EventFields.StringValidatedByCustomRule("id", ToolWindowUtilValidator::class.java)

  @JvmField
  val VIEW_MODE: EnumEventField<ViewMode> = Enum("ViewMode", ViewMode::class.java) { mode: ViewMode -> mode.name }
  @JvmField
  val LOCATION: EnumEventField<Anchor> = Enum("Location", Anchor::class.java) { location: Anchor -> location.name }
  @JvmField
  val SOURCE: EnumEventField<ToolWindowEventSource> = Enum("Source", ToolWindowEventSource::class.java)
  @JvmField
  val WEIGHT: FloatEventField = FloatEventField("weight")
  @JvmField
  val IS_MAXIMIZED: BooleanEventField = BooleanEventField("maximized")

  @JvmField
  val ACTIVATED: VarargEventId = registerToolwindowEvent("activated")
  @JvmField
  val SHOWN: VarargEventId = registerToolwindowEvent("shown")
  @JvmField
  val HIDDEN: VarargEventId = registerToolwindowEvent("hidden")
  @JvmField
  val RESIZED: VarargEventId = registerToolwindowEvent("resized")

  private fun registerToolwindowEvent(eventId: String): VarargEventId {
    return GROUP.registerVarargEvent(eventId, TOOLWINDOW_ID, EventFields.PluginInfo, VIEW_MODE, LOCATION, SOURCE, WEIGHT, IS_MAXIMIZED)
  }
}