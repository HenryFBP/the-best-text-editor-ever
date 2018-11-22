package cooldomainname.com.the_best_text_editor_ever

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.BufferedReader
import java.io.FileReader


class TextBufferTest : TestCase() {


    var folder = TemporaryFolder()

    override fun setUp() {
        folder.create()
    }

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

    /**
     * Make sure that we can save a file and read data back from it.
     */
    @Test
    fun testTextBufferSavingFile() {
        var textBuffer = TextBuffer("I got some data!", "As do I!")

        var file = folder.newFile("tempFile.txt")

        textBuffer.saveTo(file)

        var reader = BufferedReader(FileReader(file))

        Assert.assertEquals('I'.toInt(), reader.read())
        Assert.assertEquals(" got some data!", reader.readLine())
        Assert.assertEquals("As do I!", reader.readLine())

        reader.close()

        Assert.assertTrue(file.exists())
    }

    /**
     * Make sure that we can save a file and read data back from it,
     * even if it is saved with a wacky delimiter like " :) ".
     */
    fun testTextBufferSavingFileWackyDelims() {
        var textBuffer = TextBuffer("I like wacky delims.", "So do I!").setDelimiter(" :) ")

        var file = folder.newFile("tempFile.txt")

        textBuffer.saveTo(file)

        var reader = BufferedReader(FileReader(file))

        Assert.assertEquals("I like wacky delims. :) So do I!", reader.readLine())

        reader.close()

        Assert.assertTrue(file.exists())
    }

}