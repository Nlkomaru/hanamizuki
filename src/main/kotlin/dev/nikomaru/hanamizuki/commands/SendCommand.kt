package dev.nikomaru.hanamizuki.commands

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.annotation.Subcommand

@Command("hanamizuki")
class SendCommand {
    @Subcommand("send")
    @Description("Send a message to a player")
    fun send(sender: CommandSender,target: Player, message: String) {
        println("send")
    }
}