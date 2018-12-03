package cooldomainname.com.the_best_text_editor_ever.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import cooldomainname.com.the_best_text_editor_ever.BetterFile
import cooldomainname.com.the_best_text_editor_ever.Library.*
import cooldomainname.com.the_best_text_editor_ever.R
import java.io.File
import java.io.FileOutputStream
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
                    val output = FileOutputStream(outputFile)
                    val input = URL(url).openStream() //TODO error trapping for 404, 403, no internet, etc.

                    textViewStatus.append("Opened URL $url.\n")

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
                    var intent = Intent()
                    intent.putExtra(BUNDLE_KEY_FILE_URI, outputFile.absolutePath);
                    setResult(RESULT_OK, intent)

                    Thread.sleep(2000) //FIXME is this really the best way to wait?

                    finish()
                }).start()


                hideKeyboard(this@ActivityDownloadFile)

                return@OnEditorActionListener true
            }

            false
        })
    }
}