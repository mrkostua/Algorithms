package solutionsForBookCrackingTheCodingInterview.StackQueue

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
class StackSet<T>(initialCapacity: Int, private val stackMaxSize: Int) : ArrayStack<T>(initialCapacity) {
    public val stackPointers: Array<Int> = Array(arrayBuffer.size / stackMaxSize, { -1 })

    override fun pop(): T {
        val element = peek()
        val topSubStackPointer = getTopSubStackIndex()
        arrayBuffer[topPointer] = null
        stackPointers[topSubStackPointer] -= 1
        topPointer--
        return element

    }

    override fun push(item: T): T {
        if (topPointer == stackMaxSize * stackPointers.size - 1) {
            throw RuntimeException("push - stack is full")

        }
        topPointer++
        val topSubStackPointer = getTopSubStackIndex()

        stackPointers[topSubStackPointer] += 1
        arrayBuffer[topPointer] = item
        println("topSubStackPointer " + topSubStackPointer)
        println("stackPointers[topSubStackPointer] : " + stackPointers[topSubStackPointer])
        println("topPointer : " + topPointer)

        if (stackPointers[topSubStackPointer] % stackMaxSize == stackMaxSize - 1) {
            println("stackPointers[topSubStackPointer] == stackMaxSize - 1 " + true)
            stackPointers[topSubStackPointer + 1] = topPointer

        }
        return item
    }

    fun popAt(subStackIndex: Int): T {
        when {
            stackPointers.size <= subStackIndex && subStackIndex < 0 ->
                throw UnsupportedOperationException("popAt : subStack with index : " + subStackIndex + " not exist")
            stackPointers[subStackIndex] == -1 -> throw UnsupportedOperationException("subStack is empty")

            stackPointers[subStackIndex] == topPointer -> topPointer--

        }
        println("\npopAt")
        println("stackPointers[subStackIndex] " + stackPointers[subStackIndex])
        val element = arrayBuffer[stackPointers[subStackIndex]]
        arrayBuffer[stackPointers[subStackIndex]] = null
        stackPointers[subStackIndex] -= 1
        return element!!
    }

    private fun getTopSubStackIndex(): Int {
        if (isEmpty() || topPointer < stackMaxSize) {
            return 0

        }
        return topPointer / stackMaxSize
        //+ if (topPointer % stackMaxSize > 0) 1 else 0

    }

    public fun printStack() {
        println("\nstack size : " + arrayBuffer.size)
        for (i in arrayBuffer) {
            if (i == null) {
                print(" null")
            } else {
                print(" " + i.toString())
            }
        }
    }
}


fun main(args: Array<String>) {
    val multiStack = StackSet<Int>(40, 10)
    for (i in 0..24) {
        multiStack.push(i)
    }
    println("initial data of the stack : ")
    multiStack.printStack()


    multiStack.pop()
    multiStack.pop()
    multiStack.printStack()
    multiStack.popAt(0)
    multiStack.printStack()

    println("\ntesting : ")
    multiStack.stackPointers.forEach { print(" " + it) }

    multiStack.popAt(1)
    multiStack.popAt(1)
    multiStack.popAt(1)
    multiStack.printStack()
    multiStack.popAt(2)
    multiStack.popAt(2)
    multiStack.printStack()
    multiStack.push(30)
    multiStack.push(30)
    multiStack.push(30)
    multiStack.printStack()


}

