package solutionsForBookCrackingTheCodingInterview

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
     * It will work as InsertAlgorithm with complexity as n^2
     */

    fun checkStringForUniqueness(stringToCheck: String): Boolean {
        val textSize = stringToCheck.length
        println("checkStringForUniqueness stringToCheck  : " + stringToCheck)
        println("checkStringForUniqueness stringToCheck length : " + stringToCheck.length)
        for (i in 0 until textSize) {
            for (j in (i + 1) until textSize) {
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
        var value: Int
        for (i in 0 until str.length) {
            value = str[i].toInt()
            println("character : " + str[i] + " value : " + value)
            if (charSet[value]) {
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

    //will return error because 'ś' character have is 347 (our ASCII set is only 256)
    //stringCheckForAllUniqueCharacters.fastMethodFromTheBook("ś")
}