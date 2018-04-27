package stacksQueues

import org.junit.Assert.*
import org.junit.Test
import solutionsForBookCrackingTheCodingInterview.stacksAndQueues.ArrayStack
import solutionsForBookCrackingTheCodingInterview.stacksAndQueues.StackTools

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 4/27/2018
 */
class StackToolsTest {
    @Test
    fun ascendingSortTest() {
        //Given
        val initialStack = ArrayStack<Int>()
        val expecting = IntArray(11)
        for (i in 0..10) {
            initialStack.push(i)
            expecting[i] = i
        }
        expecting.sort()

        //When
        val actual = StackTools.ascendingSort(initialStack).getAllElements().toIntArray()

        //Then
        assertArrayEquals("array wasn't sorted correctly", expecting, actual)
    }

}