package solutionsForBookCrackingTheCodingInterview.StackQueue

import java.util.*

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/27/2018
 * "Cracking the Coding Interview" task 3.3
 */

/**
 * Task :
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore,
 * in real life, we would likely start a new stack when the previous stack exceeds
 * some threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks
 * should be composed of several stacks, and should create a new stack once
 * the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should
 * behave identically to a single stack (that is, pop() should return the same values as it
 * would if there were just a single stack).
 * FOLLOW UP
 * Implement a function popAt(int index) which performs a pop operation on a specific
 * sub-stack.
 */

/**
 * Solution form the book is slightly different -> the use ArrayList as container for Stacks, so in this case you don't need to
 * maintain array of pointers but it is more time/memory complex solution (as Used arrayList of arrayLists(stacks if implemented using array).
 * In this case we are using one ArrayLists divided into smaller stacks using stackPointers.
 */
class StackSet<T>(initialCapacity: Int, private val stackMaxSize: Int) : ArrayStack<T>(initialCapacity) {
    private val stackPointers: Array<Int> = if (arrayBuffer.size % stackMaxSize > 0) {
        Array(arrayBuffer.size / stackMaxSize + 1, { -1 })
    } else {
        Array(arrayBuffer.size / stackMaxSize, { -1 })
    }
    /**
     * space efficient (adding array of empty spaces in subArray, so push() will first add element to this stack)
     */
    private val emptySpaceIndexes = ArrayList<Int>(initialCapacity - stackMaxSize)

    override fun pop(): T {
        val element = peek()
        val stackPointer = getSubStackNumber(topPointer)
        arrayBuffer[topPointer] = null
        stackPointers[stackPointer] -= 1
        topPointer--
        return element

    }

    override fun push(item: T): T {
        if (emptySpaceIndexes.isEmpty() && topPointer == arrayBuffer.size - 1) {
            throw RuntimeException("push - stack is full")

        }
        if (emptySpaceIndexes.isEmpty()) {
            topPointer++
            val topSubStackPointer = getSubStackNumber(topPointer)
            arrayBuffer[++stackPointers[topSubStackPointer]] = item

            if (stackPointers[topSubStackPointer] % stackMaxSize == stackMaxSize - 1) {
                stackPointers[topSubStackPointer + 1] = topPointer

            }

        } else {
            val subStackNumber = getSubStackNumber(emptySpaceIndexes[0])
            emptySpaceIndexes.removeAt(0)
            if (stackPointers[subStackNumber] % stackMaxSize < stackMaxSize - 1) {
                stackPointers[subStackNumber] += 1
                arrayBuffer[stackPointers[subStackNumber]] = item

            } else {
                throw RuntimeException("push to subStack(not a top subStack) with empty places Error")

            }
        }
        return item
    }

    fun popAt(subStackIndex: Int): T {
        when {
            (stackPointers.size <= subStackIndex && subStackIndex < 0) ->
                throw UnsupportedOperationException("popAt : subStack with index : " + subStackIndex + " not exist")
            stackPointers[subStackIndex] == -1 ->
                throw UnsupportedOperationException("subStack is empty")

            stackPointers[subStackIndex] != topPointer -> emptySpaceIndexes.add(stackPointers[subStackIndex])
            stackPointers[subStackIndex] == topPointer -> topPointer--

        }
        val element = arrayBuffer[stackPointers[subStackIndex]]

        arrayBuffer[stackPointers[subStackIndex]] = null
        stackPointers[subStackIndex] -= 1
        return element!!
    }

    private fun getSubStackNumber(index: Int): Int {
        if (isEmpty() || index < stackMaxSize) {
            return 0
        }
        return index / stackMaxSize
    }

}

fun main(args: Array<String>) {
    //testing
    val multiStack = StackSet<Int>(46, 10)
    for (i in 0 until 46) {
        multiStack.push(i)
    }
    with(multiStack) {
        printStack()
        for (i in 0..3) {
            popAt(0)
        }
        push(3)
        push(3)
        push(3)
        push(3)
        printStack()
        pop()
        pop()
        pop()
        pop()
        push(3)
        printStack()


    }

}