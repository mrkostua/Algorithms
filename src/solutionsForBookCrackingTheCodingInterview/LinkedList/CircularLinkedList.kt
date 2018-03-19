package solutionsForBookCrackingTheCodingInterview.LinkedList

import solutionsForBookCrackingTheCodingInterview.LinkedList2

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/19/2018
 * "Cracking the Coding Interview" task 2.5
 */

/**
 * Task : Given a circular linked list, implement an algorithm which returns node at the beginning
 * of the loop.
 *  possibilities ->
 * 1) perfect circul
 */


fun <T> findBeggingOfTheLoop(head: LinkedList2.Node<T>): Pair<Boolean, LinkedList2.Node<T>> {
    //if linked list contains only 2 elements so second node points to the head
    //we can't use this algorithm (find some way to track it)
    if (head.next == null) {
        return Pair(false, head)

    }
    var slowPointer = head
    var fastPointer = head

    while (true) {
        slowPointer = slowPointer.next
        fastPointer = fastPointer.next.next
        if (fastPointer == null) {
            return Pair(false, head)

        } else if (fastPointer == slowPointer) {
            break

        }
    }

    slowPointer = head
    while (slowPointer != fastPointer) { //if head is a start of the loop alg will not enter this loop and just gonna return meeting point as head.
        slowPointer = slowPointer.next
        fastPointer = fastPointer.next

    }
    return Pair(true, fastPointer)


}

fun main(args: Array<String>) {
    val linkedList = LinkedList2<Int>()
    for (i in 0..7) {
        linkedList.addFirst(i)
    }
    linkedList.head.next.next.next.next.next.next = linkedList.head.next
    println(linkedList.toStringCircularLinkedList(20))
}