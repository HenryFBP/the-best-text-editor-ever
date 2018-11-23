package cooldomainname.com.the_best_text_editor_ever

import org.junit.Assert
import org.junit.Test

class TextBufferTest {

    @Test
    fun testTextBufferEmpty() {
        Assert.assertEquals("", TextBuffer().toString())
        Assert.assertEquals("x", TextBuffer("x").toString())
    }

    @Test
    fun testTextBufferEmptyLine() {
        Assert.assertEquals("x\ny", TextBuffer("x", "y").toString())
    }

    @Test
    fun testTextBufferSimple() {
        Assert.assertEquals("hello!\n", TextBuffer("hello!", "").toString())
    }

    @Test
    fun testTextBufferDelimiters() {
        Assert.assertEquals("1x2x30", TextBuffer("1", "2", "30").setDelimiter("x").toString())
    }

}