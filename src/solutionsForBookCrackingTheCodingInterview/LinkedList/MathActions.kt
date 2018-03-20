package solutionsForBookCrackingTheCodingInterview.LinkedList

import solutionsForBookCrackingTheCodingInterview.LinkedList2

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/20/2018
 * "Cracking the Coding Interview" task 2.4
 */

/**
 * You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1’s digit is at the head of
 * the list. Write a function that adds the two numbers and returns the sum as a linked
 * list.
 */

/**
 * Idea is to create fun to add, sub, div, mul with one main method.
 */

/**
 * recursive method for adding,
 * pros - bby using linkedList we can represent big numbers with low memory usage.
 * nodes stored in reverse order so the head is points to last digit (smallest as 002)
 */
fun addNumbers(firstNumber: LinkedList2.Node<Int>, secondNumber: LinkedList2.Node<Int>, lastAddNumber: Int = 0) {
    val toBiggerNumber: Int
    val sum: Int = firstNumber.data + secondNumber.data + lastAddNumber
    if (sum >= 10) {
        toBiggerNumber = 1
        firstNumber.data = sum - 10

    } else {
        toBiggerNumber = 0
        firstNumber.data = firstNumber.data + secondNumber.data

    }
    if (firstNumber.next != null || secondNumber.next != null) {
        addNumbers(if (firstNumber.next == null) {
            firstNumber.next = LinkedList2.Node(0, null)
            firstNumber.next
        } else firstNumber.next ,
                if (secondNumber.next == null) {
                    secondNumber.next = LinkedList2.Node(0, null)
                    secondNumber
                } else secondNumber.next, toBiggerNumber)

    } else if (toBiggerNumber > 0) {
        firstNumber.next = LinkedList2.Node(1, null)

    }

}

//TODO print in normal order  not reverse
fun main(args: Array<String>) {
    val n1 = LinkedList2<Int>()
    val n2 = LinkedList2<Int>()
    n1.addFirst(5)
    n1.addLast(6)
    n1.addLast(9)

    n2.addFirst(9)
    n2.addLast(5)
    n2.addLast(9)
    n2.addLast(9)
    n2.addLast(9)

    println("initial data: \n first number : " + n1.toString() + " second number : " + n2.toString())
    addNumbers(n1.head, n2.head)
    println("Result of adding 2 numbers : " + n1.toString())

}