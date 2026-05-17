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
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin

@AliucordPlugin
class DisablePiglinCyborgs : Plugin() {
    override fun start(context: Context) {}

    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
