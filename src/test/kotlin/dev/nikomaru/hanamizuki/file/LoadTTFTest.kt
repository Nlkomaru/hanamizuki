package dev.nikomaru.hanamizuki.file

import dev.nikomaru.hanamizuki.HanamizukiTest
import org.apache.commons.lang3.StringUtils
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.io.TempDir
import org.junit.rules.TemporaryFolder
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.inject
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


@ExtendWith(HanamizukiTest::class)
class LoadTTFTest : KoinTest {

    val emojiFont : Font by inject(named("emoji"))
    val defaultFont : Font by inject(named("default"))

    @Test
    fun loadTTF() {
        val font = LoadTTF.loadDefaultFont()
        assertEquals("DotGothic16 Regular", font.name)
    }

    @Test
    fun loadEmojiFont() {
        val font = LoadTTF.loadEmojiFont()
        assertEquals("Noto Color Emoji", font.name)
    }

    @TempDir
    lateinit var tempFolder: File


    @Test
    fun writeText() {
        val message = "こんにちは 😊"
        val font = emojiFont
        val font2 = defaultFont

        val size = 100
        val image = BufferedImage((size * 0.8 * message.codePointCount(0, message.length) + size * 0.4).toInt(), size, BufferedImage.TYPE_INT_ARGB)
        val graphics = image.createGraphics()
        graphics.font = font.deriveFont(Font.PLAIN, size * 0.8.toFloat())
        graphics.color = Color.WHITE
        graphics.fillRect(0, 0, image.width, image.height)
        graphics.color = Color.BLACK
        val (x, y) = drawStringCenter(graphics, message, image.width / 2, image.height / 2)
        graphics.drawString(message, x, y)
        val file = File("test", "test.png")

//        val file = tempFolder.resolve("test.png")
        file.parentFile.mkdirs()
        file.createNewFile()
        ImageIO.write(image, "png", file)

        val loadImage = ImageIO.read(file)

        assertEquals(loadImage.width, 600)
        assertEquals(loadImage.height, 100)

    }

    fun drawStringCenter(g: Graphics, text: String, x: Int, y: Int): Pair<Int, Int> {
        val fm = g.fontMetrics
        val rectText = fm.getStringBounds(text, g).getBounds()
        val xSize = x - rectText.width / 2
        val ySize = y - rectText.height / 2 + fm.maxAscent
        return Pair(xSize, ySize)
    }

}