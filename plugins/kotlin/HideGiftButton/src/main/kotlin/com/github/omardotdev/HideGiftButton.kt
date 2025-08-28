/*
 * Omar (omardotdev)'s Aliucord Plugins
 * Copyright (C) 2025 Omar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * http://www.apache.org/licenses/LICENSE-2.0
*/

package com.omardotdev.hidegiftbutton

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.*
import com.lytefast.flexinput.fragment.FlexInputFragment
import com.lytefast.flexinput.fragment.`FlexInputFragment$d`
import android.view.View

@AliucordPlugin
class HideGiftButton : Plugin() {
    override fun start(c: Context) {
	patcher.after<`FlexInputFragment$d`>("invoke", Any::class.java) {
		val fragment = this.receiver as FlexInputFragment
		val binding = fragment.j() ?: return@after
		binding.m.visibility = View.GONE
	}
    }

    override fun stop(context: Context) {
         patcher.unpatchAll()
    }
}
