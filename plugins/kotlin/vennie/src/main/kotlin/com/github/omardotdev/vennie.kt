/*
 * Omar (omardotdev)'s Aliucord Plugins
 * Copyright (C) 2025 Omar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * http://www.apache.org/licenses/LICENSE-2.0
*/

package com.vennie.vennie

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
import com.aliucord.Utils
import com.vennie.vennieapi.ApiResponse

@AliucordPlugin
class Vennie : Plugin() {
    override fun start(c: Context) {        
        commands.registerCommand("vennie", "Send a random picture of Vennie :) (changes per day)", listOf(
            Utils.createCommandOption(ApplicationCommandType.STRING, "id", "Image ID (e.g 30)")
        )) { ctx: CommandContext ->
	    var api_url = "https://votd.vennie.moe/api"
	    var res = Http.simpleJsonGet(api_url, ApiResponse::class.java)
	    var api_id = res.id
            if (ctx.containsArg("id")) {
                var id = ctx.getStringOrDefault("id", "")
		var file = getImage(id, c)
		ctx.addAttachment(Uri.fromFile(file).toString(), "vennie-$id.jpg")
		CommandResult("")
            } else {
		var file = getImage(api_id, c)
		ctx.addAttachment(Uri.fromFile(file).toString(), "vennie.jpg")
		CommandResult("")
            }
        }
    }
   
    private fun getImage(id: String, mContext: Context): File {
	var url = "https://votd.vennie.moe/image/$id"
        val res = Http.Request(url).execute()
        val file = File.createTempFile("temp", ".jpg", mContext.cacheDir)
        FileOutputStream(file).use { fos -> res.pipe(fos) }
        file.deleteOnExit()
        return file
    }

    override fun stop(context: Context) {
        commands.unregisterAll()
    }
}
