package solutionsForBookCrackingTheCodingInterview.stacksAndQueues

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 4/27/2018
 * "Cracking the Coding Interview" task 3.6
 */
object StackTools {
    fun <T : Comparable<in T>> ascendingSort(stackToSort: ArrayStack<T>): ArrayStack<T> {
        val resultStack = ArrayStack<T>(stackToSort.size)
        while (!stackToSort.isEmpty()) {
            if (resultStack.isEmpty() || stackToSort.peek() >= resultStack.peek()) {
                resultStack.push(stackToSort.pop())
                continue

            } else {
                val tempElement = stackToSort.pop()
                while (!resultStack.isEmpty() && tempElement < resultStack.peek()) {
                    stackToSort.push(resultStack.pop())

                }
                resultStack.push(tempElement)
            }
        }
        return resultStack

    }
}

