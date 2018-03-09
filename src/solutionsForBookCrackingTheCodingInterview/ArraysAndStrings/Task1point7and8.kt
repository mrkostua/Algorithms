package solutionsForBookCrackingTheCodingInterview.ArraysAndStrings

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/9/2018
 */

/**
 * Task 1.7
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column is set to 0.
 *
 * Solution form the book is ok!
 **/

/**
 * Task 1.8
 * Assume you have a method isSubstring which checks if one word is a substring of
another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using
only one call to isSubstring (i.e., “waterbottle” is a rotation of “erbottlewat”).
 **/

private fun isSubstring(wordToCheck: String, substringInWhichCheck: String): Boolean {
    TODO("implemented by the description of the task")
}

fun isOneStringRotationOfSecond(firstString: String, secondString: String): Boolean {
    //lets assume that by using method @isSubstring rotate word can't be a substring of a string where this word is not rotated
    //by rotating one word we can possible get another (different meaning)
    //in this case method isSubstring will return false if one is rotation of another
    TODO(" No logic for me in the solution of this task in the book.")
}
