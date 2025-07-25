/*
 * Omar (omardotdev)'s Aliucord Plugins
 * Copyright (C) 2025 Omar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * http://www.apache.org/licenses/LICENSE-2.0
*/

package com.minky.minky

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
import java.io.File
import java.io.FileOutputStream

@AliucordPlugin
class Minky : Plugin() {
    override fun start(c: Context) {
        commands.registerCommand("minky", "Send an image of Minky") 
	{ ctx: CommandContext ->
            var file = getImage(c)
            ctx.addAttachment(Uri.fromFile(file).toString(), "minky.jpg")
            CommandResult("")
        }
    }

    private fun getImage(c: Context): File {
	var url = "https://minky.materii.dev"
        val res = Http.Request(url).execute()
        val file = File.createTempFile("temp", ".jpg", c.cacheDir)
        FileOutputStream(file).use { fos -> res.pipe(fos) }
        file.deleteOnExit()
        return file
    }

    override fun stop(context: Context) {
        commands.unregisterAll()
    }
}
