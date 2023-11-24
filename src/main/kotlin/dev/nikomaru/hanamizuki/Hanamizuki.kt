package dev.nikomaru.hanamizuki

import dev.nikomaru.hanamizuki.commands.HelpCommand
import dev.nikomaru.hanamizuki.commands.SendCommand
import dev.nikomaru.hanamizuki.file.LoadTTF
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import revxrsal.commands.bukkit.BukkitCommandHandler
import revxrsal.commands.ktx.supportSuspendFunctions

open class Hanamizuki : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        setupKoin()
        LoadTTF.loadFont()
        setCommand()
    }

    private fun setupKoin() {
        val appModule = module {
            single<Hanamizuki> { this@Hanamizuki }
        }

        GlobalContext.getOrNull() ?:startKoin {
            modules(appModule)
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun setCommand() {
        val handler = BukkitCommandHandler.create(this)

        handler.setSwitchPrefix("--")
        handler.setFlagPrefix("--")
        handler.supportSuspendFunctions()

        handler.setHelpWriter { command, _ ->
            java.lang.String.format(
                """
                <color:yellow>コマンド: <color:gray>%s
                <color:yellow>使用方法: <color:gray>%s
                <color:yellow>説明: <color:gray>%s
                
                """.trimIndent(),
                command.path.toList(),
                command.usage,
                command.description,
            )
        }

        with(handler) {
            register(SendCommand())
            register(HelpCommand())
        }
    }
}