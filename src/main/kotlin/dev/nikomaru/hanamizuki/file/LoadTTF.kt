package dev.nikomaru.hanamizuki.file

import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoadTTF : KoinComponent {
    val plugin : JavaPlugin by inject()

    fun loadTTF(){
        plugin.logger.info("Loading TTF...")

    }
}