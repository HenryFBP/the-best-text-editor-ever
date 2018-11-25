package cooldomainname.com.the_best_text_editor_ever

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

    @Nullable
    fun extension(): String? {
        return this.extension
    }
}
