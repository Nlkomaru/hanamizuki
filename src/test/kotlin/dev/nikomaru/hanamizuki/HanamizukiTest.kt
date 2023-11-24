package dev.nikomaru.hanamizuki

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import dev.nikomaru.hanamizuki.commands.HelpCommand
import dev.nikomaru.hanamizuki.commands.SendCommand
import org.bukkit.Server
import org.junit.jupiter.api.extension.*
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import revxrsal.commands.bukkit.BukkitCommandHandler
import revxrsal.commands.ktx.supportSuspendFunctions

open class HanamizukiTest : BeforeEachCallback, AfterEachCallback {

    lateinit var server: ServerMock
    lateinit var plugin: Hanamizuki

    override fun beforeEach(context: ExtensionContext) {
        println("beforeEach() executed before " + context.displayName + ".");
        server = MockBukkit.mock()
        setupKoin()
    }

    override fun afterEach(context: ExtensionContext) {
        MockBukkit.unmock()
    }


    private fun setupKoin() {
        plugin = MockBukkit.load(Hanamizuki::class.java)
        val appModule = module {
            single<Hanamizuki> { plugin }
            single<ServerMock> { server }
        }
        loadKoinModules(appModule)
    }

}