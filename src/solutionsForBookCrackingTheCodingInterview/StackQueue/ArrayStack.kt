package solutionsForBookCrackingTheCodingInterview.StackQueue

import java.util.*

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/23/2018
 */

open class ArrayStack<T>(initialCapacity: Int = 0) : Stack<T>() {
    public val defaultCapacity = 20
    public var topPointer = -1 //empty stack
    public val arrayBuffer: Array<T?> = if (initialCapacity <= 0) {
        arrayOfNulls<Any>(defaultCapacity) as Array<T?>

    } else {
        arrayOfNulls<Any>(initialCapacity) as Array<T?>

    }

    override fun push(item: T): T {
        if (topPointer == arrayBuffer.size-1) {
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
