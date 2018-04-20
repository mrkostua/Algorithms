package solutionsForBookCrackingTheCodingInterview.StackQueue

import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/23/2018
 */

open class ArrayStack<T>(initialCapacity: Int = 0, val stackNumber: Int = 0) : Stack<T>() {
    val defaultCapacity = 20
    var topPointer = -1 //empty stack
    val arrayBuffer: Array<T?> = if (initialCapacity <= 0) {
        arrayOfNulls<Any>(defaultCapacity) as Array<T?>

    } else {
        arrayOfNulls<Any>(initialCapacity) as Array<T?>

    }

    override val size: Int
        get() = arrayBuffer.size

    override fun push(item: T): T {
        println("push() item : " + item.toString())
        if (topPointer == arrayBuffer.size - 1) {
            throw RuntimeException("push - stack is full")

        }
        topPointer++
        arrayBuffer[topPointer] = item
        return item

    }

    override fun pop(): T {
        val k = peek()
        println("pop() item : " + k.toString())
        arrayBuffer[topPointer] = null
        topPointer--
        return k
    }

    override fun peek(): T {
        if (isEmpty()) {
            throw RuntimeException("peek() empty stack")

        }
        return arrayBuffer[topPointer]!!

    }

    override fun isEmpty() = topPointer == -1

    fun printStack() {
        for (i in arrayBuffer) {
            if (i == null) {
                print(" n")
            } else {
                print(" " + i.toString())
            }
        }
    }

    fun getAllElements(): ArrayList<T> {
        val resultArray = java.util.ArrayList<T>()
        for (i in arrayBuffer) {
            if (i != null) {
                resultArray.add(i)

            }
        }
        return resultArray
    }

    /**
     * Works properly only if all elements in ArrayStack are unique
     */
    override fun containsAll(elements: Collection<T>): Boolean {
        for (el in elements) {
            if (!arrayBuffer.contains(el)) {
                return false
            }
        }
        return true
    }

}
