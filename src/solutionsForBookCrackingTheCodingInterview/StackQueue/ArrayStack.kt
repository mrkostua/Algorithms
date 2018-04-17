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

    override val size: Int
        get() = arrayBuffer.size

    override fun push(item: T): T {
        if (topPointer == arrayBuffer.size - 1) {
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

/*    override fun peek(): T {
        if (isEmpty()) {
            throw RuntimeException("empty stack")

        }
        return arrayBuffer[topPointer]!!

    }*/

    //TODO for using in TowerOfHanoi.kt
        override fun peek(): T {
        if (isEmpty()) {
            return -1 as T

        }
        return arrayBuffer[topPointer]!!

    }

    override fun isEmpty() = topPointer == -1

    fun printStack() {
//        println("\nstack size : " + arrayBuffer.size)
        for (i in arrayBuffer) {
            if (i == null) {
                print(" null")
            } else {
                print(" " + i.toString())
            }
        }
    }
}
