package cooldomainname.com.thebesttexteditorever

import android.support.annotation.NonNull
import org.jetbrains.annotations.Nullable
import java.io.File

class BetterFile : File {

    constructor(pathname: String) : super(pathname)

    constructor(filesDir: File, s: String) : super(filesDir, s)

    @NonNull
    fun deleteIfExists(): BetterFile {
        if (this.exists()) {
            this.delete()
        }
        return this
    }

    @NonNull
    fun createIfNotExists(): BetterFile {
        if (!this.exists()) {
            this.parentFile.mkdirs()
            this.createNewFile()
        }
        return this
    }

    @Nullable
    fun extension(): String? {
        return this.extension
    }

    /***
     * Returns a new file with an extension.
     */
    fun setExtension(extension: String): BetterFile {
        return BetterFile(this.absoluteFile.toString() + extension)
    }
}
