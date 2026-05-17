/*
 * Omar (omardotdev)'s Aliucord Plugins
 * Copyright (C) 2026 Omar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * http://www.apache.org/licenses/LICENSE-2.0
*/

package com.github.omardotdev

import android.content.Context
import com.aliucord.PluginManager.disablePlugin
import com.aliucord.PluginManager.plugins
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin

@AliucordPlugin
class DisablePiglinCyborgs : Plugin() {
    override fun start(context: Context) {
        val piglins = listOf(
            "ReviewDB",
            "HeicImageConverter",
            "PowerMode",
            "OnboardingFix",
            "AvatarResizer",
            "PasswordLogin",
            "FullscreenLock",
            "BetterUserDetails"
        )

        for (piglin in piglins) {
            if (plugins[piglin] == null) continue

            try {
                logger.info("⚠ potential ai assisted/vibe coded plugin detected, disabling")
                disablePlugin(piglin)
            } catch (e: Exception) {
                logger.info("failed to disable ai assisted plugin!", e)
            }
        }
    }

    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
