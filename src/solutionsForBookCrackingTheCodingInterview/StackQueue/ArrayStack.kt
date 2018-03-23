package solutionsForBookCrackingTheCodingInterview.StackQueue

import java.util.*

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/23/2018
 */

open class ArrayStack<T>(initialCapacity: Int = 0) : Stack<T>() {
    private val DEFAULIT_CAPCITY = 20
    private var topPointer = -1 //empty stack
    private val arrayBuffer: Array<T?> = if (initialCapacity <= 0) {
        arrayOfNulls<Any>(DEFAULT_BUFFER_SIZE) as Array<T?>

    } else {
        arrayOfNulls<Any>(initialCapacity) as Array<T?>

    }

    override fun push(item: T): T {
        if (topPointer == arrayBuffer.size) {
            throw RuntimeException("push - stack is full")

        }
        topPointer++
        arrayBuffer[topPointer] = item
        return item

    }

    override fun pop(): T {
        val k = peek()
        arrayBuffer[topPointer] = null
        topPointer--
        return k
    }

    override fun peek(): T {
        if (isEmpty()) {
            throw RuntimeException("empty stack")

        }
        return arrayBuffer[topPointer]!!

    }

    override fun isEmpty() = topPointer == -1

}
