package solutionsForBookCrackingTheCodingInterview

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
    println("arrayToCheck size : " + arrayOfSymbolsToCheck.size)
    println("countOfSymbolsToReplace : " + countOfSymbolsToReplace)
    if (countOfSymbolsToReplace <= 0) {
        return arrayOfSymbolsToCheck
    }
    val resultArraySize = (arrayOfSymbolsToCheck.size - countOfSymbolsToReplace) + countOfSymbolsToReplace * replacementSymbols.size
    println("result array size  : " + resultArraySize)
    val resultArray = CharArray(resultArraySize)
    var innerLoopIndex = 0
    arrayOfSymbolsToCheck.forEachIndexed { index, symbol ->
        if (innerLoopIndex == 0 || index == innerLoopIndex) {
            println("inside of additional if statement")
            if (symbol == symbolToReplace) {
                for (i in 0 until replacementSymbols.size) {
                    println("inside second loop replacementSymbol = " + replacementSymbols[i])
                    resultArray[index + i] = replacementSymbols[i]
                    innerLoopIndex++
                }
            } else {
                resultArray[index] = symbol
                innerLoopIndex++
            }

        }
        println("mainLopIndex : " + index)
        println("innerLoopIndex : " + innerLoopIndex)

    }
    return resultArray
}

fun main(args: Array<String>) {
    val text = charArrayOf('s', 'a', 'y', ' ', 'h', 'e', 'l', 'l', 'o', '!', '!', '!')
    println("Text to change : " + Arrays.toString(text))
    println(Arrays.toString(replaceChosenSymbolInString(text, 'l', 'L')))
    println(Arrays.toString(replaceChosenSymbolInString(text, '!', charArrayOf(':', ')'))))
}