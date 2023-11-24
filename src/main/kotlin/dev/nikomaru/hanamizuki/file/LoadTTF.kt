package dev.nikomaru.hanamizuki.file

import dev.nikomaru.hanamizuki.Hanamizuki
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.awt.Font

object LoadTTF : KoinComponent {
    val plugin : Hanamizuki by inject()

    fun loadFont(){
        println("loading font")
        val default = loadDefaultFont()
        val emoji = loadEmojiFont()
        loadKoinModules(module {
            single<Font>(named("default")) { default }
            single<Font>(named("emoji")) { emoji }
        })
        println("loaded font")
    }

    fun loadDefaultFont(): Font {
        return getFont("DotGothic16.ttf")
    }

    fun loadEmojiFont(): Font {
        return getFont("NotoEmoji.ttf")
    }

    private fun getFont(string: String) : Font {
        return Font.createFont(Font.TRUETYPE_FONT, plugin.getResource(string)!!).deriveFont(Font.PLAIN, 24f)
    }
}