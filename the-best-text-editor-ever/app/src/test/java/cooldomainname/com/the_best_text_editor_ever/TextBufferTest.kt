package cooldomainname.com.the_best_text_editor_ever

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter


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
        Assert.assertEquals(String.format("x%ny"), TextBuffer("x", "y").toString())
    }

    @Test
    fun testTextBufferSimple() {
        Assert.assertEquals(String.format("hello!%n"), TextBuffer("hello!", "").toString())
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

    /**
     * Make sure that we can open a file and read back data from it.
     *
     * Tests a variety of delimiters.
     */
    fun testTextBufferReadingFile() {

        var funDelims = listOf<CharSequence>("x", "\n", "\r\n", "\r", "ASDF")

        for (i in 0 until funDelims.size) {

            var delim = funDelims[i]

            var file = folder.newFile("tempFile-${i}.txt")

            var writer = BufferedWriter(FileWriter(file))

            writer.write("Hello!\nI like edge cases!\nDon't you?\n\n...Well?\n".replace("\n", delim.toString()))

            writer.close()

            var textBuffer: TextBuffer = TextBuffer.fromFile(file, delim)

            assertEquals("Hello!", textBuffer.getLine(0))
            assertEquals("I like edge cases!", textBuffer.getLine(1))
            assertEquals("Don't you?", textBuffer.getLine(2))
            assertEquals("", textBuffer.getLine(3))
            assertEquals("...Well?", textBuffer.getLine(4))
            assertEquals("", textBuffer.getLine(5))

            try {
                textBuffer.getLine(6)
                throw AssertionError("We shouldn't be able to go this far.")
            } catch (e: IndexOutOfBoundsException) {
            }
        }
    }
}