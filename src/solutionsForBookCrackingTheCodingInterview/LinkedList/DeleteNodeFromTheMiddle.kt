package solutionsForBookCrackingTheCodingInterview.LinkedList

import solutionsForBookCrackingTheCodingInterview.LinkedList2
import java.util.*

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/16/2018
 */

/**
 * Task 2.3 -> Implement an algorithm to delete a node in the middle of a single linked list, given
 * only access to that node.
 */
/**
 * Single LinkedList
 */
fun <T> deleteGivenNode(node: LinkedList2.Node<T>?): Boolean {
    if (node?.next == null) {
        return false

    }
    val nextNode = node.next
    node.data = nextNode.data
    node.next = nextNode.next
    return true

}