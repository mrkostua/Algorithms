package solutionsForBookCrackingTheCodingInterview.arraysAndStrings

import java.util.*

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/1/2018
 * "Cracking the Coding Interview" task 1.5
 */

/**
 * method to replace all spaces in a string with ‘L’.
 */
fun replaceChosenSymbolInString(textToWorkWith: CharArray, symbolToReplace: Char, replacementSymbol: Char): CharArray {
    textToWorkWith
            .filter { it == symbolToReplace }
            .forEach { textToWorkWith[textToWorkWith.indexOf(it)] = replacementSymbol }
    return textToWorkWith
}

/**
 *
 * 1.5 Write a method to replace all spaces in a string with ‘%20’.
 * Replaces chosen symbol with any combination of symbols
 */
//TODO fix this method don't works properly
fun replaceChosenSymbolInString(arrayOfSymbolsToCheck: CharArray, symbolToReplace: Char, replacementSymbols: CharArray): CharArray {
    /**
     * The easiest way to create array of strings so the problem with moving indexes in CharArray and size.
     * 1. we need to now count of symbolsToReplace in Array so we can create result_array with correct size.
     */
    val countOfSymbolsToReplace = arrayOfSymbolsToCheck.count { it == symbolToReplace }
    if (countOfSymbolsToReplace <= 0) {
        return arrayOfSymbolsToCheck
    }
    val resultArraySize = (arrayOfSymbolsToCheck.size - countOfSymbolsToReplace) + countOfSymbolsToReplace * replacementSymbols.size
    val resultArray = CharArray(resultArraySize)
    var index = 0

    arrayOfSymbolsToCheck.forEach { symbol ->
        if (symbol == symbolToReplace) {
            for (i in 0 until replacementSymbols.size) {
                resultArray[index] = replacementSymbols[i]
                index++

            }

        } else {
            resultArray[index] = symbol
            index++

        }
    }

    return resultArray
}

fun main(args: Array<String>) {
    val text = charArrayOf('s', 'a', '!', ' ', '!', 'e', 'l', 'l', 'o', '!', '!', '!')
    println("Text to change : " + Arrays.toString(text) + "\n")
    println("method to change chosen symbol with one replacement symbol")
    println(Arrays.toString(replaceChosenSymbolInString(text, 'l', 'L')))
    println("\n\nSecond simulation \n\n")
    println("method to change chosen symbol with any amount of symbols")
    println(Arrays.toString(replaceChosenSymbolInString(text, '!', charArrayOf(':', ')'))))
}