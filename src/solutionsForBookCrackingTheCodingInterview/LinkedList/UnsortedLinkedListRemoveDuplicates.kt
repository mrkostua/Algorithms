package solutionsForBookCrackingTheCodingInterview.LinkedList

import java.util.*

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/10/2018
 * "Cracking the Coding Interview" task 2.1
 */


/**
 * Write code to remove duplicates from an unsorted linked list.
 * a temporary buffer is not allowed
 **/

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
 * buffer it is a peace of data which remember something that is going to be used in the future (gives fast access)
 * no need to access main data. Some temporary data.
 **/
fun <T : Any> removeDuplicatesWithoutAdditionalBuffer(head: LinkedList2.Node<T>?) {
    println("removeDuplicatesWithoutAdditionalBuffer()")
    if (head == null) {
        println("head is null")
        return

    }
    var currentNode: LinkedList2.Node<T>? = head
    var previousNode: LinkedList2.Node<T>?
    var loopNode: LinkedList2.Node<T>?

    while (currentNode != null) {
        previousNode = currentNode
        loopNode = currentNode.next
        while (loopNode != null) {
            if (currentNode.data == loopNode.data) {
                //removing loopNode
                previousNode!!.next = loopNode.next
                //iteration
                loopNode = previousNode.next

            } else {
                //iteration
                previousNode = loopNode
                loopNode = loopNode.next

            }

        }
        currentNode = currentNode.next
    }
}

fun main(args: Array<String>) {
    val random = Random()
    val unsortedList = LinkedList<Int>()

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
        linkedList.addFirst(random.nextInt(5))
    }
    println(linkedList.toString())
    removeDuplicatesWithoutAdditionalBuffer(linkedList.getHead())
    println("\nafter removing duplicates : ")
    print(linkedList.toString())

}