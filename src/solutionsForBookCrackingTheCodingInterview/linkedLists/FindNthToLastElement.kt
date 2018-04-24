package solutionsForBookCrackingTheCodingInterview.linkedLists

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/14/2018
 *  "Cracking the Coding Interview" task 2.2
 */

/**
 * Implement an algorithm to find the nth to last element of a singly linked list.
 * really hard to understand the description of the task but from looking at solution the task is to find  "n-1"
 * elements back from last element. (1,2,3,4,5) last element is 5 and the n=2 so result is 3
 *
 * Solution form the book
 */
private fun <T> getNthElementToLast(head: LinkedList2.Node<T>?, n: Int): LinkedList2.Node<T>? {
    println("getNthElementToLast algorithm")
    if (head == null || n < 1) {
        throw UnsupportedOperationException("head can't be null, and n need to be > 1")

    }
    var p1: LinkedList2.Node<T> = head
    var p2: LinkedList2.Node<T>? = head
    for (j in 0 until n - 1) { // skip n-1 steps ahead
        if (p2 == null) {
            return null // not found since list size < n
        }
        p2 = p2.next
    }
    while (p2!!.next != null) {
        p1 = p1.next
        p2 = p2.next
    }
    return p1
}


fun main(args: Array<String>) {
    val linkedList = LinkedList2<Int>()
    for (i in 0..10) {
        linkedList.addFirst(i)
    }
    println(linkedList.toString())
    println(getNthElementToLast(linkedList.head, 5)?.data)
}
