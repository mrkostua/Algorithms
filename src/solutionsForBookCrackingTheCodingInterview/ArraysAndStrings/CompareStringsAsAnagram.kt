package solutionsForBookCrackingTheCodingInterview.ArraysAndStrings

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 2/28/2018
 * "Cracking the Coding Interview" task 1.4 (no sense of writing code ease solution)
 */

//todo examine solution "Check if the two strings have identical counts for each unique char "  from the book
fun compareStringsForAnagram(firstText: String, secondText: String): Boolean {
    /**
     * first way is to have one list and with 2 for loops compare first symbol of first String to all symbols from
     * other string and if they match delete this symbol from second array and continue if no match found return false.
     */
    /**
     * second way is to just first sort all this symbols using some algorithm so complexity is n*log(n) and after just compare
     * symbol with symbol in order (we gonna get result faster some time in the begging)
     */
    if (firstText.length != secondText.length) {
        return false
    }
    return true
}