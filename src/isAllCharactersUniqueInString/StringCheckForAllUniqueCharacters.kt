package isAllCharactersUniqueInString

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 2/26/2018
 * "Cracking the Coding Interview" task 1.1
 */
class StringCheckForAllUniqueCharacters {
    /**
     * Implement an algorithm to determine if a string has all unique characters. What if you
     *  can not use additional data structures?
     */

    /**
     * 1 Answer : using Java collections Set we can simply add character by character to Set and no duplicate character will be added to Set, or (HashMap)
     */

    /**
     * 2 Answer (can not use additional data structures)
     * We can use 2 loops to check every character against all others in string.
     * It will work as InsertAlgorithm with complexity as n^2@author TODO to check it.
     */

    fun checkStringForUniqueness(stringToCheck: String): Boolean {
        val stringSize = stringToCheck.length
        println("checkStringForUniqueness stringToCheck  : " + stringToCheck)
        println("checkStringForUniqueness stringToCheck length : " + stringToCheck.length)
        for (i in 0 until stringSize) {
            for (j in (i + 1) until stringSize) {
                println("characters to compare (1) : " + stringToCheck[i] + " (2) : " + stringToCheck[j])
                if (stringToCheck[i] == stringToCheck[j]) {
                    return false
                }
            }
        }
        return true
    }

    fun fastMethodFromTheBook(str: String): Boolean {
        val charSet = BooleanArray(256)
        for (i in 0 until str.length) {
            val value = str[i].toInt()
            println("character : " + str[i])
            if (charSet[value]){
                return false
            }
            charSet[value] = true
        }
        return true
    }

}


fun main(args: Array<String>) {
    val stringCheckForAllUniqueCharacters = StringCheckForAllUniqueCharacters()
    if (stringCheckForAllUniqueCharacters.checkStringForUniqueness("qwertyuiope")) {
        println("this String don't contain duplicate characters")
    } else {
        println("this String contains duplicate characters")

    }

    stringCheckForAllUniqueCharacters.fastMethodFromTheBook("qwertyuiope")
}