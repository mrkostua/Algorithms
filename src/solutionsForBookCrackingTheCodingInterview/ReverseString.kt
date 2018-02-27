package solutionsForBookCrackingTheCodingInterview

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 2/27/2018
 */

fun reverseString(textToReverse: String) : String {
    val result = StringBuilder(textToReverse.length)
    for (character in textToReverse.length -1 downTo 0 ) {
        result.append(textToReverse[character])
    }
    return result.toString()

}

fun main(args: Array<String>) {
    val cStyleString = "string"
    println("initial data : string to reverse : " + cStyleString)
    println(" reversed string : " + reverseString(cStyleString))

}