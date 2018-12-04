package cooldomainname.com.thebesttexteditorever.syntaxhighlighting

import org.jetbrains.annotations.Nullable

class LanguageIdentifier(var name: String, var matchers: Set<Regex>) {

    companion object {

        val languageIdentifiers = HashSet<LanguageIdentifier>().apply {
            listOf(
                LanguageIdentifier(
                    "java",
                    HashSet<Regex>().apply {
                        addAll(
                            listOf(
                                Regex("\"\"\"((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+[\\\\${'$'}_\\w\\<\\>\\w\\s\\[\\]]*\\s+[\\\\${'$'}_\\w]+\\([^\\)]*\\)?\\s*\"\"\"")
                            )
                        )
                    }),
                LanguageIdentifier(
                    "kotlin",
                    HashSet<Regex>().apply {
                        addAll(
                            listOf(
                            )
                        )

                    })
            )
        }

        /***
         * Identifies a string as belonging to a language.
         *
         * Queries each line to see if it matches a regex that identifies it, and raises the 'score' of one language.
         *
         * Returns null if it could not identify it.
         * @param string The string of code.
         * @return An extension.
         */
        @Nullable
        fun identify(string: String): String? {

            var scoreMap = HashMap<LanguageIdentifier, Int>()

            for (languageIdentifier in languageIdentifiers) { //For all languages,

                for (matcher in languageIdentifier.matchers) //For an identifier of a language,
                {
                    if (matcher.containsMatchIn(string)) //If we have a match,
                    {
                        if (!scoreMap.containsKey(languageIdentifier)) { //If we've never seen it before,
                            scoreMap[languageIdentifier] = 0 //Set it to zero.
                        }

                        scoreMap[languageIdentifier] = scoreMap[languageIdentifier]!! + 1 //Seen it one more time now!
                    }
                }
            }

            // Highest-scoring language.
            val highest = scoreMap.maxBy { entry -> entry.value }

            if (highest != null) { //We've got a highest-detected language
                return highest.key.name //TODO again, instead of dumb extensions, make a Language object
            } else //No matches whatsoever
            {
                return null;
            }

        }

    }


}
