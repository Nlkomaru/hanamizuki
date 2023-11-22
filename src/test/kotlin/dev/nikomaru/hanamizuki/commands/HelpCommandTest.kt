package dev.nikomaru.hanamizuki.commands

import be.seeseemelk.mockbukkit.ServerMock
import dev.nikomaru.hanamizuki.Hanamizuki
import dev.nikomaru.hanamizuki.HanamizukiTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import org.koin.test.inject


@ExtendWith(HanamizukiTest::class)
class HelpCommandTest : KoinTest{
    val server : ServerMock by inject()
    val plugin : Hanamizuki by inject()

    @Test
    @DisplayName("コマンドテスト: hanamizuki help")
    fun sendHelp(){
        val player = server.addPlayer()
        assert(player.performCommand("hanamizuki help"))
    }
}