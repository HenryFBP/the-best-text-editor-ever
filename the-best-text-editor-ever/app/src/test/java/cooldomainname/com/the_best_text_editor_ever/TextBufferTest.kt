package cooldomainname.com.the_best_text_editor_ever

import org.junit.Assert
import org.junit.Test

class TextBufferTest {

    @Test
    fun testTextBufferEmpty() {

        var textBuffer = TextBuffer()

        Assert.assertEquals("", textBuffer.toString())
    }

    @Test
    fun testTextBufferEmptyLine() {
        var textBuffer = TextBuffer()

        textBuffer.add("", "")

        Assert.assertEquals("\n", textBuffer.toString())
    }

    @Test
    fun testTextBufferSimple() {

        var textBuffer = TextBuffer()

        textBuffer.add("hello!")
        textBuffer.add("")

        Assert.assertEquals("hello!\n", textBuffer.toString())
    }

    @Test
    fun testTextBufferDelimiters() {
        var textBuffer = TextBuffer("1", "2", "30")

        textBuffer.delimiter = "x"

        Assert.assertEquals("1x2x30", textBuffer.toString())
    }

}