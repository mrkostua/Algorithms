package solutionsForBookCrackingTheCodingInterview.linkedLists

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/9/2018
 */

/**
 * @Singly-linked list:
 * Pros: Simple in implementation, requires relatively lesser memory for storage, assuming you need to delete/insert (at)
 * next node – deletion/insertion is faster.
 * Cons: Cannot be iterated in reverse, need to maintain a handle to the head node of the list else, the list will be
 * lost in memory. If you’re deleting previous node or inserting at previous node, you will need to traverse
 * list from head to previous node to be able to carry out those operations – O(N) time.
 *
 * So, this should be used when you have lesser memory and your main goal is insertion/deletion and not searching elements
 *
 *
 * @Doubly-linked list:
 * Pros: Can be iterated in forward as well as reverse direction. In case of needing to delete previous node, there is
 * no need to traverse from head node, as the node to be deleted can be found from ‘.previous’ pointer.
 * Cons: Relatively complex to implement, requires more memory for storage (1 ‘.previous’ pointer per node).
 * Insertions and deletions are relatively more time consuming (assigning/reassigning ‘.previous’ pointer for neighbor nodes)
 *
 * This should be used when you have no or minimal limitations on memory, and your main goal is to search for elements.
 **/