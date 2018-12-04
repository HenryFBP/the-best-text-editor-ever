package cooldomainname.com.thebesttexteditorever.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import cooldomainname.com.thebesttexteditorever.BetterFile
import cooldomainname.com.thebesttexteditorever.Library.*
import cooldomainname.com.thebesttexteditorever.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL

class ActivityDownloadFile : AppCompatActivity() {

    companion object {
        // Key for storing our uri once it's downloaded.
        const val BUNDLE_KEY_FILE_URI = "downloaded file"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_file)

        // Set up UI elements
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        val editTextFilename = findViewById<EditText>(R.id.editTextFilename)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textViewStatus = findViewById<TextView>(R.id.textViewStatus)

        // Focus the filename EditText
        editTextFilename.requestFocus()

        // Show the keyboard
        showKeyboard(this@ActivityDownloadFile)

        // When they do something with the keyboard,
        editTextFilename.setOnEditorActionListener(TextView.OnEditorActionListener { view, actionId, event ->
            //If it's 'done' or 'next',
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {

                val url = view.text.toString()
                val filesafeurl = fileSafeString(url)


                val tempDir = File(applicationContext.filesDir.toString(), "/temp")

                var outputFile = BetterFile(tempDir, filesafeurl).createIfNotExists()

                // If, for some reason, it's a directory, make it not be one.
                if (outputFile.isDirectory) {
                    outputFile.deleteIfExists()
                    outputFile = BetterFile(outputFile.absolutePath).createIfNotExists()
                }

                //TODO make progress bar work. Put incremental stuff inside of this thread.
                Thread(Runnable {

                    var input: InputStream? = null
                    val output = FileOutputStream(outputFile)

                    try {
                        input = URL(url).openStream() //TODO error trapping for 403, no internet, etc.


                    } catch (e: FileNotFoundException) { //If 404,
                        runOnUiThread {
                            toastLong("HTTP Error 404, not found.", applicationContext)
                        }
                        logException(e)
                        return@Runnable

                    } catch (e: MalformedURLException) { // Malformed URL
                        runOnUiThread {
                            toastLong(
                                "URL is malformed. Check your colons and slashes.",
                                applicationContext
                            )
                        }
                        logException(e)
                        return@Runnable

                    } catch (e: Exception) { // General exception
                        runOnUiThread {
                            toastLong(
                                """Other exception occurred:
                                |${e.localizedMessage}""".trimMargin(), applicationContext
                            )
                        }
                        logException(e)
                        return@Runnable
                    }

                    assert(input != null)

                    textViewStatus.append("Opened URL $url.\n")

                    //FIXME no progress
                    // Copy input to output.
                    val bytesCopied = input.use {
                        output.use {
                            input.copyTo(output)
                        }
                    }

                    // We're done!
                    progressBar.progress = 100

                    textViewStatus.append("$bytesCopied bytes copied.\n")

                    // Return back to the parent activity our downloaded file's URL.
                    val intent = Intent()
                    intent.putExtra(BUNDLE_KEY_FILE_URI, outputFile.absolutePath);
                    setResult(RESULT_OK, intent)

                    finish()

                }).start()


                hideKeyboard(this@ActivityDownloadFile)

                return@OnEditorActionListener true
            }

            false
        })
    }
}