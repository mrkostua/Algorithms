package solutionsForBookCrackingTheCodingInterview
/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 2/27/2018
 * "Cracking the Coding Interview" task 1.3
 *
 * Design an algorithm and write code to remove the duplicate characters in a string
 * without using any additional buffer. NOTE: One or two additional variables are fine.
 * An extra copy of the array is not.
 */

/**
 * With additional array
 * @param textToWorkWith only extend ASCII characters (256)
 */
fun removeDuplicateCharacters(textToWorkWith: String): String {
    if (textToWorkWith.length < 2)
        return textToWorkWith

    var resultText = ""
    val charSet = BooleanArray(256)
    var characterIntegerValue: Int
    for (character in 0 until textToWorkWith.length) {
        characterIntegerValue = textToWorkWith[character].toInt()
        if (!charSet[characterIntegerValue]) {
            charSet[characterIntegerValue] = true
            resultText += textToWorkWith[character]

        }

    }
    return resultText
}

//todo create TestCases and write method without additional memory as BooleanArray
fun main(args: Array<String>) {
    val textToCheck = "hello losers :) :)"
    println("text to delete duplicate characters from : " + textToCheck)
    println("result text : " + removeDuplicateCharacters(textToCheck))

    val arrayToCheck: CharArray = CharArray(textToCheck.length)
    for (i in 0 until textToCheck.length) {
        arrayToCheck[i] = textToCheck[i]
    }


}