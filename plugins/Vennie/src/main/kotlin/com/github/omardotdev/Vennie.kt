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
import android.net.Uri
import com.aliucord.Http
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.api.CommandsAPI.CommandResult
import com.aliucord.entities.CommandContext
import com.aliucord.entities.Plugin
import com.discord.api.commands.ApplicationCommandType
import java.io.File
import java.io.FileOutputStream
import com.aliucord.Utils
import com.vennie.vennieapi.ApiResponse

@AliucordPlugin
class Vennie : Plugin() {
    override fun start(context: Context) {
        commands.registerCommand(
            "vennie", "Send a random picture of Vennie :) (changes per day)", listOf(
                Utils.createCommandOption(ApplicationCommandType.STRING, "id", "Image ID (e.g 30)")
            )
        ) { ctx: CommandContext ->
            val json = Http.simpleJsonGet("https://votd.vennie.moe/api", ApiResponse::class.java)

            if (ctx.containsArg("id")) {
                val id = ctx.getStringOrDefault("id", "")
                ctx.addAttachment(Uri.fromFile(getImage(id, context)).toString(), "vennie-$id.jpg")
                CommandResult("")
            } else {
                ctx.addAttachment(Uri.fromFile(getImage(json.id, context)).toString(), "vennie.jpg")
                CommandResult("")
            }
        }
    }

    private fun getImage(id: String, context: Context): File {
        val res = Http.Request("https://votd.vennie.moe/image/$id").execute()
        val file = File.createTempFile("temp", ".jpg", context.cacheDir)
        FileOutputStream(file).use { fos -> res.pipe(fos) }
        file.deleteOnExit()
        return file
    }

    override fun stop(context: Context) {
        commands.unregisterAll()
    }
}
