package solutionsForBookCrackingTheCodingInterview.stacksAndQueues

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/21/2018
 * "Cracking the Coding Interview" task 3.1, 3.2
 */

/**
 * Task 1 :
 * Describe how you could use a single array to implement three stacks.
 */

/**
 * Solution :
 * Fixed-size stack
 * If it is fixed-size stack, by creating array and dividing into 3 parts with first and last element(problem is that by deleting form one stack we will crash other stacks indexes)
 * Other way (easier) store elements of stack on after next like s1,s2,s3 first element If it is deleted it is set as null and pointer (top) just point to a previous element.
 *
 * Dynamic stack (stack is growing as long as there is place in array)
 * wrong answer -> array size can't be dynamically increased. Solution is to 3 stacks growing independently one of each others and some way to free up array memory after pop().
 *
 * Solution - is to use stack with StackNode as linkedLists so every element has reference to previous element which in return gives way to add elements of 3 stacks
 * one after one and deleting them just by moving topPointer to previous element. One problem -> by deleting inner element of the array leaves free space( solution is to create additional array
 * with free spaces indexes and after pushing use places from this array first or move whole array after popping which is bad because of additional time and resource complexity.
 *
 * if we need only 2 stacks easy choice is to set their top pointers to first and last element and push until p1.pointer == p2.pointer.
 *
 *
 */


/**
 * Task 2 :
 *
 * How would you design a stack which, in addition to push and pop, also has a function
 * min which returns the minimum element? Push, pop and min should all operate in
 * O(1) time
 */

/**
 * WRONG answer it is mor for finding last element not min.
 * Stack need to be implemented over some data structure so in case of array(constant time of find element as first) in case of linkedList(head is top element
 * only choice is to iterate through all elements until node.next = null)
 *
 */
public class MinArrayStack(initialCapacity: Int = 0) : ArrayStack<Int>(initialCapacity) {
    private val minElementStack = ArrayStack<Int>(initialCapacity)
    override fun push(item: Int): Int {
        if (peek() <= item) {
            minElementStack.push(item)

        }
        return super.push(item)
    }

    override fun pop(): Int {
        val topElement = super.pop()
        if (topElement == getMin()) {
            minElementStack.pop()

        }
        return topElement
    }

    public fun getMin(): Int {
        if (minElementStack.isEmpty()) {
            return Integer.MAX_VALUE

        }
        return minElementStack.peek()
    }

}

fun main(args: Array<String>) {

}


