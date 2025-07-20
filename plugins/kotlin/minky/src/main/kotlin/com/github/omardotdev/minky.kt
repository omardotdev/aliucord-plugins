/*
 * Omar (omardotdev)'s Aliucord Plugins
 * Copyright (C) 2025 Omar
 *
 * This plugin was taken from Wolfkid200444 and modfiied.
 * The original plugin's name is petoet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * http://www.apache.org/licenses/LICENSE-2.0
*/

package com.aliucord.plugins

import android.content.Context
import android.net.Uri
import com.aliucord.Http
import com.aliucord.Main
import com.aliucord.Utils.createCommandOption
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.api.CommandsAPI.CommandResult
import com.aliucord.entities.CommandContext
import com.aliucord.entities.Plugin
import com.discord.api.commands.ApplicationCommandType
import com.discord.utilities.icon.IconUtils
import java.io.File
import java.io.FileOutputStream

@AliucordPlugin
class Minky : Plugin() {
    override fun start(context: Context) {
        commands.registerCommand(
            "minky",
            "Send an image of Minky",
    	) 
	{ ctx: CommandContext ->
            var file: File? = null
            try {
                file = imageToDataUri(context)
            } catch (e: Throwable) {
                Main.logger.error(e)
            }
            assert(file != null)
            ctx.addAttachment(Uri.fromFile(file).toString(), "minky.jpg")
            CommandResult("")
        }
    }

    @Throws(Throwable::class)
    private fun imageToDataUri(mContext: Context): File {
        val res = Http.Request(url).execute()
        val f = File.createTempFile("temp", ".jpg", mContext.cacheDir)
        FileOutputStream(f).use { fos -> res.pipe(fos) }
        f.deleteOnExit()
        return f
    }

    override fun stop(context: Context) {
        commands.unregisterAll()
    }

    companion object {
        private const val url = "https://minky.materii.dev/"
    }
}
