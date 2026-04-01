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
import android.content.Intent
import android.net.Uri
import com.aliucord.Utils
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.fragments.InputDialog
import com.aliucord.patcher.*

@AliucordPlugin
class AliucordPremium : Plugin() {
    override fun start(context: Context) {
        patcher.after<com.discord.app.AppActivity>("onResume") {
            val desc = "Get ✨ Aliucord Premium ✨ now with the special features including:\n✨ Onboarding ✨ \n✨ Server Applications ✨ \n✨ Memory-safe Aliucord ✨\n✨ PluginDownloader working at all times ✨\n ✨ 99% chance of Aliucord not crashing at all ✨\n✨ Exclusive Access to secret Aliucord chat ✨\n Tap the button below to subscribe!!\n Below text field is for any feedback."

            val dialog = InputDialog()
                .setTitle("Get aliucord premium now!")
                .setDescription(desc)

            dialog.setOnOkListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
                Utils.appActivity.startActivity(browserIntent)

                dialog.dismiss()
            }

            dialog.show(Utils.appActivity.supportFragmentManager, "Get aliucord premium now!")
        }
    }

    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
