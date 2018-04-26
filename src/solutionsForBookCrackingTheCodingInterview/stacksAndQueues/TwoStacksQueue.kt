package solutionsForBookCrackingTheCodingInterview.stacksAndQueues

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 4/24/2018
 * "Cracking the Coding Interview" task 3.5
 */

/**
 * Task :Implement a MyQueue class which implements a queue using two stacks.
 */
class TwoStacksQueue<T> : QueueInterface<T> {
    override var size: Int = 0
        get() = headStack.size + tailStack.size

    private val headStack = ArrayStack<T>()
    val tailStack = ArrayStack<T>()

    override fun dequeue(): T {
        val result = peekHead()
        headStack.pop()
        return result
    }

    override fun enqueue(element: T) {
        tailStack.push(element)

    }

    override fun isEmpty(): Boolean {
        return tailStack.isEmpty()
    }

    override fun clearAll() {
        while (!tailStack.isEmpty()) {
            tailStack.pop()

        }
    }

    override fun peekHead(): T {
        if (headStack.isEmpty()) {
            if(tailStack.isEmpty()){
                throw UnsupportedOperationException("can't peekHead from empty stack")

            }
            while (!tailStack.isEmpty()) {
                headStack.push(tailStack.pop())

            }

        }
        return headStack.peek()

    }
}


interface QueueInterface<T> {
    var size: Int
    /**
     * removes element from the head
     */
    fun dequeue(): T

    /**
     * insert element to the tail
     */
    fun enqueue(element: T)

    fun isEmpty(): Boolean
    fun clearAll()
    fun peekHead(): T

}