package cooldomainname.com.the_best_text_editor_ever

import java.io.File

class BetterFile : File {

    constructor(pathname: String) : super(pathname)

    constructor(filesDir: File, s: String) : super(filesDir, s)

    fun deleteIfExists(): BetterFile {
        if (this.exists()) {
            this.delete()
        }
        return this
    }
}
