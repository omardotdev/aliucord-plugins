/*
 * Omar (omardotdev)'s Aliucord Plugins
 * Copyright (C) 2025 Omar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * http://www.apache.org/licenses/LICENSE-2.0
*/

package com.github.omardotdev

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.*
import android.view.View
import android.widget.FrameLayout
import com.aliucord.Utils
import com.discord.widgets.channels.list.WidgetChannelsList
import com.google.android.material.appbar.CollapsingToolbarLayout

@AliucordPlugin
class HideServerBanner : Plugin() {
    override fun start(c: Context) {
        // The code is a bit horror, but it works :|
        patcher.after<WidgetChannelsList>("onViewBound", View::class.java) { (_, view: View) ->
            // Generates view id for the FrameLayout
            val layoutId = View.generateViewId()

            // Gets the parent of the FrameLayout (which has the channel banner), applies the view id and removes the channel banner
            view.findViewById<CollapsingToolbarLayout>(Utils.getResId("collapsing_toolbar", "id"))
                .getChildAt(0).apply { id = layoutId }
            val container = view.findViewById<FrameLayout>(Utils.getResId("$layoutId", "id"))
            container.removeViewAt(0)

            // Remove the weird background
            container.getChildAt(0).apply { setBackgroundColor(0x00000000) }
        }
    }

    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
