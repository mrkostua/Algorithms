package solutionsForBookCrackingTheCodingInterview.LinkedList

import java.util.*
import solutionsForBookCrackingTheCodingInterview.LinkedList2
import solutionsForBookCrackingTheCodingInterview.LinkedList2.Node

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/10/2018
 */

/**
 * Write code to remove duplicates from an unsorted linked list.
 * a temporary buffer is not allowed
 **/


private val unsortedList = LinkedList<Int>()


/**
 * algorithm with using additional buffer
 */
fun removeDuplicates(list: LinkedList<Int>): LinkedList<Int> {
    println("removeDuplicates()")
    val set = HashSet<Int>()
    set.addAll(list)
    list.clear()
    list.addAll(set)
    return list

}

/**
 * buffer it is a peace of data which remember something that is go be used in the future (gives fast access)
 * no need to access main data. Some temporary data.
 **/
fun removeDuplicatesWithoutAdditionalBuffer(head: LinkedList2.Node<Int>?) {
    println("removeDuplicatesWithoutAdditionalBuffer()")
    if (head == null) {
        println("head is null")
        return
    }
    var currentNode: LinkedList2.Node<Int>? = head
    var previousNode: LinkedList2.Node<Int>?
    var loopNode: LinkedList2.Node<Int>?
    while (currentNode != null) {
        previousNode = currentNode
        loopNode = currentNode.next
        while (loopNode != null) {
            if (currentNode.data == loopNode.data) {
                previousNode!!.next = loopNode.next
            }
            previousNode = loopNode
            loopNode = loopNode.next

        }
        currentNode = currentNode.next
    }


    //we can first sort this array and after just by using iterator check previous and next element for equality

    //search in linked list is not efficient ( it just going form the head to the end and comparing element)
}


fun main(args: Array<String>) {
    val random = Random()
    println("FIRST version : " + "\n initial unsorted linkedList")
    for (i in 0..20) {
        unsortedList.add(random.nextInt(10))
        print(unsortedList[i].toString() + ", ")
    }
    println("\nafter removing duplicates : ")
    removeDuplicates(unsortedList).forEach({ print(it.toString() + ", ") })
    println("\n\n")

//second algorithm
    println("SECOND version : " + "\n initial unsorted linkedList")
    val linkedList = LinkedList2<Int>()
    for (i in 0..20) {
        linkedList.addFirst(random.nextInt(10))
    }
    println(linkedList.toString())
    removeDuplicatesWithoutAdditionalBuffer(linkedList.getHead())
    println("\nafter removing duplicates : ")
    print(linkedList.toString() + ", ")


}
